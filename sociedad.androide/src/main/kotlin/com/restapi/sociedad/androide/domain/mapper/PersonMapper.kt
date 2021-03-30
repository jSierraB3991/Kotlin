package com.restapi.sociedad.androide.domain.mapper

import com.restapi.sociedad.androide.domain.dto.PersonDto
import com.restapi.sociedad.androide.domain.model.Person
import org.springframework.stereotype.Component

@Component
class PersonMapper: AuthorMapper<PersonDto, Person> {
    override fun getDto(domain: Person): PersonDto {
        return PersonDto(domain.id, domain.dni, domain.name, domain.lastName, domain.date)
    }

    override fun getDomain(dto: PersonDto): Person {
        return Person(dto.dni, dto.name, dto.lastName, dto.date, dto.id)
    }
}