package com.example.quickseries.models

import com.example.quickseries.intefaces.data.IModel
import java.util.*

class Resource : IModel
{
    companion object
    {
        val DEFAULT_ID: UUID = UUID.fromString("628aea2e-b75a-4103-8c7b-d00ccf9d8e06")
        const val DEFAULT_NAME = "Default"
        const val DEFAULT_DESC = "Default"
    }

    var htmlDesc = DEFAULT_DESC
    var id = DEFAULT_ID
    var name = DEFAULT_NAME
    var resourceDetails: ResourceDetails = ResourceDetails()
}