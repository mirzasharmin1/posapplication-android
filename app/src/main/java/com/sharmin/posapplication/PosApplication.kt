package com.sharmin.posapplication

import android.app.Application
import androidx.room.Room
import com.sharmin.posapplication.db.PosDatabase
import com.sharmin.posapplication.db.converters.ProductTypeConverter
import com.sharmin.posapplication.db.converters.RoleConverter
import com.sharmin.posapplication.db.converters.TransactionStatusConverter

class PosApplication : Application() {

    lateinit var db: PosDatabase

    override fun onCreate() {
        super.onCreate()
        initializeDb()
    }

    private fun initializeDb() {
        db = Room.databaseBuilder(
            applicationContext,
            PosDatabase::class.java,
            "pos-database"
        ).build()
    }
}