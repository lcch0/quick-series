package com.example.quickseries.network.repositories

import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.models.Category
import com.example.quickseries.models.CategoryList
import com.example.quickseries.network.json.Categories
import com.example.quickseries.network.queries.CategoryListQuery
import java.util.*

class CategoryListRepository : IRepository<CategoryList>
{
    override var onModelLoaded: ((CategoryList) -> Unit)? = null
    override var onError: ((CategoryList) -> Exception)? = null

    override fun loadModel(app: IAppContext)
    {
        val query = CategoryListQuery()
        query.onSuccess =
            {
                val res = transformFrom(it)
                onModelLoaded?.invoke(res)
            }

        query.request(app)
    }

    private fun transformFrom(categories: List<Categories>): CategoryList
    {
        val list = CategoryList()
        for (c in categories)
        {
            val model = Category()
            model.id = UUID.fromString(c.eid)
            model.name = c.title
            list.categories.add(model)
        }
        return list
    }
}