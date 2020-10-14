package com.newsuper.code.gank.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newsuper.code.R
import com.newsuper.code.gank.util.spanClick

import kotlinx.android.synthetic.main.fragment_about.*


class GankAboutFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_about_app.spanClick {
            if (context == null)
                return@spanClick
            GankWebActivity.start(context!!, it)
        }
        tv_open_source.spanClick {
            if (context == null)
                return@spanClick
            GankWebActivity.start(context!!, it)
        }
    }

    companion object {

        const val TAG = "AboutFragment"

        @JvmStatic
        fun newInstance() = GankAboutFragment()
    }
}
