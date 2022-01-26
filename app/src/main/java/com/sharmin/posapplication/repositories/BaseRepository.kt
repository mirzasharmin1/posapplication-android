package com.sharmin.posapplication.repositories

import android.content.Context
import com.sharmin.posapplication.PosApplication
import com.sharmin.posapplication.db.PosDatabase

class BaseRepository(val context: Context) {

    val db: PosDatabase
        get() = (context.applicationContext as PosApplication).db
}