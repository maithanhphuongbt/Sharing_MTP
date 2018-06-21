package com.example.maith.sharinginternetaccess.retrofit.apihelper

import android.util.Log
import com.example.maith.sharinginternetaccess.retrofit.listener.FetchDataCallback
import com.example.maith.sharinginternetaccess.retrofit.model.Product
import retrofit2.Call
import retrofit2.Response


/**
 * Created by User on 22-Apr-18.
 */
class APIServiceIml {

    fun getAllProduct(fetchDataCallback: FetchDataCallback) {
        val apiService = RetrofitClient.retrofit.create(APIService::class.java)
        apiService.getAllProduct().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {
                Log.e(TAG, t.toString())
                fetchDataCallback.onFetchFault(t as Exception)
            }

            override fun onResponse(call: Call<List<Product>>?, response: Response<List<Product>>?) {
                fetchDataCallback.onFetchSuccess(Product.getAllProduct(response!!.body()!!.toList()))
            }
        })
    }

    companion object {
        val TAG = APIServiceIml::javaClass.name
    }
}