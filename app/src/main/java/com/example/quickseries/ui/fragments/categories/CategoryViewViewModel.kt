package com.example.quickseries.ui.fragments.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickseries.QuickSeriesApp
import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.SortDirection
import com.example.quickseries.models.CategoryList

class CategoryViewViewModel(repository: IRepository<CategoryList>) : ViewModel()
{
    var categories: MutableLiveData<CategoryList> = MutableLiveData()

    init
    {
        repository.onModelLoaded = { categories.value = it }
        repository.loadModel(QuickSeriesApp.appContext)
    }

    fun sort(direction: SortDirection)
    {
        val list = categories.value ?: return
        when (direction)
        {
            SortDirection.Asc -> list.categories.sortBy { c -> c.name }
            SortDirection.Desc-> list.categories.sortByDescending { c -> c.name }
        }

        categories.value = list
    }
}
