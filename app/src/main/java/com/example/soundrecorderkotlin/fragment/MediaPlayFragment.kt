package com.example.soundrecorderkotlin.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.soundrecorderkotlin.R
import com.example.soundrecorderkotlin.base.BaseFragment
import com.example.soundrecorderkotlin.databinding.FragmentMediaPlaybackBinding
import com.example.soundrecorderkotlin.viewmodel.MediaPlayViewModel
import kotlinx.android.synthetic.main.fragment_media_playback.*

class MediaPlayFragment : BaseFragment() {

    lateinit var mediaPlaybackBinding: FragmentMediaPlaybackBinding
    lateinit var mediaPlayViewModel: MediaPlayViewModel

    var minutes: Long = 0
    var seconds: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mediaPlayViewModel = ViewModelProviders.of(this).get(MediaPlayViewModel::class.java)
        mediaPlaybackBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_media_playback,
            container,
            false
        )
        mediaPlaybackBinding.lifecycleOwner = this
        mediaPlaybackBinding.mediaPlayViewModel = mediaPlayViewModel

        return mediaPlaybackBinding.root
    }

//    @SuppressLint("ResourceAsColor")
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//
//        var myDialog: Dialog = super.onCreateDialog(savedInstanceState)
//        var builder: AlertDialog.Builder = AlertDialog.Builder(activity)
//        var view: View = activity!!.layoutInflater.inflate(R.layout.fragment_media_playback, null)
//
//        var filter: ColorFilter = LightingColorFilter(R.color.primary, R.color.primary)
//        seekbar.progressDrawable.colorFilter = filter
//        seekbar.thumb.colorFilter = filter
//
//        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onStopTrackingTouch(p0: SeekBar?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        })
//
//        builder.setView(view)
//        myDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
//        return builder.create()
//    }
}