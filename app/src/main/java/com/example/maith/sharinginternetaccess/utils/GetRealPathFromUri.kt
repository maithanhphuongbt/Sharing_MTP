package com.example.maith.sharinginternetaccess.utils

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class GetRealPathFromUri {
    companion object {
        @SuppressLint("Recycle")
        fun excute(uri: Uri, context: Context): String? {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor = context.contentResolver.
                    query(uri, proj, null, null, null)
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        }

    }
}