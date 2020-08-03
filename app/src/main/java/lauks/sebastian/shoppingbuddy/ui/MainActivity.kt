package lauks.sebastian.shoppingbuddy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.data.Product
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()


    }

    private fun initUi(){
        //needed to handle long click on products (in order to remove it)
        val onProductLongClicked: (name: String) -> Unit = {name ->
            val product: Product? = viewModel.findProductByName(name)
            if(product != null){
                viewModel.removeProduct(product)
                Toast.makeText( this, R.string.text_product_successfull_remove, Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText( this, R.string.text_product_unsuccessfull_remove, Toast.LENGTH_LONG).show()
            }
        }

        val factory = InjectorUtils.proviceProductsViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory)
            .get(ProductsViewModel::class.java)

        main_recycler_view.adapter = ProductsAdapter(viewModel.getProducts(), onProductLongClicked)
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.setHasFixedSize(true)

        viewModel.getProducts().observe(this, Observer { products ->
            (main_recycler_view.adapter as ProductsAdapter).notifyDataSetChanged()
        })

        bt_add.setOnClickListener {
            if(et_new_products.text.toString() != "") {
                val product =
                    Product(et_new_products.text.toString(), et_new_products.text.toString())
                et_new_products.setText("")
                viewModel.addProduct(product)
            }else{
                Toast.makeText(this, R.string.text_empty_product, Toast.LENGTH_LONG).show()
            }
        }
    }
}
