package com.example.demotest.controller

import com.example.demotest.domain.enums.ClientLevel
import com.example.demotest.domain.enums.ClientType
import com.example.demotest.infrastructure.request.ClientRequest
import com.example.demotest.infrastructure.service.ClientService
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.javafaker.Faker
import org.hamcrest.Matchers
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(methodMode = AFTER_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var clientService: ClientService
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private val faker = Faker()
    private val api = "/api/v1/client"

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    fun index(){

        for (i in 5 downTo 1 step 1) {
            clientService.save(ClientRequest().apply {
                code = (faker.number().randomNumber() * i)
                level = ClientLevel.BRONZE
                type = ClientType.B2B
            })
        }

        mockMvc.perform(get(api))
            .andDo(print())
            .andExpect(
                ResultMatcher.matchAll(
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.total_elements", Matchers.equalTo(5)),
                    jsonPath("$.content[0].uuid", notNullValue()),
                    jsonPath("$.content[0].level", notNullValue()),
                    jsonPath("$.content[0].type", notNullValue()),
                    jsonPath("$.content[0].code", notNullValue())
                )
            )
    }

    @Test
    fun show(){
        val client = clientService.save(ClientRequest().apply {
            code = faker.number().randomNumber()
            level = ClientLevel.BRONZE
            type = ClientType.B2B
        })

        mockMvc.perform(get("$api/${client.uuid}"))
            .andDo(print())
            .andExpect(
                ResultMatcher.matchAll(
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.uuid", Matchers.equalTo(client.uuid.toString())),
                    jsonPath("$.level", Matchers.equalTo(client.level?.name)),
                    jsonPath("$.type", Matchers.equalTo(client.type?.name)),
                    jsonPath("$.code", Matchers.equalTo(client.code?.toInt()))
                )
            )
    }

    @Test
    fun save(){
        val client = ClientRequest().apply {
            code = faker.number().randomNumber()
            level = ClientLevel.BRONZE
            type = ClientType.B2B
        }

        mockMvc.perform(post(api)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client))
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())

        val clientById = clientService.finByCode(client.code!!)
        Assertions.assertNotNull(clientById.uuid)
        Assertions.assertEquals(client.code, clientById.code)
        Assertions.assertEquals(client.level, clientById.level)
        Assertions.assertEquals(client.type, clientById.type)
    }
}