package com.example.soundrecorderkotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Chronometer
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager.widget.ViewPager
import com.astuetz.PagerSlidingTabStrip
import com.example.soundrecorderkotlin.R
import com.example.soundrecorderkotlin.base.BaseFragment
import com.example.soundrecorderkotlin.database.DataSingleton
import com.example.soundrecorderkotlin.databinding.FragmentRecordBinding
import com.example.soundrecorderkotlin.service.RecordingService
import com.example.soundrecorderkotlin.viewmodel.RecordViewModel
import kotlinx.android.synthetic.main.fragment_record.*
import kotlinx.android.synthetic.main.fragment_record.view.*
import org.jetbrains.anko.doAsync
import java.io.File

class RecordFragment : BaseFragment() {

    lateinit var recordViewModel: RecordViewModel
    lateinit var recodeBinding: FragmentRecordBinding

    private var start: Boolean = true
    private var pause: Boolean = true

    private var recordPromptCount = 0

    var timeWhenPause: Int = 0


    companion object {
        public fun newInstance(position: Int): RecordFragment {
            var recordFragment = RecordFragment();
            return recordFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel::class.java)

        recodeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_record, container, false)
        recodeBinding.lifecycleOwner = this
        recodeBinding.recordViewModel = recordViewModel

        // btnPause.visibility = View.GONE

        return recodeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        recordViewModel._floatClick.observe(recodeBinding.lifecycleOwner!!, Observer {
            Toast.makeText(context, "orange", Toast.LENGTH_LONG).show()
            Log.d("001", "orange")
            onRecord(start)
            start = !start

        })

//        recordViewModel._pauseClick.observe(recodeBinding.lifecycleOwner!!, Observer {
//            onPauseRecord(pause)
//            pause = !pause
//        })
    }

    private fun onRecord(start: Boolean) {

        var intent = Intent(context, RecordingService::class.java)

        if (start) {
            btnRecord.setImageResource(R.drawable.ic_media_stop)
            Toast.makeText(activity, R.string.toast_recording_start, Toast.LENGTH_SHORT).show()

            var folder = File(Environment.getExternalStorageDirectory().path + "/SoundRecorder/")
            if (!folder.exists()) {
                folder.mkdir()
            }

            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
            chronometer.setOnChronometerTickListener { chronometer ->
                if (recordPromptCount == 0) {
                    recording_status_text.text = "."
                } else if (recordPromptCount == 1) {
                    recording_status_text.text = ".."
                } else if (recordPromptCount == 2) {
                    recording_status_text.text = "..."
                    recordPromptCount = -1
                }
                recordPromptCount++
            }

            doAsync {
                activity?.startService(intent)
            }
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            recording_status_text.text = "."
            recordPromptCount++


        } else {
            btnRecord.setImageResource(R.drawable.ic_mic_white_36dp)

            chronometer.stop()
            chronometer.base = SystemClock.elapsedRealtime()
            timeWhenPause = 0
            recording_status_text.text = "tomato"

            doAsync {
                activity?.stopService(intent)
            }
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        }
    }

    private fun onPauseRecord(pause: Boolean) {
        if (pause) {

        }
    }


}