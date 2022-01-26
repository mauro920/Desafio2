package com.example.not_bored.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.not_bored.models.ActivityModel
import com.example.not_bored.utils.ActivitiesList

class ActivityViewModel : ViewModel() {


    fun getActivitiesList(participants: String): List<ActivityModel> {
        return if (participants.isNotEmpty()) {
            ActivitiesList.activitiesList.filter { it.participants == participants }
        } else {
            ActivitiesList.activitiesList
        }
    }

     fun getRandom(activities: List<ActivityModel>) = activities[(0..activities.size).random()]


}