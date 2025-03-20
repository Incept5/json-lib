package org.incept5.json

import com.fasterxml.jackson.core.type.TypeReference
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class JsonTest {

    data class TestData(val name: String, val age: Int, val date: LocalDate? = null)
    
    @Test
    fun `should serialize and deserialize object`() {
        // Given
        val testData = TestData("John Doe", 30, LocalDate.of(2023, 1, 1))
        
        // When
        val json = Json.toJson(testData)
        val deserialized = Json.fromJson<TestData>(json)
        
        // Then
        assertNotNull(json)
        assertEquals(testData, deserialized)
    }
    
    @Test
    fun `should handle pretty printing`() {
        // Given
        val testData = TestData("John Doe", 30)
        
        // When
        val json = Json.toJsonWithPrettyPrint(testData)
        
        // Then
        assertNotNull(json)
        assert(json.contains("\n"))
    }
    
    @Test
    fun `should deserialize using class reference`() {
        // Given
        val json = """{"name":"Jane Doe","age":25}"""
        
        // When
        val deserialized = Json.fromJson(json, TestData::class.java)
        
        // Then
        assertEquals("Jane Doe", deserialized.name)
        assertEquals(25, deserialized.age)
    }
    
    @Test
    fun `should deserialize using type reference`() {
        // Given
        val json = """[{"name":"Jane Doe","age":25},{"name":"John Doe","age":30}]"""
        
        // When
        val deserialized = Json.fromJson(json, object : TypeReference<List<TestData>>() {})
        
        // Then
        assertEquals(2, deserialized.size)
        assertEquals("Jane Doe", deserialized[0].name)
        assertEquals("John Doe", deserialized[1].name)
    }
}