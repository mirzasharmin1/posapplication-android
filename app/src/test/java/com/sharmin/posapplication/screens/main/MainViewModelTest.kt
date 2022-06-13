package com.sharmin.posapplication.screens.main

import com.sharmin.posapplication.repositories.SharedPrefRepository
import org.mockito.kotlin.mock

class MainViewModelTest {

    private val sharedPrefRepository = mock<SharedPrefRepository>()
    private val viewModel = MainViewModel(sharedPrefRepository)
}