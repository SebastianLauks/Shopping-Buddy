package lauks.sebastian.shoppingbuddy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.data.Product
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils

class MainActivity : AppCompatActivity() {
    private var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }


    fun initUi(){
        val factory = InjectorUtils.proviceProductsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(ProductsViewModel::class.java)

        main_recycler_view.adapter = ProductsAdapter(viewModel.getProducts())
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.setHasFixedSize(true)

        viewModel.getProducts().observe(this, Observer { products ->
            (main_recycler_view.adapter as ProductsAdapter).notifyDataSetChanged()
        })

        bt_add.setOnClickListener {
            val product = Product("prod01"+i, "Nalesniki"+i)
            i++;
            viewModel.addProduct(product)
        }
    }
}
