package com.example.maith.sharinginternetaccess.retrofit.presenter

import android.content.Context
import com.example.maith.sharinginternetaccess.retrofit.adapter.ProductAdapter
import com.example.maith.sharinginternetaccess.retrofit.apihelper.APIService
import com.example.maith.sharinginternetaccess.retrofit.apihelper.RetrofitClient
import com.example.maith.sharinginternetaccess.retrofit.model.Product
import retrofit2.Call
import retrofit2.Response


/**
 * Created by User on 22-Apr-18.
 */
class ProductPresenter(context: Context,
                       private var productsList: ArrayList<Product>,
                       private var productAdapter: ProductAdapter) : BasePresenter(context) {

    fun fetchData() {
        val apiService = RetrofitClient.retrofit.create(APIService::class.java)
        apiService.getAllProduct().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {
//                Log.e(TAG, t.toString())
//                fetchDataCallback.onFetchFault(t as Exception)
            }

            override fun onResponse(call: Call<List<Product>>?, response: Response<List<Product>>?) {
                if (response!!.isSuccessful) {

                } else {

                }
//                fetchDataCallback.onFetchSuccess(Product.getAllProduct(response!!.body()!!.toList()))
            }
        })
//        APIServiceIml().getAllProduct(object : FetchDataCallback {
//            override fun onFetchSuccess(list: ArrayList<Product>) {
////                productsList.clear()
//                productsList.addAll(list)
//                productAdapter.notifyDataSetChanged()
//            }
//
//            override fun onFetchFault(e: Exception) {
//
//            }
//        })
    }
}