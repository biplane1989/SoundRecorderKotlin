package com.example.soundrecorderkotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.soundrecorderkotlin.R
import com.example.soundrecorderkotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolbar.title = "orange"
        toolbar.popupTheme = R.style.ThemeOverlay_AppCompat_Light

        var adapter = fragmentManager?.let { MyAdapter(it) }
        pager.adapter = adapter
        tabs.setViewPager(pager)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }

    public class MyAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm) {

        private var titles = arrayOf("RECORD", "SAVED RECORDINGS")


        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    return RecordFragment.newInstance(position)
                }
                1 -> {
                    return FileViewerFragment.newInstance(position)
                }
            }
            return FileViewerFragment.newInstance(position)
        }

        override fun getCount(): Int {
            return titles.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }

    }
}