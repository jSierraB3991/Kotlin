package com.example.demotest.infrastructure.dto

import com.example.demotest.domain.enums.ClientLevel
import com.example.demotest.domain.enums.ClientType
import java.util.UUID

class ClientDto {
    var uuid: UUID? = null
    var level: ClientLevel? = null
    var type: ClientType? = null
    var code: Long? = null
}