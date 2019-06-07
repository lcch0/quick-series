package com.example.quickseries.db.repositories

import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.models.Category

class CategoryRepository(categoryId: String) : IRepository<Category>
{
    override var onModelLoaded: ((Category) -> Unit)? = null
    override var onError: ((Category) -> Exception)? = null
    override fun loadModel(app: IAppContext)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}