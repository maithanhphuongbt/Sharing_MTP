@file:Suppress("SENSELESS_COMPARISON")

package com.example.maith.sharinginternetaccess.httprequest

import android.os.AsyncTask
import android.os.Bundle
import android.provider.Contacts
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.maith.sharinginternetaccess.R
import com.example.maith.sharinginternetaccess.httprequest.config.Constant
import kotlinx.android.synthetic.main.fragment_httprequest.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class HttpRequestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_httprequest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        doSomeThing()
//        Geturl(txtResult).execute(Constant.URL_PATH)
        request()
    }


    fun request() {
        launch(UI) {
            var text = httRequest2().await()
            setText(text)
        }
    }

    private fun httRequest2(): Deferred<String> {
        return async(CommonPool) {
            val connection = URL(Constant.URL_PATH).openConnection() as HttpURLConnection
            connection.inputStream.bufferedReader().readText()
        }
    }

    private fun setText(data: String) {
        txtResult.text = data

    }

    private fun doSomeThing() {
        Log.d(TAG, "Start")

        // Start a coroutine

        launch {
            delay(1000)
            Log.d(TAG, "Hello launch")

        }

//        Thread.sleep(2000) // wait for 2 seconds
        Log.d(TAG, "Stop")

    }

    class Geturl(private val textResult: TextView) : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String): String? {

            val connection = URL(params[0]).openConnection() as HttpURLConnection
            return connection.inputStream.bufferedReader().readText()
        }

        override fun onPostExecute(result: String?) {
            if (result != null) {
                textResult.text = result
            }
            super.onPostExecute(result)
        }
    }

    companion object {
        val TAG: String = HttpRequestFragment::class.java.simpleName
    }
}