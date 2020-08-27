package lauks.sebastian.shoppingbuddy.ui.shopping_lists

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_list_item.view.*
import lauks.sebastian.shoppingbuddy.R
import lauks.sebastian.shoppingbuddy.data.shopping_lists.ShoppingList
import lauks.sebastian.shoppingbuddy.ui.products.ProductsActivity

class ShoppingistsAdapter(
    private val shoppingListsList: LiveData<List<ShoppingList>>,
    private val onShoppingListLongClicked: (name: String) -> Unit
) : RecyclerView.Adapter<ShoppingistsAdapter.ShoppingListsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingistsAdapter.ShoppingListsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item, parent, false)
        val holder =
            ShoppingListsViewHolder(itemView)

        holder.itemView.setOnLongClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onShoppingListLongClicked.invoke(shoppingListsList.value!![holder.adapterPosition].name)
            }
            false
        }

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, ProductsActivity::class.java)
            intent.putExtra("shoppingListId", shoppingListsList.value!![holder.adapterPosition].id)
            context.startActivity(intent)
        }

        return holder
    }

    override fun getItemCount() = shoppingListsList.value!!.size

    override fun onBindViewHolder(
        holder: ShoppingistsAdapter.ShoppingListsViewHolder,
        position: Int
    ) {
        val currenItem = shoppingListsList.value!![position]
        holder.tvShoppingListsName.text = currenItem.name
        holder.tvCodeContent.text = currenItem.code


    }

    class ShoppingListsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvShoppingListsName: TextView = itemView.tv_shopping_list_name
        val tvCodeContent: TextView = itemView.tv_code_content
    }

}