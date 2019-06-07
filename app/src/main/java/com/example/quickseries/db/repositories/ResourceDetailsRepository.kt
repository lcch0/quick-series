package com.example.quickseries.db.repositories

import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.models.ResourceDetails

class ResourceDetailsRepository(resourceId: ResourceDetails) : IRepository<ResourceDetails>
{
    override var onModelLoaded: ((ResourceDetails) -> Unit)? = null
    override var onError: ((ResourceDetails) -> Exception)? = null
    override fun loadModel(app: IAppContext)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}