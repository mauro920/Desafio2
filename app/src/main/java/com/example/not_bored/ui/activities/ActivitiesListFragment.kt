package com.example.not_bored.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.not_bored.R
import com.example.not_bored.databinding.FragmentActivitiesListBinding
import com.example.not_bored.models.ActivityModel
import com.example.not_bored.ui.activities.adapters.ActivityAdapter
import com.example.not_bored.viewmodel.ActivityViewModel
import java.util.*
import androidx.navigation.fragment.findNavController

class ActivitiesListFragment : Fragment(R.layout.fragment_activities_list),
    ActivityAdapter.OnActivityCLickListener {
    private lateinit var binding: FragmentActivitiesListBinding
    private val activityViewModel: ActivityViewModel by activityViewModels()
    private lateinit var participants: String
    private lateinit var activities: List<ActivityModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        participants = activityViewModel.getParticipants()
        activities = activityViewModel.getActivitiesList(participants)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivitiesListBinding.bind(view)
        initUI()
    }

    private fun initUI() {
        binding.btnRandom.setOnClickListener {
            goActivityDetails(activityViewModel.getRandom(activities), true)
        }
        setRecycler()
    }

    private fun setRecycler() {
        if (activities.isNotEmpty()){
            with(binding.rvActivities) {
                layoutManager = LinearLayoutManager(context)
                adapter = ActivityAdapter(activities, this@ActivitiesListFragment)
            }
        } else {
            binding.noParts.visibility = View.VISIBLE
        }

    }

    override fun onActivityClick(activity: ActivityModel) {
        goActivityDetails(activity, false)
    }

    private fun goActivityDetails(activity: ActivityModel, random: Boolean) {
        val action =
            ActivitiesListFragmentDirections.actionActivitiesListFragmentToActivityDetailsFragment(
                activity.activityName,
                activity.participants,
                activity.price,
                activity.category,
                random
            )
        val actionRandom =
            ActivitiesListFragmentDirections.actionActivitiesListFragmentToActivityDetailsFragment(
                activity.activityName,
                activity.participants,
                activity.price,
                activity.category,
                random
            )
        if (random) {
            findNavController().navigate(actionRandom)
        } else findNavController().navigate(action)
    }

}