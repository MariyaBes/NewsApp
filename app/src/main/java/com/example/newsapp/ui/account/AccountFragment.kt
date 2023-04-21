package com.example.newsapp.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentAccountBinding
import com.example.newsapp.databinding.FragmentDetailsBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}