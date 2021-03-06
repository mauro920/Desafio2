package com.example.not_bored.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.not_bored.R
import com.example.not_bored.databinding.FragmentActivityDetailsBinding
import com.example.not_bored.models.ActivityModel
import com.example.not_bored.viewmodel.ActivityViewModel

class ActivityDetailsFragment : Fragment(R.layout.fragment_activity_details) {
    private lateinit var binding: FragmentActivityDetailsBinding
    private val activityViewModel: ActivityViewModel by activityViewModels()
    private lateinit var participants: String
    private val args: ActivityDetailsFragmentArgs by navArgs()
    private lateinit var activities: List<ActivityModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        participants = activityViewModel.getParticipants()
        activities = activityViewModel.getActivitiesList(participants)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivityDetailsBinding.bind(view)

        initUI()
    }


    private fun initUI() {
        binding.btnTry.setOnClickListener {
            goRandom()
        }
        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
        getAndShowArgs()
    }

    private fun getAndShowArgs() {
        val price = args.price
        val category = args.category
        val activityName = args.activityName
        val participants = args.participants
        val random = args.random
        setUpArgs(category, activityName, price, participants, random)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpArgs(
        category: String,
        activityName: String,
        price: Float,
        participants: String,
        random: Boolean
    ) {
        with(binding) {
            if (random) {
                tvTitleActivity.text = "Random"
                tvRandom.isVisible = true
                tvRandom.text = category
                tvParticipant.text = participants
                tvInfo.text = activityName
                tvPrice.text = getPrice(price)
            } else {
                tvTitleActivity.text = category
                tvParticipant.text = participants
                tvInfo.text = activityName
                tvPrice.text = getPrice(price)
            }
        }
    }

    private fun getPrice(price: Float): String {
        return when (price) {
            0.0f -> "Free"
            in 0.0f..0.3f -> "Low"
            in 0.3f..0.6f -> "Medium"
            in 0.6f..1.0f -> "High"
            else -> ""
        }
    }

    private fun goRandom() {
        val randomAct = activityViewModel.getRandom(activities)
        val action = ActivityDetailsFragmentDirections.actionActivityDetailsFragmentSelf(
            randomAct.activityName,
            randomAct.participants,
            randomAct.price,
            randomAct.category,
            true
        )
        findNavController().navigate(action)
        getAndShowArgs()
    }
}