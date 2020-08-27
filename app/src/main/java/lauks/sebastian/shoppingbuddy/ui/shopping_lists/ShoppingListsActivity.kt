package lauks.sebastian.shoppingbuddy.ui.shopping_lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shopping_lists.*
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.data.shopping_lists.ShoppingList
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils

class ShoppingListsActivity : AppCompatActivity() {

    lateinit var viewModel: ShoppingListsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_lists)

        initUi()
    }


    private fun initUi(){
        val onShoppingListLongClicked: (name: String) -> Unit = {name ->
            createCustomDialog("Czy na pewno chcesz usunąć $name z listy?", "Tak", "Nie") {
                val shoppingList: ShoppingList? = viewModel.findShoppingList(name)
                if (shoppingList != null) {
                    viewModel.removeShoppingList(shoppingList)
                    Toast.makeText(
                        applicationContext,
                        R.string.text_shopping_list_successfull_remove,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        R.string.text_shopping_list_unsuccessfull_remove,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }



        val factory = InjectorUtils.provideShoppingListsViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(ShoppingListsViewModel::class.java)

        shopping_lists_recycler_view.adapter =
            ShoppingistsAdapter(
                viewModel.getShoppingLists(),
                onShoppingListLongClicked
            )
        shopping_lists_recycler_view.layoutManager = LinearLayoutManager(this)
        shopping_lists_recycler_view.setHasFixedSize(true)

        //Todo
        viewModel.getShoppingLists().observe(this, Observer { shoppingLists ->
            Log.d("size", shoppingLists.size.toString())
            (shopping_lists_recycler_view.adapter as ShoppingistsAdapter).notifyDataSetChanged()
        })

    }

    fun createCustomDialog(message: String, yes: String, no:String, yesFunction: () -> Unit){
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title
//            builder.setTitle("Usun wszystkie przedmioty")

        // Display a message on alert dialog
        builder.setMessage(message)

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(yes){ _, _ ->
            yesFunction()
        }


        // Display a negative button on alert dialog
        builder.setNegativeButton(no){ _, _ ->
        }

        builder.create().show()
    }
}
