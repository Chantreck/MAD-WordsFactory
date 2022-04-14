package com.chantreck.wordsfactory.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.chantreck.wordsfactory.SharedPrefs
import com.chantreck.wordsfactory.databinding.ActivityStartBinding
import com.chantreck.wordsfactory.sign_up.SignUpActivity
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {
    private val binding by lazy { ActivityStartBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<OnboardingViewModel>()
    private val adapter = OnboardingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        SharedPrefs.setup(this)

        binding.onboardingViewPager.adapter = adapter
        adapter.submitList(viewModel.pages)
        TabLayoutMediator(binding.onboardingTabLayout, binding.onboardingViewPager) { _, _ -> }.attach()

        setObservers()
        setListeners()
    }

    private fun setObservers() {
        viewModel.position.observe(this) { position ->
            binding.onboardingViewPager.setCurrentItem(position, true)
            binding.nextButton.setText(viewModel.pages[position].button)
        }

        viewModel.userSaved.observe(this) { state ->
            if (state) {
                goToHub()
            }
        }

        viewModel.onboardingWatched.observe(this) { state ->
            if (state) {
                goToSignUp()
            }
        }
    }

    private fun setListeners() {
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.nextPageBySwipe(position)
            }
        })

        binding.nextButton.setOnClickListener {
            if (viewModel.position.value == viewModel.pages.lastIndex) {
                viewModel.goToSignUp()
                goToSignUp()
            } else {
                viewModel.nextPage()
            }
        }

        binding.skipButton.setOnClickListener {
            viewModel.goToSignUp()
            goToSignUp()
        }
    }

    private fun goToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToHub() {
        //TODO
    }
}