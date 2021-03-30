package com.restapi.sociedad.androide.infrastructure.controller

import com.restapi.sociedad.androide.domain.dto.PersonDto
import com.restapi.sociedad.androide.domain.exception.BusinessException
import com.restapi.sociedad.androide.domain.exception.NotFoundException
import com.restapi.sociedad.androide.domain.mapper.PersonMapper
import com.restapi.sociedad.androide.domain.model.Person
import com.restapi.sociedad.androide.domain.service.PersonService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.restapi.sociedad.androide.domain.util.Constants.Companion.URL_PERSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping(URL_PERSON)
class PersonRestController {

    @Autowired
    val personService: PersonService? = null

    @Autowired
    val personMapper: PersonMapper? = null

    @GetMapping
    fun findAll(): ResponseEntity<List<PersonDto>> {
        return try {
            val persons: List<Person> = personService!!.findAll()
            ResponseEntity(personMapper!!.getListDto(persons), OK)
        }catch (e: BusinessException){
            ResponseEntity(INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idPerson: Long) :ResponseEntity<PersonDto> {
        return try {
            val person : Person = personService!!.load(idPerson)
            ResponseEntity(personMapper!!.getDto(person), OK)
        }catch (e:NotFoundException){
            ResponseEntity(NOT_FOUND)
        }catch (e:BusinessException){
            ResponseEntity(INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun insert(@RequestBody person: PersonDto): ResponseEntity<PersonDto>{
        return try {
            val person : Person = personService!!.save(personMapper!!.getDomain(person))
            ResponseEntity(personMapper!!.getDto(person), OK)
        }catch (e: BusinessException){
            ResponseEntity(INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idPerson: Long): ResponseEntity<Any> {
        return try {
            personService!!.remove(idPerson)
            ResponseEntity(OK)
        } catch (e: NotFoundException){
            ResponseEntity(NOT_FOUND)
        }catch (e:BusinessException){
            ResponseEntity(INTERNAL_SERVER_ERROR)
        }
    }

}