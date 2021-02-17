package com.gsanthosh91.app.ui.main

import android.view.View
import com.gsanthosh91.app.R
import com.gsanthosh91.app.base.BaseFragment
import com.gsanthosh91.app.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View?) {
        binding = FragmentHomeBinding.bind(view!!)


    }

}