package com.example.soundrecorderkotlin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.astuetz.PagerSlidingTabStrip
import com.example.soundrecorderkotlin.R
import com.example.soundrecorderkotlin.base.BaseFragment
import com.example.soundrecorderkotlin.databinding.FragmentRecordBinding
import com.example.soundrecorderkotlin.viewmodel.RecordViewModel
import kotlinx.android.synthetic.main.fragment_record.view.*

class RecordFragment : BaseFragment() {

    lateinit var recordViewModel: RecordViewModel
    lateinit var recodeBinding: FragmentRecordBinding

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

        return recodeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recordViewModel._floatClick.observe(recodeBinding.lifecycleOwner!!, Observer {
            Toast.makeText(context, "orange", Toast.LENGTH_LONG).show()
            Log.d("001", "orange")
        })
    }


}