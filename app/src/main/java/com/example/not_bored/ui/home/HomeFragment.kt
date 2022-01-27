package com.example.not_bored.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.not_bored.R
import com.example.not_bored.databinding.FragmentHomeBinding
import com.example.not_bored.viewmodel.ActivityViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_prefs),
            Context.MODE_PRIVATE
        )
        if (sharedPreferences != null) {
            viewModel.setSharedPreferences(sharedPreferences)
        }

        with(binding) {
            btnStart.setOnClickListener {
                val result = etParticipants.text.toString()
                viewModel.addParticipants(result)
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