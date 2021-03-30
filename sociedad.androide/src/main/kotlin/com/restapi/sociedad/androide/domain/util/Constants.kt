package com.restapi.sociedad.androide.domain.util

class Constants {

    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION

        const val URL_PERSON = "$URL_BASE/persons"
    }
}