package com.example.not_bored.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.not_bored.R
import com.example.not_bored.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        with(binding) {
            btnStart.setOnClickListener {
                val result = etParticipants.text.toString()
                setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                findNavController().navigate(R.id.action_homeFragment_to_activitiesListFragment)
            }
            tvConditions.setOnClickListener { goToTerms() }
            etParticipants.doAfterTextChanged { afterTextChanged() }
        }
    }

    private fun goToTerms() {
        findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
    }

    private fun afterTextChanged() {
        val text = binding.etParticipants.text.toString()
        try {
            if (text.toInt() > 0 && text.isNotEmpty()) {
                binding.btnStart.isEnabled = true
            } else Toast.makeText(this.activity, "Only Positive numbers", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this.activity, "Only Positive numbers", Toast.LENGTH_SHORT).show()
        }
    }

}