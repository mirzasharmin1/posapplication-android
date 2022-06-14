package com.sharmin.posapplication.screens.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sharmin.posapplication.repositories.SharedPrefRepository
import org.junit.Rule
import org.mockito.kotlin.mock

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val sharedPrefRepository = mock<SharedPrefRepository>()
    private val viewModel = MainViewModel(sharedPrefRepository)
}