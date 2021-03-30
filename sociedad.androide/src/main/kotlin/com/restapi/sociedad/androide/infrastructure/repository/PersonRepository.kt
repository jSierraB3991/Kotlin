package com.restapi.sociedad.androide.infrastructure.repository

import com.restapi.sociedad.androide.domain.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, Long>