package com.example.not_bored.ui.activities

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
    private val activityViewModel by viewModels<ActivityViewModel>()
    private lateinit var participants: String
    private var activities: List<ActivityModel> = activityViewModel.getActivitiesList(participants)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivitiesListBinding.bind(view)
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            participants = result!!
        }
        initUI()
    }

    private fun initUI() {
        binding.btnRandom.setOnClickListener {
            goActivityDetails(activityViewModel.getRandom(activities), true)
        }
        setRecycler()
    }

    private fun setRecycler() {
        with(binding.rvActivities) {
            layoutManager = LinearLayoutManager(context)
            adapter = ActivityAdapter(activities, this@ActivitiesListFragment)
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
