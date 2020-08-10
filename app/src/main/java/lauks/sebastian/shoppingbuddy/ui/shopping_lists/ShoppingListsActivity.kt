package lauks.sebastian.shoppingbuddy.ui.shopping_lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_shopping_lists.*
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils

class ShoppingListsActivity : AppCompatActivity() {

    lateinit var viewModel: ShoppingListsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_lists)

        initUi()
    }


    private fun initUi(){
        val factory = InjectorUtils.provideShoppingListsViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(ShoppingListsViewModel::class.java)

        shopping_lists_recycler_view.adapter =
            ShoppingistsAdapter(
                viewModel.getShoppingLists()
            )
        shopping_lists_recycler_view.layoutManager = GridLayoutManager(this,2)
        shopping_lists_recycler_view.setHasFixedSize(true)

        //Todo
        viewModel.getShoppingLists().observe(this, Observer { shoppingLists ->
            Log.d("size", shoppingLists.size.toString())
            (shopping_lists_recycler_view.adapter as ShoppingistsAdapter).notifyDataSetChanged()
        })





    }
}
