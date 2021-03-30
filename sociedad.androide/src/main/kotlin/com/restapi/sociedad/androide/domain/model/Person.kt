package com.restapi.sociedad.androide.domain.model

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

import javax.persistence.GenerationType.AUTO

@Entity
@Table(name = "person")
data class Person(
    val dni: Long = 0,
    val name: String = "",
    val lastName: String = "",
    val date: LocalDate? = null,

    @Id
    @GeneratedValue(strategy = AUTO)
    var id: Long = 0

    ) {
}