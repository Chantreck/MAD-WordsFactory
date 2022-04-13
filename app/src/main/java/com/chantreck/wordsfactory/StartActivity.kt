package com.chantreck.wordsfactory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.chantreck.wordsfactory.databinding.ActivityStartBinding
import com.chantreck.wordsfactory.onboarding.OnboardingAdapter
import com.chantreck.wordsfactory.onboarding.OnboardingData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StartActivity : AppCompatActivity() {
    private val binding by lazy { ActivityStartBinding.inflate(layoutInflater) }
    private val adapter = OnboardingAdapter()
    private val pages = listOf(
        OnboardingData(1, R.string.onboarding_title1, R.drawable.img_onboard1, R.string.next),
        OnboardingData(2, R.string.onboarding_title2, R.drawable.img_onboard2, R.string.next),
        OnboardingData(3, R.string.onboarding_title3, R.drawable.img_onboard3, R.string.lets_start)
    )

    private var _position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.onboardingViewPager.adapter = adapter
        adapter.submitList(pages)

        TabLayoutMediator(binding.onboardingTabLayout, binding.onboardingViewPager) { _, _ -> }.attach()

        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.nextButton.setText(pages[position].button)
                _position = position
            }
        })

        binding.nextButton.setOnClickListener {
            if (_position == 2) {
                Toast.makeText(this, "Let\'s start!", Toast.LENGTH_SHORT).show()
            } else {
                binding.onboardingViewPager.setCurrentItem(_position + 1, true)
            }
        }

        binding.skipButton.setOnClickListener {
            Toast.makeText(this, "Skip", Toast.LENGTH_SHORT).show()
        }
    }
}