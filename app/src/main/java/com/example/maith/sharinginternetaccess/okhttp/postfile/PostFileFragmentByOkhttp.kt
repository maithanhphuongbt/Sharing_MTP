package com.example.maith.sharinginternetaccess.okhttp.postfile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.TextView
import com.example.maith.sharinginternetaccess.R
import com.example.maith.sharinginternetaccess.utils.GetRealPathFromUri
import com.example.maith.sharinginternetaccess.utils.GetScreenSize
import com.example.maith.sharinginternetaccess.utils.ResizeBitmap
import kotlinx.android.synthetic.main.fragment_okhttp_post_file.*
import okhttp3.*
import java.io.File
import java.io.InputStream
import android.os.Build
import android.app.Activity
import android.annotation.TargetApi
import com.example.maith.sharinginternetaccess.okhttp.config.Constant
import com.example.maith.sharinginternetaccess.utils.ProgressBar


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
class PostFileFragmentByOkhttp : Fragment() {

    private var path: String? = null
    private val PERMISSIONS_STORAGE = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_okhttp_post_file, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar  = ProgressBar(context!!,Constant.POSTING)
        verifyStoragePermissions(activity!!)
        btnSelectImage.setOnClickListener {  pickImage() }
        btnSendFile.setOnClickListener {
            path?.let { it1 -> PostFile(it1, txtResult).execute(Constant.URL_POST_FILE) }
            progressBar!!.createProgressDialog()
        }
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

    private fun pickImage() {
        startActivityForResult(Intent(Intent.ACTION_PICK).setType(Constant.IMAGE_TYPE), REQUEST_CODE_FOLDER)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            val uri: Uri = data.data
            path = context?.let { GetRealPathFromUri.excute(uri, it) }
            val inputStream: InputStream = context!!.contentResolver.openInputStream(uri)
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            imgFile.setImageBitmap(ResizeBitmap.excute(bitmap,
                    GetScreenSize.getScreenWidth(activity), (GetScreenSize.getScreenWidth(activity) / 2)))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        val TAG = PostFileFragmentByOkhttp::javaClass.name
        const val REQUEST_CODE_FOLDER = 111
    }

    inner class PostFile(private val path: String, private val textView: TextView) : AsyncTask<String, Void, String>() {
        private val okhttp: OkHttpClient = OkHttpClient.Builder().build()

        override fun doInBackground(vararg params: String): String {
            val file = File(path)
            val contentType: String = getType(file.path)
            val filePath: String = file.absolutePath

            val fileBody: RequestBody = RequestBody.create(MediaType.parse(contentType), file)
            val requestBody: RequestBody = MultipartBody.Builder()
                    .addFormDataPart(Constant.UPLOAD_IMAGE, filePath
                            .substring(filePath.lastIndexOf(Constant.SlASH_ICON) + 1), fileBody)
                    .setType(MultipartBody.FORM)
                    .build()
            val request: Request = Request.Builder().url(params[0]).post(requestBody).build()
            val response: Response = okhttp.newCall(request).execute()
            return response.body()!!.string()
        }

        override fun onPostExecute(result: String?) {
            textView.text = result
            progressBar!!.destroyProgressDialog()
            super.onPostExecute(result)
        }

        // return value after "." of file

        private fun getType(path: String?): String {
            val extension: String = MimeTypeMap.getFileExtensionFromUrl(path)
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
    }
}