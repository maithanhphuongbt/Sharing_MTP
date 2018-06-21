package com.example.maith.sharinginternetaccess.utils

import android.graphics.Bitmap

class ResizeBitmap {
    companion object {
        fun excute(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
            val width = bitmap.width
            val height = bitmap.height

            val scaleWidth = newWidth.toFloat() / width
            val scaleHeight = newHeight.toFloat() / height

            val factorToUse = if (scaleHeight > scaleWidth) scaleWidth else scaleHeight

            return Bitmap.createScaledBitmap(bitmap, (width * factorToUse).toInt(), (height * factorToUse).toInt(), false)
        }
    }
}