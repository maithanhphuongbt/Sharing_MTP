package com.example.maith.sharinginternetaccess.okhttp.geturl

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.maith.sharinginternetaccess.R
import com.example.maith.sharinginternetaccess.okhttp.config.Constant
import kotlinx.android.synthetic.main.fragment_okhttp_get_url.*
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit
import android.content.Context
import okhttp3.CertificatePinner
import java.security.KeyStore
import javax.net.ssl.SSLSocketFactory


@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class GetUrlFragmentByOkhttp : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_okhttp_get_url, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtResult.movementMethod = ScrollingMovementMethod()
        btnGetUrl.setOnClickListener {
            Geturl(txtResult).execute(Constant.SCHEME_URL + edtAddUrl.text.trim())
//            getUrl()
        }
    }

    private fun getUrl() {
        launch {

            var okHttpClient: OkHttpClient? = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()
            val builder: Request.Builder = Request.Builder()

            builder.url(Constant.SCHEME_URL + edtAddUrl.text.trim())

            val request: Request = builder.build()
            val response: Response = okHttpClient?.newCall(request)!!.execute()
            txtResult.text = response.body().toString()
        }

    }

    companion object {
        val TAG = GetUrlFragmentByOkhttp::javaClass.name
    }

    class Geturl(private val textResult: TextView) : AsyncTask<String, String, String>() {
        var okHttpClient: OkHttpClient? = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .certificatePinner(CertificatePinner.Builder()
                        .add("sofavietgiare.top", "sha256/qI0JU7m7A5MR4Qlnv09XZSWWPFEwBfm1asXN1DFDOSo=")
                        .add("sofavietgiare.top", "sha256/YLh1dUR9y6Kja30RrAn7JKnbQG/uEtLMkBgFF2Fuihg=")
                        .add("sofavietgiare.top", "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys=")
                        .build())
                .build()

        override fun doInBackground(vararg params: String): String? {
            val builder: Request.Builder = Request.Builder()

            builder.url(params[0])

            val request: Request = builder.build()
            val response: Response = okHttpClient?.newCall(request)!!.execute()

            return response.body()!!.string()
        }


        override fun onPostExecute(result: String?) {
            if (result != null) {
                textResult.text = result
            }
            super.onPostExecute(result)
        }
    }
}