package com.chantreck.wordsfactory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chantreck.wordsfactory.databinding.ActivityStartBinding
import com.chantreck.wordsfactory.onboarding.OnboardingAdapter
import com.chantreck.wordsfactory.onboarding.OnboardingData

class StartActivity : AppCompatActivity() {
    private val binding by lazy { ActivityStartBinding.inflate(layoutInflater) }
    private val adapter = OnboardingAdapter()
    private val pages = listOf(
        OnboardingData(1, R.string.onboarding_title1, R.drawable.img_onboard1, R.string.next),
        OnboardingData(2, R.string.onboarding_title2, R.drawable.img_onboard2, R.string.next),
        OnboardingData(3, R.string.onboarding_title3, R.drawable.img_onboard3, R.string.lets_start)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.onboardingViewPager.adapter = adapter
        adapter.submitList(pages)
    }
}