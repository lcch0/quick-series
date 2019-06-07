package com.example.quickseries.network.json.vacationspots

data class VacationSpot(
    val __v: Int,
    val _active: Boolean,
    val _id: String,
    val addresses: List<Any>,
    val category_eid: String,
    val contactInfo: ContactInfo,
    val created_at: String,
    val description: String,
    val eid: String,
    val freeText: List<Any>,
    val photo: String,
    val slug: String,
    val socialMedia: SocialMedia,
    val title: String,
    val updated_at: String
)