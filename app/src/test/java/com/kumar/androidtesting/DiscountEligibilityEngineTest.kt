package com.kumar.androidtesting

import org.junit.Assert.*
import org.junit.Test

class DiscountEligibilityEngineTest{

    @Test
    fun getDiscountPercentage_whenAgeIsLessThanEighteen_shouldReturnTen(){
        // Given
        val discountEligibilityEngine = DiscountEligibilityEngine(
            age = 10,
            employmentStatus = EmploymentStatus.EMPLOYED
        )

        // When
        val discount = discountEligibilityEngine.getDiscountPercentage()

        // Then
        assertEquals("",10.0, discount, 0.0)
    }
}