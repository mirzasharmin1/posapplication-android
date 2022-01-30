package com.sharmin.posapplication.di

import android.content.Context
import androidx.room.Room
import com.sharmin.posapplication.db.PosDatabase
import com.sharmin.posapplication.db.dao.ProductDao
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
import dagger.Module

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): PosDatabase {
        // No need to cancel this scope as it'll be torn down with the process
        val applicationScope = CoroutineScope(SupervisorJob())
        return PosDatabase.getDatabase(appContext, applicationScope)
    }

    @Provides
    fun provideChannelDao(posDatabase: PosDatabase): ProductDao {
        return posDatabase.productDao()
    }
}