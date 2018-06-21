package com.example.maith.sharinginternetaccess.roomdatabase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.maith.sharinginternetaccess.R
import com.example.maith.sharinginternetaccess.roomdatabase.adapter.ProductAdapter
import com.example.maith.sharinginternetaccess.roomdatabase.database.AppDatabase
import com.example.maith.sharinginternetaccess.roomdatabase.model.Product
import kotlinx.android.synthetic.main.fragment_room_database.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class RoomFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room_database, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnInsert.setOnClickListener {
            insertDatabase()
            Toast.makeText(context,resources.getString(R.string.notyet,"hihi","haha"),Toast.LENGTH_LONG).show()
        }
    }

    fun insertDatabase() {
        async(UI) {
            val users: Deferred<List<Product>> = bg { getProduct() }
            showProduct(users.await())
        }
    }

    private fun showProduct(products: List<Product>){
          productRecyclerview.apply {
              layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
              adapter = ProductAdapter(products)
          }
    }

    private fun getProduct(): List<Product> {
        val dao = AppDatabase.getInstance(context!!).productDao()
        val users = dao.getAll()

        if (users.isEmpty()) {
            val products = ArrayList<Product>()
            products.add(Product("1", "Budi", R.drawable.ico_mic_on))
            products.add(Product("2", "Budi", R.drawable.ico_mic_on))
            products.add(Product("3", "Budi", R.drawable.ico_mic_on))
            products.add(Product("4", "Budi", R.drawable.ico_mic_on))
            products.add(Product("5", "Budi", R.drawable.ico_mic_on))
            products.add(Product("6", "Budi", R.drawable.ico_mic_on))

            dao.insertAll(products)

            return dao.getAll()
        }

        return users
    }


    companion object {
        val TAG = "RoomFragment"
    }
}