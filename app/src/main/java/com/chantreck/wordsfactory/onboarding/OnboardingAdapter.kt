package com.chantreck.wordsfactory.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chantreck.wordsfactory.R
import com.chantreck.wordsfactory.databinding.ItemOnboardingBinding

class OnboardingAdapter : ListAdapter<OnboardingData, OnboardingAdapter.PagerViewHolder>(DIFF) {
    private companion object {
        val DIFF = object : DiffUtil.ItemCallback<OnboardingData>() {
            override fun areItemsTheSame(oldItem: OnboardingData, newItem: OnboardingData) =
                oldItem.position == newItem.position

            override fun areContentsTheSame(oldItem: OnboardingData, newItem: OnboardingData) =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_onboarding, parent, false)
        )

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemOnboardingBinding.bind(view)

        fun bind(item: OnboardingData) {
            binding.onboardingPageImageView.setImageResource(item.image)
            binding.onboardingPageTitleTextView.setText(item.title)
        }
    }
}