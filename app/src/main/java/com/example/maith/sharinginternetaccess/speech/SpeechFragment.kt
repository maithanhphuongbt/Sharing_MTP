package com.example.maith.sharinginternetaccess.speech

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.SpeechRecognizer
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maith.sharinginternetaccess.R
import com.example.maith.speechtextdemo.speech.RecognitionCallback
import com.example.maith.speechtextdemo.speech.RecognitionStatus
import com.example.maith.speechtextdemo.speech.SpeechUtil
import kotlinx.android.synthetic.main.fragment_speech.*

class SpeechFragment : Fragment(), RecognitionCallback{

    private var listResult = ArrayList<String>()
    private var isRecore: Boolean = false
    private val recognitionManager: SpeechUtil by lazy {
        SpeechUtil(context!!, callback = this)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speech,container,false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtSpeechResult.movementMethod = ScrollingMovementMethod()
        verifyStoragePermissions(activity!!)
        btnRecord.setOnClickListener{
            if (!isRecore) {
                startRecognition()
                listResult.clear()
                btnRecord.setImageResource(R.drawable.ico_mic_on)
                isRecore = true
                txtSpeechResult.text = ""
            }else {
                stopRecognition()
                btnRecord.setImageResource(R.drawable.ico_mic_off)
                for (result in listResult) {
                    txtSpeechResult.text = txtSpeechResult.text.toString()+ result
                }
                isRecore = false
            }
        }
    }

    private fun startRecognition() {
        recognitionManager.startRecognition()
    }

    private fun stopRecognition() {
        recognitionManager.stopRecognition()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
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
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    companion object {
        val TAG = SpeechFragment::javaClass.name
        val REQUEST_EXTERNAL_STORAGE = 123
    }

    override fun onPrepared(status: RecognitionStatus) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onKeywordDetected() {
    }

    override fun onReadyForSpeech(params: Bundle) {
    }

    override fun onBufferReceived(buffer: ByteArray) {
    }

    override fun onRmsChanged(rmsdB: Float) {
    }

    override fun onPartialResults(results: List<String>) {
    }

    override fun onResults(results: List<String>, scores: FloatArray?) {
        val text = results.joinToString(separator = "\n")
        Log.i(TAG,"onResults : $text")
        listResult.add(text)
    }

    private fun getErrorText(errorCode: Int): String = when (errorCode) {
        SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
        SpeechRecognizer.ERROR_CLIENT -> "Client side error"
        SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
        SpeechRecognizer.ERROR_NETWORK -> "Network error"
        SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
        SpeechRecognizer.ERROR_NO_MATCH -> "No match"
        SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
        SpeechRecognizer.ERROR_SERVER -> "Error from server"
        SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
        else -> "Didn't understand, please try again."
    }

    override fun onError(errorCode: Int) {
        val errorMessage = getErrorText(errorCode)
        Log.i("Recognition","onError: $errorMessage")
    }

    override fun onEvent(eventType: Int, params: Bundle) {
    }

    override fun onEndOfSpeech() {
    }

}