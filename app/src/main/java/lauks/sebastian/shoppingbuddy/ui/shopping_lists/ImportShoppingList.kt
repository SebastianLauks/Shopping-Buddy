package lauks.sebastian.shoppingbuddy.ui.shopping_lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils

class ImportShoppingList : AppCompatActivity() {

    private lateinit var viewModel: ShoppingListsViewModel
    private lateinit var btImport: Button
    private lateinit var etCode: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_import_shopping_list)

        val factory = InjectorUtils.provideShoppingListsViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(ShoppingListsViewModel::class.java)

        btImport = findViewById(R.id.bt_import_shopping_list)
        etCode = findViewById(R.id.et_shopping_list_code)

        btImport.setOnClickListener {
            val code = etCode.text.toString()
            if (code == "") {
                //ToDo
            } else {
                etCode.setText("")
                viewModel.importShoppingList(code)
                finish()
            }
        }
    }
}
