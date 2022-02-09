package com.sharmin.posapplication.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sharmin.posapplication.db.models.People

@Dao
interface PeopleDao {

    @Insert
    fun insert(people: People)

    @Insert
    fun insertAll(vararg peoples: People)

    @Query("SELECT * FROM People WHERE name LIKE :name")
    fun getPeople(name: String): People?
}