package com.example.maith.sharinginternetaccess.navigation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maith.sharinginternetaccess.R
import kotlinx.android.synthetic.main.fragment_navigation.*

class NavigationFragment : Fragment() {

    var navigation: Navigation? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Navigation) {
            navigation = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAndroidDownloadManager.setOnClickListener { navigation?.navigateToAndroidDownloadManager() }
        btnHttpRequest.setOnClickListener { navigation?.navigateToHttpRequest() }
        btnOkHttp.setOnClickListener { navigation?.navigateToOkHttp() }
        btnRetrofit.setOnClickListener { navigation?.navigateToRetrofit() }
        btnSsl.setOnClickListener { navigation?.navigateToSSL() }
        btnSpeechRe.setOnClickListener{navigation?.navigateToSpeech()}
        btnRoomDatabase.setOnClickListener { navigation?.navigateToRoomDatabase() }
    }

    interface Navigation {
        fun navigateToAndroidDownloadManager()
        fun navigateToHttpRequest()
        fun navigateToOkHttp()
        fun navigateToRetrofit()
        fun navigateToSSL()
        fun navigateToSpeech()
        fun navigateToRoomDatabase()
    }

    companion object {
        val TAG = Navigation::class.java.simpleName
        fun newInstance(): NavigationFragment {
            return NavigationFragment()
        }
    }
}