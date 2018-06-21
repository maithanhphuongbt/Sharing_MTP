package com.example.maith.sharinginternetaccess.androiddownloadmanager

import android.annotation.TargetApi
import android.app.Activity
import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maith.sharinginternetaccess.R
import kotlinx.android.synthetic.main.fragment_android_download_manager.*
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.support.v4.app.ActivityCompat
import com.example.maith.sharinginternetaccess.androiddownloadmanager.cofig.Constant
import java.io.File


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
class AndroidDownLoadManagerFragment : Fragment() {

    private val PERMISSIONS_STORAGE = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var downloadManager: DownloadManager? = null
    private var lastDownload: Long = -1L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_android_download_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // request permission
        verifyStoragePermissions(activity!!)

        downloadManager = activity!!.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?

        activity!!.registerReceiver(onComplete,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        activity!!.registerReceiver(onNotificationClick,
                IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED))

        btnDownload.setOnClickListener {
            startDownload(it)
        }

        btnViewLog.setOnClickListener {
            startActivity(Intent(DownloadManager.ACTION_VIEW_DOWNLOADS))
        }
    }

    fun startDownload(view: View) {
        val uri = Uri.parse(Constant.URL_IMAGE)
        lastDownload = downloadManager!!.enqueue(DownloadManager.Request(uri)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(Constant.IMAGE_NAME)
                .setDescription(Constant.IMAGE_DESCRIPTION)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Constant.IMAGE_NAME))
        view.isEnabled = false
        imgDownload.setImageBitmap(null)
        queryStatus()
    }

    fun queryStatus() {
        val cursor = downloadManager!!.query(DownloadManager.Query().setFilterById(lastDownload))
        txtStatus.visibility = View.VISIBLE

        if (cursor == null) {
            txtStatus.text = Constant.DOWNLOAD_NOT_FOUND
        } else {
            cursor.moveToFirst()
            txtStatus.text = statusMessage(cursor)
        }
    }

    private fun statusMessage(c: Cursor): String? {

        return when (c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
            DownloadManager.STATUS_FAILED -> Constant.DOWNLOAD_FAIL

            DownloadManager.STATUS_PAUSED -> Constant.DOWNLOAD_PAUSED

            DownloadManager.STATUS_PENDING -> Constant.DOWNLOAD_PENDING

            DownloadManager.STATUS_RUNNING -> Constant.DOWNLOAD_RUNNING

            DownloadManager.STATUS_SUCCESSFUL -> Constant.DOWNLOAD_COMPLETE

            else -> Constant.DOWNLOAD_IN_SIGHT
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            Constant.REQUEST_EXTERNAL_STORAGE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    activity!!.supportFragmentManager.popBackStack()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun verifyStoragePermissions(activity: Activity) {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            requestPermissions(
                    PERMISSIONS_STORAGE,
                    Constant.REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    var onComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            btnDownload.isEnabled = true
            txtStatus.visibility = View.GONE
            setImage()
        }
    }

    var onNotificationClick: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            Toast.makeText(ctxt, Constant.DOWNLOAD_NOT_BOTHER, Toast.LENGTH_LONG).show()
        }
    }

    private fun setImage() {
        val imageFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constant.IMAGE_NAME)
        if (imageFile.exists()) {
            imgDownload.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))
        }
    }

    companion object {
        val TAG: String = AndroidDownLoadManagerFragment::class.java.simpleName
    }
}