package org.incept5.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * Statically accessible Jackson ObjectMapper factory used by http gateways
 */
object Json {

    private val objectMapper: ObjectMapper = jacksonObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .registerModule(JavaTimeModule())
        .registerModule(Jdk8Module())
        .registerModule(KotlinModule.Builder().build())

    fun mapper(): ObjectMapper = objectMapper

    fun toJson(value: Any): String = objectMapper.writeValueAsString(value)

    fun toJsonWithPrettyPrint(value: Any): String = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value)

    inline fun <reified T> fromJson(json: String): T = mapper().readValue(json, T::class.java)

    fun <T> fromJson(json: String, clazz: Class<T>) : T {
        return objectMapper.readValue(json, clazz)
    }

    /**
     * fromJson but handle parameterized types
     */
    fun <T> fromJson(json: String, ref: TypeReference<T>): T {
        return objectMapper.readValue(json, ref)
    }

}