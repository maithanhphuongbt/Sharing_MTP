package com.example.maith.sharinginternetaccess.retrofit.apihelper

import com.example.maith.sharinginternetaccess.retrofit.config.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by User on 22-Apr-18.
 */
class RetrofitClient {

    companion object {
        var retrofit = Retrofit.Builder()
                .baseUrl(Constant.URL_PRODUCT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()!!
    }
}