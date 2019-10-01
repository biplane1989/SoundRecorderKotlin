package com.example.soundrecorderkotlin.service

import android.app.Service
import android.content.Intent
import android.media.MediaRecorder
import android.os.Environment
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.soundrecorderkotlin.database.DataSingleton
import com.example.soundrecorderkotlin.database.Record
import org.jetbrains.anko.doAsync
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class RecordingService : Service() {

    private var mediaRecord: MediaRecorder? = null
    private lateinit var time: Timer
    private var timeTask: TimerTask? = null

    private lateinit var db: DataSingleton
    private lateinit var onTimeChangedListener: OnTimerChangedListener

    private lateinit var fileName: String
    private lateinit var filePath: String
    private var startTime: Long = 0
    private var elapsedTime: Long = 0

    companion object {
        private val timeFormat: SimpleDateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    interface OnTimerChangedListener {
        fun onTimerChanged(seconds: Int)
    }

    override fun onCreate() {
        super.onCreate()
        doAsync {
            db = DataSingleton.getInstance(applicationContext)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startRecording()
        return START_STICKY
    }

    override fun onDestroy() {
        if(mediaRecord != null){
            stopRecording()
        }
        super.onDestroy()
    }

    public fun startRecording() {
        setFileNameAndPath()

        mediaRecord = MediaRecorder()
        mediaRecord?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecord?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecord?.setOutputFile(filePath)
        mediaRecord?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecord?.setAudioChannels(1)
        mediaRecord?.setAudioSamplingRate(44100)

        try {
            mediaRecord?.prepare()
            mediaRecord?.start()
            startTime = System.currentTimeMillis()
        } catch (e: IOException) {
            Log.e("001", "Error save file ^_^", e)
        }
    }


    public fun setFileNameAndPath() {

        doAsync {
            var list = db.recordDAO().getAllRecords()
            var count = 0
            var f: File
            do {
                count++
                fileName = "Orange" + "_" + (list.size + count) + ".mp4"
                filePath = Environment.getExternalStorageDirectory().absolutePath
                filePath += "/SoundRecorder/" + fileName

                f = File(filePath)

            } while (f.exists() && !f.isDirectory)
        }

    }

    public fun stopRecording() {
        mediaRecord?.stop()
        elapsedTime = (System.currentTimeMillis() - startTime)
        mediaRecord?.release()

        Toast.makeText(this, "save file" + filePath, Toast.LENGTH_LONG).show()

        if (timeTask != null) {
            timeTask?.cancel()
            timeTask = null
        }
        mediaRecord = null

        doAsync {
            try {
                var record = Record(
                    id = null,
                    recordingName = fileName,
                    filePath = filePath,
                    length = elapsedTime,
                    timeAdded = System.currentTimeMillis()
                )
                db.recordDAO().insert(record)
            } catch (e: IOException) {
                Log.e("001", "Error save file ^_^", e)
            }
        }
    }
}