package com.restapi.sociedad.androide.domain.service.impl

import com.restapi.sociedad.androide.domain.exception.BusinessException
import com.restapi.sociedad.androide.domain.exception.NotFoundException
import com.restapi.sociedad.androide.domain.model.Person
import com.restapi.sociedad.androide.domain.service.PersonService
import com.restapi.sociedad.androide.infrastructure.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PersonServiceImpl : PersonService {

    @Autowired
    val personRepository: PersonRepository? = null

    @Throws(BusinessException::class)
    override fun findAll(): List<Person> {
        try {
            return personRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message!!)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idPerson: Long): Person {
        val op: Optional<Person>
        try {
            op = personRepository!!.findById(idPerson)
        } catch (e: Exception) {
            throw BusinessException(e.message!!)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró ninguna persona con el id: $idPerson")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(person: Person): Person {
        try {
            return personRepository!!.save(person)
        }catch (e:Exception){
            throw BusinessException(e.message!!)
        }
    }

    override fun remove(idPerson: Long) {
        val person: Person
        try {
            person = load(idPerson)
        }catch (e: NotFoundException) {
            throw NotFoundException(e.message!!)
        }catch (e:BusinessException){
            throw BusinessException(e.message!!)
        }

        try {
            personRepository!!.delete(person)
        }catch (e:Exception){
            throw BusinessException(e.message!!)
        }
    }
}