package lauks.sebastian.shoppingbuddy.ui.products.products_incart

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products_in_cart.*

import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.data.Product
import lauks.sebastian.shoppingbuddy.ui.products.ProductsViewModel
import lauks.sebastian.shoppingbuddy.ui.products.products_tobuy.ProductsAdapter
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProductsShoppingCartFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProductsShoppingCartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsInCartFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.fragment_products_in_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    private fun initUi(){
        //needed to handle long click on products (in order to remove it)
        val onProductLongClicked: (name: String) -> Unit = {name ->
            val product: Product? = viewModel.findProductInCart(name)
            if(product != null){
                viewModel.moveProductsFromCart(product)
                Toast.makeText(activity?.applicationContext, R.string.text_product_successfull_move_to_cart, Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText( activity?.applicationContext, R.string.text_product_unsuccessfull_move_to_cart, Toast.LENGTH_LONG).show()
            }
        }

        val factory = InjectorUtils.proviceProductsViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(ProductsViewModel::class.java)

        products_in_cart_recycler_view.adapter =
            ProductsInCartAdapter(
                viewModel.getProductsInCart(),
                onProductLongClicked
            )
        products_in_cart_recycler_view.layoutManager = LinearLayoutManager(activity?.applicationContext)
        products_in_cart_recycler_view.setHasFixedSize(true)

        viewModel.getProductsInCart().observe(activity as LifecycleOwner, Observer { products ->
            (products_in_cart_recycler_view.adapter as ProductsInCartAdapter).notifyDataSetChanged()
        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductsShoppingCartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ProductsInCartFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
