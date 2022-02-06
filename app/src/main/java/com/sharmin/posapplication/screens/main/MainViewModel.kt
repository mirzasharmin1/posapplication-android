package com.sharmin.posapplication.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharmin.posapplication.repositories.SharedPrefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val sharedPrefRepository: SharedPrefRepository) : ViewModel() {

    val userNameLiveData: MutableLiveData<String> = MutableLiveData("")
    val passwordLiveData: MutableLiveData<String> = MutableLiveData("")
    val rememberMeLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        retrieveUsernamePassword()
    }

    fun setRememberMe(rememberMe: Boolean) {
        sharedPrefRepository.store(SharedPrefRepository.KEY_REMEMBER, rememberMe)
    }

    fun handleRememberMe(userName: String, password: String) {
        val rememberMeEnabled = sharedPrefRepository.getBoolean(SharedPrefRepository.KEY_REMEMBER) ?: return

        if (rememberMeEnabled) {
            sharedPrefRepository.store(SharedPrefRepository.KEY_USERNAME, userName)
            sharedPrefRepository.store(SharedPrefRepository.KEY_PASSWORD, password)
        }
    }

    private fun retrieveUsernamePassword() {
        val rememberMeEnabled = sharedPrefRepository.getBoolean(SharedPrefRepository.KEY_REMEMBER) ?: return

        if (rememberMeEnabled) {
            val userName = sharedPrefRepository.getString(SharedPrefRepository.KEY_USERNAME)
            val password = sharedPrefRepository.getString(SharedPrefRepository.KEY_PASSWORD)
            userNameLiveData.value = userName
            passwordLiveData.value = password
        }

        rememberMeLiveData.value = rememberMeEnabled
    }
}