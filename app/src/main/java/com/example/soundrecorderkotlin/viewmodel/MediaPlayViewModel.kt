package com.example.soundrecorderkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MediaPlayViewModel : ViewModel() {

    var fileName: MutableLiveData<String> = MutableLiveData()
    var startTime: MutableLiveData<String> = MutableLiveData()
    var totalTime: MutableLiveData<String> = MutableLiveData()

    fun onClickFloat() {

    }

    init {
        fileName.value = "tomato.mp4"
        startTime.value = "00:00"
        totalTime.value = "00:00"
    }
}