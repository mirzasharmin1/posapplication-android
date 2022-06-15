package com.sharmin.posapplication.screens.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sharmin.posapplication.repositories.SharedPrefRepository
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val sharedPrefRepository = mock<SharedPrefRepository>()
    private lateinit var viewModel: MainViewModel

    @Test
    fun `init Given rememberMe is available and enabled Then retrieves username and password from sharedPrefRepository and sets rememberMeLiveData to true`() {
        val username = "user_one"
        val password = "password_one"
        whenever(sharedPrefRepository.getBoolean(SharedPrefRepository.KEY_REMEMBER)).thenReturn(true)
        whenever(sharedPrefRepository.getString(SharedPrefRepository.KEY_USERNAME)).thenReturn(username)
        whenever(sharedPrefRepository.getString(SharedPrefRepository.KEY_PASSWORD)).thenReturn(password)

        viewModel = MainViewModel(sharedPrefRepository)

        val usernameValue = viewModel.userNameLiveData.value!!
        val passwordValue = viewModel.passwordLiveData.value!!
        val rememberMeValue = viewModel.rememberMeLiveData.value!!
        verify(sharedPrefRepository).getBoolean(SharedPrefRepository.KEY_REMEMBER)
        verify(sharedPrefRepository).getString(SharedPrefRepository.KEY_USERNAME)
        verify(sharedPrefRepository).getString(SharedPrefRepository.KEY_PASSWORD)
        assertEquals(usernameValue, username)
        assertEquals(passwordValue, password)
        assertEquals(rememberMeValue, true)
    }

    @Test
    fun `init Given if rememberMe is not available from sharedPrefRepository then keeps rememberMeLiveData as false and return`() {
        whenever(sharedPrefRepository.getBoolean(SharedPrefRepository.KEY_REMEMBER)).thenReturn(null)

        viewModel = MainViewModel(sharedPrefRepository)

        val rememberMeValue = viewModel.rememberMeLiveData.value!!
        verify(sharedPrefRepository).getBoolean(SharedPrefRepository.KEY_REMEMBER)
        verify(sharedPrefRepository, never()).getString(SharedPrefRepository.KEY_USERNAME)
        verify(sharedPrefRepository, never()).getString(SharedPrefRepository.KEY_PASSWORD)
        assertEquals(rememberMeValue, false)
    }

    @Test
    fun `init Given rememberMe is available and enabled Then does not retrieve username and password from sharedPrefRepository and sets rememberMeLiveData to false`() {
        whenever(sharedPrefRepository.getBoolean(SharedPrefRepository.KEY_REMEMBER)).thenReturn(false)

        viewModel = MainViewModel(sharedPrefRepository)

        val rememberMeValue = viewModel.rememberMeLiveData.value!!
        verify(sharedPrefRepository).getBoolean(SharedPrefRepository.KEY_REMEMBER)
        verify(sharedPrefRepository, never()).getString(SharedPrefRepository.KEY_USERNAME)
        verify(sharedPrefRepository, never()).getString(SharedPrefRepository.KEY_PASSWORD)
        assertEquals(rememberMeValue, false)
    }

    @Test
    fun `setRememberMe sets remember key flag via sharedPrefRepository`() {
        viewModel = MainViewModel(sharedPrefRepository)

        viewModel.setRememberMe(true)

        verify(sharedPrefRepository).store(SharedPrefRepository.KEY_REMEMBER, true)
    }

    @Test
    fun `handleRememberMe Given if rememberMe is available and enabled Then stores username and password through sharedPrefRepository`() {
        whenever(sharedPrefRepository.getBoolean(SharedPrefRepository.KEY_REMEMBER)).thenReturn(true)
        val username = "user_one"
        val password = "password_one"
        viewModel = MainViewModel(sharedPrefRepository)

        viewModel.handleRememberMe(username, password)

        verify(sharedPrefRepository, times(2)).getBoolean(SharedPrefRepository.KEY_REMEMBER)
        verify(sharedPrefRepository).store(SharedPrefRepository.KEY_USERNAME, username)
        verify(sharedPrefRepository).store(SharedPrefRepository.KEY_PASSWORD, password)
    }

    @Test
    fun `handleRememberMe Given if rememberMe is unavailable or disabled Then does not store username and password through sharedPrefRepository`() {
        whenever(sharedPrefRepository.getBoolean(SharedPrefRepository.KEY_REMEMBER)).thenReturn(false)
        val username = "user_one"
        val password = "password_one"
        viewModel = MainViewModel(sharedPrefRepository)

        viewModel.handleRememberMe(username, password)

        verify(sharedPrefRepository, times(2)).getBoolean(SharedPrefRepository.KEY_REMEMBER)
        verify(sharedPrefRepository, never()).store(SharedPrefRepository.KEY_USERNAME, username)
        verify(sharedPrefRepository, never()).store(SharedPrefRepository.KEY_PASSWORD, password)
    }
}