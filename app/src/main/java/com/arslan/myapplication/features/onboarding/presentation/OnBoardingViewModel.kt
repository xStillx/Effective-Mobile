package com.arslan.myapplication.features.onboarding.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arslan.myapplication.utils.SingleLiveEvent
import com.arslan.myapplication.utils.asLiveData
import com.arslan.myapplication.utils.call
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val _coursesList = MutableLiveData<List<String>>()
    val courses get() = _coursesList.asLiveData()

    val goToLogin = SingleLiveEvent<Unit>()

    init {
        getCourses()
    }

    private fun getCourses() {
        _coursesList.value = listOf(
            "1С Администрирование",
            "RabbitMQ",
            "Трафик",
            "Контент маркетинг",
            "B2B Маркетинг",
            "Google аналитика",
            "UX исследователь",
            "Веб-аналитика",
            "Big Data",
            "Геймдизайн",
            "Веб-дизайн",
            "Cinema 4D",
            "Промпт инженеринг",
            "Webflow",
            "Three.js",
            "Парсинг",
            "Python-разработчик",
        )
    }

    fun onContinueClick(){
        goToLogin.call()
    }


}