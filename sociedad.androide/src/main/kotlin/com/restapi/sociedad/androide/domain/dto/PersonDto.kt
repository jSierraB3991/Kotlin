package com.restapi.sociedad.androide.domain.dto

import java.time.LocalDate

data class PersonDto (
    val id: Long = 0,
    val dni: Long = 0,
    val name: String = "",
    val lastName: String = "",
    val date: LocalDate? = null
) {
}