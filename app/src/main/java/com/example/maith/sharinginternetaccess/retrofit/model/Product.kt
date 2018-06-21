package com.example.maith.sharinginternetaccess.retrofit.model

import java.io.Serializable


/**
 * Created by User on 22-Apr-18.
 */
data class Product(val name: String,
                   val thumnail: String) : Serializable {
    companion object {
        fun getAllProduct(productList: List<Product>): ArrayList<Product> {
            val arrayList = ArrayList<Product>()
            for (product in productList) {

                arrayList.add(product)

            }
            return arrayList
        }
    }
}