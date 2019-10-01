package com.example.soundrecorderkotlin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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
        loadData()
    }

    override fun onPause() {
        super.onPause()
        Log.d("002", "orange")

    }

    override fun onStop() {
        super.onStop()
        Log.d("002", "orange")
    }

    override fun onStart() {
        super.onStart()
        Log.d("002", "orange")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    fun loadData() {
        doAsync {
            var list = db.recordDAO().getAllRecords()

            var arrRecord = ArrayList<CardViewItem>()

            var index = 0
            for (record in list) {
                var card = CardViewItem(
                    list.get(index).recordingName,
                    list.get(index).filePath,
                    list.get(index).length.toString()
                )
                arrRecord.add(card)
                index++
            }

            uiThread {
                var fileViewerAdapter = FileViewerAdapter(arrRecord, viewLifecycleOwner)
                rv_list_record.layoutManager = LinearLayoutManager(context)
                rv_list_record.setHasFixedSize(true)
                rv_list_record.adapter = fileViewerAdapter
                rv_list_record.adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onClick() {
        Toast.makeText(context, "Orange", Toast.LENGTH_LONG).show()
        Log.d("001", "orange")
    }
}