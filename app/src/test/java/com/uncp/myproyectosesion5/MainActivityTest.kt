package com.uncp.myproyectosesion5

import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityTest {
    @Test
    fun calcularDosNumerosValidosTest() {
        // Given
        val number1 = "12"
        val number2 = "10"
        val responseExpected = 44
        // When
        val response = calcularDosNumeros(number1, number2)
        // Then
        assertEquals(responseExpected, response)
    }

    @Test
    fun calcularDosNumerosInvalidosTest() {
        // Given
        val number1 = "ga"
        val number2 = "ge"
        val responseExpected = 0
        // When
        val response = calcularDosNumeros(number1, number2)
        // Then
        assertEquals(responseExpected, response)
    }
}