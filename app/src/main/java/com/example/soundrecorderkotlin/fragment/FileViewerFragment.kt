package com.example.soundrecorderkotlin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soundrecorderkotlin.R
import com.example.soundrecorderkotlin.adapter.FileViewerAdapter
import com.example.soundrecorderkotlin.base.BaseFragment
import com.example.soundrecorderkotlin.commol.OnClicked
import com.example.soundrecorderkotlin.database.DataSingleton
import com.example.soundrecorderkotlin.databinding.FragmentFileViewerBinding
import com.example.soundrecorderkotlin.viewmodel.CardViewItem
import com.example.soundrecorderkotlin.viewmodel.FileViewerViewModel
import kotlinx.android.synthetic.main.fragment_file_viewer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FileViewerFragment : BaseFragment(), OnClicked {

    lateinit var db: DataSingleton
    lateinit var fileViewerViewModel: FileViewerViewModel
    lateinit var fragmentFileViewerBinding: FragmentFileViewerBinding

    companion object {
        public fun newInstance(position: Int): FileViewerFragment {
            var fileViewerFragment = FileViewerFragment()
            return fileViewerFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fileViewerViewModel = ViewModelProviders.of(this).get(FileViewerViewModel::class.java)

        fragmentFileViewerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_file_viewer, container, false)
        fragmentFileViewerBinding.lifecycleOwner = this
        fragmentFileViewerBinding.fileViewerViewModel = fileViewerViewModel

        db = DataSingleton.getInstance(context!!.applicationContext)

        return fragmentFileViewerBinding.root
    }

    override fun onResume() {
        super.onResume()
        var arrRecord = ArrayList<CardViewItem>()
        CoroutineScope(Dispatchers.Default).launch {
            arrRecord = loadData()
            loadView(arrRecord)

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var arrRecord = ArrayList<CardViewItem>()
        CoroutineScope(Dispatchers.Default).launch {
            arrRecord = loadData()
            loadView(arrRecord)
        }
    }

    suspend fun loadData(): ArrayList<CardViewItem> {

        val list = doWord(db).await()
        var arrRecord = ArrayList<CardViewItem>()

        var index = 0

        for (record in list) {
            var card = CardViewItem(
                list.get(index).recordingName,
                list.get(index).filePath,
                list.get(index).length.toString()
            )
            card.setOnClick(this)
            arrRecord.add(card)
            index++
        }
        return arrRecord
    }

    fun loadView(arrRecord: ArrayList<CardViewItem>) {
        var fileViewerAdapter = FileViewerAdapter(arrRecord, viewLifecycleOwner)
        rv_list_record.layoutManager = LinearLayoutManager(context)
        rv_list_record.setHasFixedSize(true)
        rv_list_record.adapter = fileViewerAdapter
        rv_list_record.adapter!!.notifyDataSetChanged()
    }

    fun doWord(db: DataSingleton) = CoroutineScope(Dispatchers.Default).async {
        var list = db.recordDAO().getAllRecords()
        return@async list
    }

    override fun onClick() {
        Toast.makeText(context, "Orange", Toast.LENGTH_LONG).show()
        Log.d("001", "orange")
        val directions = MainFragmentDirections.acctionMainToMediaplay()
        NavHostFragment.findNavController(this).navigate(directions)

//        findNavController().navigate(R.id.acction_fileviewwr_to_mediaplay)
    }
}