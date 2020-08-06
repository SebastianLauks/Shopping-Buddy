package lauks.sebastian.shoppingbuddy.ui.products

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProductsViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    private val mFragmentList = ArrayList<Fragment>()

    override fun getItemCount() = mFragmentList.size

    override fun createFragment(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    fun addFragment(fragment: Fragment){
        mFragmentList.add(fragment)
    }
}