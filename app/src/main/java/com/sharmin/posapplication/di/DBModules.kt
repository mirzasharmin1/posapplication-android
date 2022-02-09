package com.sharmin.posapplication.di

import android.content.Context
import androidx.room.Room
import com.sharmin.posapplication.db.PosDatabase
import com.sharmin.posapplication.db.dao.PeopleDao
import com.sharmin.posapplication.db.dao.ProductDao
import com.sharmin.posapplication.db.dao.TransactionDao
import com.sharmin.posapplication.db.dao.TransactionItemDao
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
    fun provideProductDao(posDatabase: PosDatabase): ProductDao {
        return posDatabase.productDao()
    }

    @Provides
    fun provideTransactionDao(posDatabase: PosDatabase): TransactionDao {
        return posDatabase.transactionDao()
    }

    @Provides
    fun provideTransactionItemDao(posDatabase: PosDatabase): TransactionItemDao {
        return posDatabase.transactionItemDao()
    }

    @Provides
    fun providePeopleDao(posDatabase: PosDatabase): PeopleDao {
        return posDatabase.peopleDao()
    }
}