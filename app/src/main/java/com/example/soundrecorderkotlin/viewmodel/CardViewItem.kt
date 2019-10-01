package com.example.soundrecorderkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soundrecorderkotlin.base.BaseViewModel
import com.example.soundrecorderkotlin.commol.OnClicked

class CardViewItem(var name: String?, var length: String, var time: String) {

//    val _OnClick = MutableLiveData<String>()

    lateinit var onClicked : OnClicked

    fun setOnClick(clicked: OnClicked){
        onClicked = clicked
    }

//    val _OnClick: MutableLiveData<ItemClickListener> by lazy {
//        MutableLiveData<CardViewItem.ItemClickListener>()
//    }

    fun onClickItem() {
//        _OnClick.value = ""
//        Log.d("001", "orange")
        onClicked.onClick()

    }

//    interface ItemClickListener {
//        fun onItemClicked()
//    }
}