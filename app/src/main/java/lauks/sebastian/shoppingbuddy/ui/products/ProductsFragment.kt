package lauks.sebastian.shoppingbuddy.ui.products

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_products.*

import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.data.Product
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils


class ProductsFragment : Fragment() {

//    private var param1: String? = null
//    private var param2: String? = null

    private var listener: OnFragmentInteractionListener? = null
    lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    private fun initUi(){
        //needed to handle long click on products (in order to remove it)
        val onProductLongClicked: (name: String) -> Unit = {name ->
            val product: Product? = viewModel.findProductByName(name)
            if(product != null){
                viewModel.removeProduct(product)
                Toast.makeText(activity?.applicationContext, R.string.text_product_successfull_remove, Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText( activity?.applicationContext, R.string.text_product_unsuccessfull_remove, Toast.LENGTH_LONG).show()
            }
        }

        val factory = InjectorUtils.proviceProductsViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(ProductsViewModel::class.java)

        products_recycler_view.adapter = ProductsAdapter(
            viewModel.getProducts(),
            onProductLongClicked
        )
        products_recycler_view.layoutManager = LinearLayoutManager(activity?.applicationContext)
        products_recycler_view.setHasFixedSize(true)

        viewModel.getProducts().observe(activity as LifecycleOwner, Observer { products ->
            (products_recycler_view.adapter as ProductsAdapter).notifyDataSetChanged()
        })

        bt_add.setOnClickListener {
            tryToAddProduct()
        }
        et_new_products.setOnEditorActionListener{_,actionId,_->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_NEXT) {
                tryToAddProduct()
                true
            }
            false
        }

    }
    fun tryToAddProduct(){
        if(et_new_products.text.toString() != "") {
            val product =
                Product(et_new_products.text.toString(), et_new_products.text.toString())
            et_new_products.setText("")
            viewModel.addProduct(product)
        }else{
            Toast.makeText(activity?.applicationContext, R.string.text_empty_product, Toast.LENGTH_LONG).show()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ProductsFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
