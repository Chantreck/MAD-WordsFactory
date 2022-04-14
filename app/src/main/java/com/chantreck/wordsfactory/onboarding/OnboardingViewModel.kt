package com.chantreck.wordsfactory.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chantreck.wordsfactory.R
import com.chantreck.wordsfactory.SharedPrefs

class OnboardingViewModel: ViewModel() {
    private val _position = MutableLiveData(0)
    val position: LiveData<Int> get() = _position

    private val _onboardingWatched = MutableLiveData<Boolean>()
    val onboardingWatched: LiveData<Boolean> get() = _onboardingWatched

    private val _userSaved = MutableLiveData<Boolean>()
    val userSaved: LiveData<Boolean> get() = _userSaved

    val pages = listOf(
        OnboardingData(1, R.string.onboarding_title1, R.drawable.img_onboard1, R.string.next),
        OnboardingData(2, R.string.onboarding_title2, R.drawable.img_onboard2, R.string.next),
        OnboardingData(3, R.string.onboarding_title3, R.drawable.img_onboard3, R.string.lets_start)
    )

    init {
        _userSaved.value = SharedPrefs.isUserSaved()
        _onboardingWatched.value = SharedPrefs.isOnboardingWatched()
    }

    fun nextPage() {
        _position.value = _position.value?.plus(1)
    }

    fun nextPageBySwipe(position: Int) {
        _position.value = position
    }

    fun goToSignUp() {
        SharedPrefs.saveOnboarding()
    }
}