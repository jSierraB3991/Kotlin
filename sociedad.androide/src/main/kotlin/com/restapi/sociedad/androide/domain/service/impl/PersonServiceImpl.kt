package com.restapi.sociedad.androide.domain.service.impl

import com.restapi.sociedad.androide.domain.model.Person
import com.restapi.sociedad.androide.domain.service.PersonService
import com.restapi.sociedad.androide.infrastructure.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl: PersonService {

    @Autowired
    val personRepository: PersonRepository? = null

    override fun findAll(): List<Person> {
        TODO("Not yet implemented")
    }

    override fun load(idPerson: Long): Person {
        TODO("Not yet implemented")
    }

    override fun save(person: Person): Person {
        TODO("Not yet implemented")
    }

    override fun remove(idPerson: Long) {
        TODO("Not yet implemented")
    }
}