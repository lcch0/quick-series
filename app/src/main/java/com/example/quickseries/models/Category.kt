package com.example.quickseries.models

import com.example.quickseries.intefaces.data.IModel
import java.util.*

class Category : IModel
{
    companion object
    {
        val DEFAULT_ID: UUID = UUID.fromString("c31d226c-42c2-4361-9ce2-6b42f3f9f24b")
        const val DEFAULT_NAME = "Default"
    }

    var id = DEFAULT_ID
    var name = DEFAULT_NAME
    val resources = mutableListOf<Resource>()
}