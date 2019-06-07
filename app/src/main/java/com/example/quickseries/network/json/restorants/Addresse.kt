package com.example.quickseries.network.json.restorants

data class Addresse(
    val address1: String,
    val city: String,
    val country: String,
    val label: String,
    val state: String,
    val zipCode: String,
    val gps: Gps?
)