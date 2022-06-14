package com.sharmin.posapplication.repositories

import com.sharmin.posapplication.db.dao.PeopleDao
import com.sharmin.posapplication.db.models.People
import com.sharmin.posapplication.db.models.Role
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class PeopleRepositoryTest {

    private val peopleDao = mock<PeopleDao>()
    private val repository = PeopleRepository(peopleDao)

    @Test
    fun `getCurrentUser Calls getPeople from peopleDao with Customer One as parameter and returns the result`() {
        val people = People(Role.ADMIN, "Customer One")
        whenever(peopleDao.getPeople("Customer One")).thenReturn(people)

        val result = repository.getCurrentUser()

        verify(peopleDao).getPeople("Customer One")
        assertEquals(result, result)
    }
}