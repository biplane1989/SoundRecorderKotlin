package com.example.soundrecorderkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.soundrecorderkotlin.R
import com.example.soundrecorderkotlin.commol.OnListenerData
import com.example.soundrecorderkotlin.database.DataSingleton
import com.example.soundrecorderkotlin.databinding.CardViewBinding
import com.example.soundrecorderkotlin.generated.callback.OnClickListener
import com.example.soundrecorderkotlin.viewmodel.CardViewItem
import com.example.soundrecorderkotlin.viewmodel.FileViewerViewModel

class FileViewerAdapter(var list: ArrayList<CardViewItem>, val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<FileViewerAdapter.ViewHolder>(), OnListenerData {

    lateinit var context: Context
    lateinit var db: DataSingleton

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: CardViewBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.card_view, parent, false)
        binding.lifecycleOwner = lifecycleOwner
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setBingding(list.get(position))


    }

    inner class ViewHolder(var binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setBingding(data: CardViewItem) {
            binding.cardViewItem = data
        }
    }

    override fun onListener() { // change data list
        notifyItemInserted(itemCount - 1)
    }
}