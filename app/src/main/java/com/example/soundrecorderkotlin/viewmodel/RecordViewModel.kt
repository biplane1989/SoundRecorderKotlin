package com.example.soundrecorderkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.soundrecorderkotlin.R
import com.example.soundrecorderkotlin.base.BaseViewModel

class RecordViewModel : BaseViewModel() {

    var _floatClick = MutableLiveData<String>()
    var _pauseClick = MutableLiveData<String>()

    var textRecord: MutableLiveData<String> = MutableLiveData()

    init {
        textRecord.value = "orange"
    }

    fun onFloatClick() {
        _floatClick.value = ""
    }

    fun onPauseClick() {
        _pauseClick.value = ""
    }
}