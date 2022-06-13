package com.sharmin.posapplication.repositories

import android.content.Context
import org.mockito.kotlin.mock

class SharedPrefRepositoryTest {

    private val context = mock<Context>()
    private val repository = SharedPrefRepository(context)
}