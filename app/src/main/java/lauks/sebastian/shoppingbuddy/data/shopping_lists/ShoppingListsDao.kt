package lauks.sebastian.shoppingbuddy.data.shopping_lists

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import lauks.sebastian.shoppingbuddy.data.products.Product

class ShoppingListsDao {

    private val userShoppingListsFB: DatabaseReference
    private val shoppingListsFB: DatabaseReference
    private val shoppingListsList = mutableListOf<ShoppingList>()
    private val shoppingListsLiveData = MutableLiveData<List<ShoppingList>>()

    private val userId = "user1"


    init {
        shoppingListsLiveData.value = shoppingListsList
        userShoppingListsFB =  Firebase.database.reference.child("Users").child(userId)
            .child("shoppingLists")
        shoppingListsFB = Firebase.database.reference.child("ShoppingLists")


        userShoppingListsFB.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                shoppingListsList.clear()
                var itemsProcessed = 0
                snapshot.children.forEach{ shoppingListSnapshot: DataSnapshot ->
                    val shoppingListId = shoppingListSnapshot.value as String
                    shoppingListsFB.child(shoppingListId).child("name").addListenerForSingleValueEvent(object :ValueEventListener{
                        override fun onCancelled(error: DatabaseError) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onDataChange(_snapshot: DataSnapshot) {
                            val shoppingListName =  _snapshot.value.toString()
                            shoppingListsList.add(ShoppingList(shoppingListId, shoppingListName))
                            if(++itemsProcessed == snapshot.childrenCount.toInt())
                                shoppingListsLiveData.value = shoppingListsList
                        }

                    })
                }
                shoppingListsLiveData.value = shoppingListsList

            }

        })
    }

    fun getShoppingLists() = shoppingListsLiveData as LiveData<List<ShoppingList>>
}