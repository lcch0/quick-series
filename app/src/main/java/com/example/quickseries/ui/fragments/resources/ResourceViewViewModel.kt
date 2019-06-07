package com.example.quickseries.ui.fragments.resources

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickseries.QuickSeriesApp
import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.SortDirection
import com.example.quickseries.models.Category

class ResourceViewViewModel(repository: IRepository<Category>) : ViewModel()
{
    var data: MutableLiveData<Category> = MutableLiveData()

    init
    {
        repository.onModelLoaded = { data.value = it }
        repository.loadModel(QuickSeriesApp.appContext)
    }

    fun sort(direction: SortDirection)
    {
        val list = data.value ?: return
        when (direction)
        {
            SortDirection.Asc  -> list.resources.sortBy { c -> c.name }
            SortDirection.Desc -> list.resources.sortByDescending { c -> c.name }
        }

        data.value = list
    }
}
