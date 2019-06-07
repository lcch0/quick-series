package com.example.quickseries.network.json.restorants

data class Restorant(
    val __v: Int,
    val _active: Boolean,
    val _id: String,
    val addresses: List<Addresse>?,
    val bizHours: BizHours?,
    val category_eid: String,
    val contactInfo: ContactInfo?,
    val created_at: String,
    val description: String,
    val eid: String,
    val photo: String,
    val slug: String,
    val socialMedia: SocialMedia?,
    val title: String,
    val updated_at: String
)