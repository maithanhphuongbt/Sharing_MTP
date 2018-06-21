package com.example.maith.sharinginternetaccess.retrofit.listener

import com.example.maith.sharinginternetaccess.retrofit.model.Product

/**
 * Created by User on 22-Apr-18.
 */
interface FetchDataCallback {
    fun onFetchSuccess(list: ArrayList<Product>)
    fun onFetchFault(e: Exception)
}