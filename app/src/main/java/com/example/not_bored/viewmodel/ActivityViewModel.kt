package com.example.not_bored.viewmodel


import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.not_bored.models.ActivityModel
import com.example.not_bored.utils.ActivitiesList

class ActivityViewModel : ViewModel() {

    var participants = MutableLiveData<String>()
    private lateinit var sharedPreferences: SharedPreferences

    fun setSharedPreferences (preferences: SharedPreferences){
         this.sharedPreferences = preferences
    }

    fun addParticipants(input: String){
        this.sharedPreferences.edit().putString("user", input).apply()
    }
    fun getParticipants(): String{
        participants.value = this.sharedPreferences.getString("user","")
        return participants.value.toString()
    }

    fun getActivitiesList(participants: String): List<ActivityModel> {
        return if (participants.isNotEmpty()) {
            ActivitiesList.activitiesList.filter { it.participants == participants }
        } else {
            ActivitiesList.activitiesList
        }
    }

     fun getRandom(activities: List<ActivityModel>) = activities[(0..activities.size).random()]


}