package com.example.quickseries.db.repositories

import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.models.CategoryList

class CategoryListRepository : IRepository<CategoryList>
{
    override var onModelLoaded: ((CategoryList) -> Unit)? = null
    override var onError: ((CategoryList) -> Exception)? = null

    override fun loadModel(app: IAppContext)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}