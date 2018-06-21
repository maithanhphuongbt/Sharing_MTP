package com.example.maith.sharinginternetaccess.okhttp.getimage

import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.maith.sharinginternetaccess.R
import com.example.maith.sharinginternetaccess.okhttp.config.Constant
import kotlinx.android.synthetic.main.fragment_okhttp_get_image.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class GetImageFragmentByOkhttp : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_okhttp_get_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnGetImage.setOnClickListener {
            GetImage(imageForOkhttpGet).execute(Constant.URL_GET_IMAGE)
        }
    }

    companion object {
        val TAG = GetImageFragmentByOkhttp::javaClass.name
    }

    class GetImage(private val image: ImageView) : AsyncTask<String, Void, ByteArray>() {

        var okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

        override fun doInBackground(vararg params: String): ByteArray {
            val builder: Request.Builder = Request.Builder()

            builder.url(params[0])

            val request: Request = builder.build()
            val response: Response = okHttpClient.newCall(request)!!.execute()
            return response.body()!!.bytes()
        }

        override fun onPostExecute(result: ByteArray?) {
            if (result!!.isNotEmpty()) {
                image.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.size))
            }
            super.onPostExecute(result)
        }
    }
}