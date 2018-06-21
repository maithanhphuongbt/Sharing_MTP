package com.example.maith.sharinginternetaccess.roomdatabase.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maith.sharinginternetaccess.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_get_data_retrofit.view.*

/**
 * Created by User on 22-Apr-18.
 */
class ProductAdapter(private val productList: List<com.example.maith.sharinginternetaccess.roomdatabase.model.Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_get_data_retrofit, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = productList!!.size
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(productList!![position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: com.example.maith.sharinginternetaccess.roomdatabase.model.Product) {
            itemView.tv_name.text = product.productName
            Picasso.get().load(product.productThumb).into(itemView.img_product)
        }
    }
}