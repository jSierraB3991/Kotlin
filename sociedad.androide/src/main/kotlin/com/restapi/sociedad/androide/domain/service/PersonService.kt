package com.restapi.sociedad.androide.domain.service

import com.restapi.sociedad.androide.domain.model.Person

interface PersonService {

    fun findAll(): List<Person>
    fun load(idPerson:Long): Person
    fun save(person: Person): Person
    fun remove(idPerson: Long)
}