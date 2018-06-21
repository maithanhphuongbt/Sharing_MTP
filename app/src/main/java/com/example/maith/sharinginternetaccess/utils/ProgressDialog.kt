package com.example.maith.sharinginternetaccess.utils

import android.app.ProgressDialog
import android.content.Context

class ProgressBar(context: Context, message: String) {
    var mesage: String = message
    val progressDialog: ProgressDialog = ProgressDialog(context)
    fun createProgressDialog() {
        progressDialog.setMessage(mesage)
        progressDialog.setCancelable(false)
        progressDialog.show()
    }
    fun destroyProgressDialog() {
        progressDialog.dismiss()
    }
}