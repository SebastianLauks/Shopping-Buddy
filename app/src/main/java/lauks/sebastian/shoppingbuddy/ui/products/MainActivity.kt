package lauks.sebastian.shoppingbuddy.ui.products

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.data.Product
import lauks.sebastian.shoppingbuddy.utilities.InjectorUtils

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ProductsViewModel
    val TABS_TITLES = listOf("Lista", "Koszyk")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ProductsViewPagerAdapter(this)
        adapter.addFragment(ProductsFragment.newInstance())
        adapter.addFragment(ProductsShoppingCartFragment.newInstance())
        viewPager.adapter = adapter


        val tabLayout = tabs
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = TABS_TITLES[position]
        }.attach()
    }

}
