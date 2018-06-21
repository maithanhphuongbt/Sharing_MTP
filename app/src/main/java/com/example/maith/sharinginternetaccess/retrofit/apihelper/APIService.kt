package com.example.maith.sharinginternetaccess.retrofit.apihelper

import com.example.maith.sharinginternetaccess.retrofit.config.Constant
import com.example.maith.sharinginternetaccess.retrofit.model.Product
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by User on 22-Apr-18.
 */
interface APIService {

    @GET(Constant.GET_ALL_PRODUCT)
    fun getAllProduct(): Call<List<Product>>
}