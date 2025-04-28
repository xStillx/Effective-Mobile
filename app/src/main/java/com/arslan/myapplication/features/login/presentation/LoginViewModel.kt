package com.arslan.myapplication.features.login.presentation

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.arslan.myapplication.utils.SingleLiveEvent
import com.arslan.myapplication.utils.call
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {


    val goToMain = SingleLiveEvent<Unit>()
    val goToVk = SingleLiveEvent<String>()
    val goToOk = SingleLiveEvent<String>()

    val showFillTheField = SingleLiveEvent<Unit>()
    val showEnterValidEmail = SingleLiveEvent<Unit>()

    fun onLoginClick(email: String, password: String){
        if (email.isNotEmpty() && password.isNotEmpty()){

            if (isEmailValid(email)){
                goToMain.call()
            }else{
                showEnterValidEmail.call()
            }

        }else{
            showFillTheField.call()
        }
    }

    private fun isEmailValid(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun onVkCLick(){
        goToVk.call(VK)
    }

    fun onOkCLick(){
        goToOk.call(OK)
    }
}

const val VK = "https://vk.com/"
const val OK = "https://ok.ru/"