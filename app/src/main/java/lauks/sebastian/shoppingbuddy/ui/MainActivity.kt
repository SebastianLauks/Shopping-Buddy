package lauks.sebastian.shoppingbuddy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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


    fun initUi(){
        //needed to handle long click on products (in order to remove it)
        val onProductLongClicked: (name: String) -> Unit = {name ->
            val product: Product? = viewModel.findProductByName(name)
            if(product != null){
                viewModel.removeProduct(product)
                Toast.makeText( this, "Produkt został usunięty", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText( this, "Wystąpił błąd podczas usuwania produktu.", Toast.LENGTH_LONG).show()
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
            val product = Product(et_new_products.text.toString(), et_new_products.text.toString())
            et_new_products.setText("")
            viewModel.addProduct(product)
        }
    }
}
