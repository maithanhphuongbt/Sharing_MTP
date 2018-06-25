package com.example.maith.sharinginternetaccess

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.maith.sharinginternetaccess.androiddownloadmanager.AndroidDownLoadManagerFragment
import com.example.maith.sharinginternetaccess.animation.sprite.AnimationFragment
import com.example.maith.sharinginternetaccess.httprequest.HttpRequestFragment
import com.example.maith.sharinginternetaccess.navigation.NavigationFragment
import com.example.maith.sharinginternetaccess.okhttp.OkHttpFragment
import com.example.maith.sharinginternetaccess.retrofit.RetrofitFragment
import com.example.maith.sharinginternetaccess.roomdatabase.RoomFragment
import com.example.maith.sharinginternetaccess.speech.SpeechFragment
import com.example.maith.sharinginternetaccess.ssl.SSLFragment

class MainActivity : AppCompatActivity(), NavigationFragment.Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!isExisted()) {
            addMainNavigation()
        }
    }

    private fun addMainNavigation() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, NavigationFragment.newInstance(),
                NavigationFragment.TAG)
        transaction.commit()
    }

    private fun isExisted(): Boolean {
        return supportFragmentManager.findFragmentById(R.id.container) != null
    }

    override fun navigateToAndroidDownloadManager() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, AndroidDownLoadManagerFragment(),
                AndroidDownLoadManagerFragment.TAG)
        transaction.addToBackStack(AndroidDownLoadManagerFragment.TAG)
        transaction.commit()
    }

    override fun navigateToHttpRequest() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, HttpRequestFragment(), HttpRequestFragment.TAG)
        transaction.addToBackStack(HttpRequestFragment.TAG)
        transaction.commit()
    }

    override fun navigateToOkHttp() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, OkHttpFragment(), OkHttpFragment.TAG)
        transaction.addToBackStack(OkHttpFragment.TAG)
        transaction.commit()
    }

    override fun navigateToRetrofit() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, RetrofitFragment(), RetrofitFragment.TAG)
        transaction.addToBackStack(RetrofitFragment.TAG)
        transaction.commit()
    }

    override fun navigateToSpeech() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, SpeechFragment(), SpeechFragment.TAG)
        transaction.addToBackStack(SpeechFragment.TAG)
        transaction.commit()
    }

    override fun navigateToSSL() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, SSLFragment(), SSLFragment.TAG)
        transaction.addToBackStack(SSLFragment.TAG)
        transaction.commit()
    }

    override fun navigateToRoomDatabase() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, RoomFragment(), RoomFragment.TAG)
        transaction.addToBackStack(RoomFragment.TAG)
        transaction.commit()
    }

    override fun navigateToAnimation() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, AnimationFragment(), AnimationFragment.TAG)
        transaction.addToBackStack(AnimationFragment.TAG)
        transaction.commit()
    }
}
