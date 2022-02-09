package com.sharmin.posapplication.repositories

import com.sharmin.posapplication.db.dao.PeopleDao
import com.sharmin.posapplication.db.models.People
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val peopleDao: PeopleDao) {
    fun getCurrentUser(): People? {
        // Returns dummy "People" object as the current logged in user
        return peopleDao.getPeople("Customer One")
    }
}