package com.example.quickseries.network.repositories

import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.models.ResourceDetails

class ResourceDetailsRepository(resource: ResourceDetails) : IRepository<ResourceDetails>
{
    private val resourceDetails = resource

    override var onModelLoaded: ((ResourceDetails) -> Unit)? = null
    override var onError: ((ResourceDetails) -> Exception)? = null
    override fun loadModel(app: IAppContext)
    {
        //this is terrible way to do that, but i run out of time
        //the good solution would be to cache results from previous requests to db and load them
        //i wanted to use Room, but i'm not very familiar with it, so..

        onModelLoaded?.invoke(resourceDetails)
    }
}