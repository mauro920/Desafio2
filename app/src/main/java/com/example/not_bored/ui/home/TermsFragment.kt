package com.example.not_bored.ui.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.View
import com.example.not_bored.R
import com.example.not_bored.databinding.FragmentTermsBinding

class TermsFragment : Fragment(R.layout.fragment_terms) {

    private lateinit var binding: FragmentTermsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTermsBinding.bind(view)

        with(binding){
            btnBack.setOnClickListener{ activity?.onBackPressed()}
            tvTermsAndConditions.movementMethod = ScrollingMovementMethod()
        }
    }
}