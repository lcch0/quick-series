package com.example.quickseries.network.repositories

import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.models.Resource

class ResourceRepository: IRepository<Resource>
{
    override var onModelLoaded: ((Resource) -> Unit)? = null
    override var onError: ((Resource) -> Exception)? = null
    override fun loadModel(app: IAppContext)
    {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}