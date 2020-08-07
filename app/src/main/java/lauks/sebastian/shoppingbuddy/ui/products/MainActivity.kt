package lauks.sebastian.shoppingbuddy.ui.products

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.ui.products.products_incart.ProductsInCartFragment
import lauks.sebastian.shoppingbuddy.ui.products.products_tobuy.ProductsFragment

class MainActivity : AppCompatActivity() {

    val TABS_TITLES = listOf("Lista", "Koszyk")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ProductsViewPagerAdapter(this)
        adapter.addFragment(ProductsFragment.newInstance())
        adapter.addFragment(ProductsInCartFragment.newInstance())
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false // disable swiping between tabs



        val tabLayout = tabs
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = TABS_TITLES[position]
        }.attach()
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
