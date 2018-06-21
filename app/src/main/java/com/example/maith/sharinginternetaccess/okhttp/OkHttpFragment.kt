package com.example.maith.sharinginternetaccess.okhttp

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maith.sharinginternetaccess.R
import com.example.maith.sharinginternetaccess.okhttp.getimage.GetImageFragmentByOkhttp
import com.example.maith.sharinginternetaccess.okhttp.geturl.GetUrlFragmentByOkhttp
import com.example.maith.sharinginternetaccess.okhttp.postfile.PostFileFragmentByOkhttp
import com.example.maith.sharinginternetaccess.okhttp.postuser.PostUserFragmentByOkhttp
import com.example.maith.sharinginternetaccess.utils.OpenFragment
import kotlinx.android.synthetic.main.fragment_okhttp.*

class OkHttpFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_okhttp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnGetUrl.setOnClickListener(this)
        btnGetImage.setOnClickListener(this)
        btnPostUser.setOnClickListener(this)
        btnPostFile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnGetUrl -> {
                OpenFragment.excute(GetUrlFragmentByOkhttp(),
                        GetUrlFragmentByOkhttp.TAG, requireActivity())
            }
            R.id.btnGetImage -> {
                OpenFragment.excute(GetImageFragmentByOkhttp(),
                        GetImageFragmentByOkhttp.TAG, requireActivity())
            }
            R.id.btnPostUser -> {
                OpenFragment.excute(PostUserFragmentByOkhttp(),
                        PostUserFragmentByOkhttp.TAG, requireActivity())
            }
            R.id.btnPostFile -> {
                OpenFragment.excute(PostFileFragmentByOkhttp(),
                        PostFileFragmentByOkhttp.TAG, requireActivity())
            }
        }
    }

    companion object {
        val TAG: String = OkHttpFragment::class.java.simpleName
    }

}