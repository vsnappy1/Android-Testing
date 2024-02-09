package com.kumar.androidtesting

enum class EmploymentStatus {
    EMPLOYED, UNEMPLOYED
}

class DiscountEligibilityEngine(
    private val age: Int,
    private val employmentStatus: EmploymentStatus
) {
    fun getDiscountPercentage(): Double {
        if (age < 18) return 10.0
        if (age in 18..35 && employmentStatus == EmploymentStatus.EMPLOYED) return 15.0
        if (age in 18..35 && employmentStatus == EmploymentStatus.UNEMPLOYED) return 20.0
        else return 25.0
    }
}