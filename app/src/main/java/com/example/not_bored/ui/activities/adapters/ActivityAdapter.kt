package com.example.not_bored.ui.activities.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.not_bored.databinding.ItemActivityBinding
import com.example.not_bored.models.ActivityModel

class ActivityAdapter(
    private val activities: List<ActivityModel>,
    private val itemClickListener: OnActivityCLickListener
) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val itemBinding =
            ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ActivityViewHolder(itemBinding)
        itemBinding.btnNext.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onActivityClick(activities[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(activities[position])
    }

    override fun getItemCount(): Int = activities.size

    inner class ActivityViewHolder(
        private val binding: ItemActivityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ActivityModel) {
            with(binding) {
                tvActivity.text = item.category
            }
        }
    }

    interface OnActivityCLickListener {
        fun onActivityClick(activity: ActivityModel)
    }
}
