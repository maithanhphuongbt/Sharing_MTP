package com.example.maith.sharinginternetaccess.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.maith.sharinginternetaccess.R

class OpenFragment {
    companion object {
        fun excute(fragment: Fragment, tag: String, activity: FragmentActivity) {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment,
                    tag)?.addToBackStack(tag)?.commit()
        }
    }
}