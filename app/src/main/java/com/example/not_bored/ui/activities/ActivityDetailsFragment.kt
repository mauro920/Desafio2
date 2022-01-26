package com.example.not_bored.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.not_bored.R
import com.example.not_bored.databinding.FragmentActivityDetailsBinding
import com.example.not_bored.models.ActivityModel
import com.example.not_bored.utils.ActivitiesList
import com.example.not_bored.viewmodel.ActivityViewModel

class ActivityDetailsFragment : Fragment(R.layout.fragment_activity_details) {
    private lateinit var binding: FragmentActivityDetailsBinding
    private val activityViewModel by viewModels<ActivityViewModel>()
    private lateinit var participants: String
    private val args: ActivityDetailsFragmentArgs by navArgs()
    private var activities: List<ActivityModel> = ActivitiesList.activitiesList.filter { it.participants == participants }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivityDetailsBinding.bind(view)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            participants = result!!
        }
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
        setUpArgs(
            randomAct.category,
            randomAct.activityName,
            randomAct.price,
            randomAct.participants,
            true
        )
    }
}