package lauks.sebastian.shoppingbuddy.ui.shopping_lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils

class CreateShoppingList : AppCompatActivity() {

    private lateinit var viewModel: ShoppingListsViewModel
    private lateinit var btCreate: Button
    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_create_shopping_list)

        val factory = InjectorUtils.provideShoppingListsViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(ShoppingListsViewModel::class.java)

        btCreate = findViewById(R.id.bt_create_shopping_list)
        etName = findViewById(R.id.et_new_shopping_list)

        btCreate.setOnClickListener {
            val name = etName.text.toString()
            if(name == ""){
                //ToDo
            }else{
                etName.setText("")
                viewModel.createShoppingList(name)
                finish()
            }

        }

    }
}
