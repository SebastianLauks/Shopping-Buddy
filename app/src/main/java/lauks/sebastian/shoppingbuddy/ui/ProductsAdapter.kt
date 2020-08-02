package lauks.sebastian.shoppingbuddy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_item.view.*
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.data.Product

class ProductsAdapter(private val productsList: LiveData<List<Product>>, private val onProductLongClicked: (name: String) -> Unit): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        val holder = ProductsViewHolder(itemView)
        holder.itemView.setOnLongClickListener {
            if(holder.adapterPosition != RecyclerView.NO_POSITION){
                onProductLongClicked.invoke(productsList.value!![holder.adapterPosition].name)
            }
            false
        }

        return holder
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentItem = productsList.value!![position]
        holder.tvProductName.text = currentItem.name
    }

    override fun getItemCount() = productsList.value!!.size

    class ProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvProductName: TextView = itemView.tv_product_name
    }
}