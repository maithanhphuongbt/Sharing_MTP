package com.example.maith.sharinginternetaccess.okhttp.postuser

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.maith.sharinginternetaccess.R
import com.example.maith.sharinginternetaccess.okhttp.config.Constant
import kotlinx.android.synthetic.main.fragment_okhttp_post_user.*
import okhttp3.*

class PostUserFragmentByOkhttp : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_okhttp_post_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPost.setOnClickListener {
            PostUser(edtUserName.text.toString().trim(),
                    edtPassWord.text.toString().trim(), txtResult).execute(Constant.URL_POST_USER)
        }
    }

    companion object {
        val TAG = PostUserFragmentByOkhttp::javaClass.name
    }

    class PostUser(var userName: String, var passWord: String,
                   val txtResult: TextView) : AsyncTask<String, Void, String>() {

        var okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

        override fun doInBackground(vararg params: String): String {
            val requestBody: RequestBody = MultipartBody.Builder()
                    .addFormDataPart(Constant.USER_NAME, userName)
                    .addFormDataPart(Constant.PASSWORD, passWord)
                    .setType(MultipartBody.FORM)
                    .build()

            val request: Request = Request.Builder().url((params[0])).post(requestBody).build()

            val response: Response = okHttpClient.newCall(request).execute()
            return response.body()!!.string()
        }

        override fun onPostExecute(result: String?) {
            txtResult.text = result
            super.onPostExecute(result)
        }

    }
}