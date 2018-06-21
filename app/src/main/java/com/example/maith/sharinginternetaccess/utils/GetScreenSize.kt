package com.example.maith.sharinginternetaccess.utils

import android.util.DisplayMetrics
import android.app.Activity



class GetScreenSize {
    companion object {
        /**
         * Gets the screen width.
         *
         * @param activity the activity
         * @return the screen width
         */
        fun getScreenWidth(activity: Activity?): Int {
            val metrics = DisplayMetrics()

            if (activity == null)
                return 0
            activity.windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.widthPixels
        }

        /**
         * Gets the screen height.
         *
         * @param activity the activity
         * @return the screen height
         */
        fun getScreenHeight(activity: Activity): Int {
            val metrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.heightPixels
        }
    }
}