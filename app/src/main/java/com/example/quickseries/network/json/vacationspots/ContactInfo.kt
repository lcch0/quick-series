package com.example.quickseries.network.json.vacationspots

data class ContactInfo(
    val email: List<String>,
    val faxNumber: List<String>,
    val phoneNumber: List<String>,
    val tollFree: List<String>,
    val website: List<String>
)