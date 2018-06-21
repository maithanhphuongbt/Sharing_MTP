package com.example.maith.sharinginternetaccess.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.example.maith.sharinginternetaccess.R

class DialogHelper(var context: Context) {

    fun showTutorialDialog(totorialContent: String) {
        val dialog = AlertDialog.Builder(context).create()
        val dialogView = LayoutInflater
                .from(context)
                .inflate(R.layout.tutorial_layout, null)
        val btnColse = dialogView.findViewById<ImageView>(R.id.btnCloseTutorial)
        btnColse.setOnClickListener {
            dialog.dismiss()
        }
        val content = dialogView.findViewById<TextView>(R.id.txtWrappingTutorialContent)
        content.movementMethod = ScrollingMovementMethod()
        content.text = totorialContent
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.show()
    }

}