package com.example.quickseries.ui.fragments.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.CategoryList

class CategoryViewViewModelFactory(private val mode: AccessModel) : ViewModelProvider.Factory
{
    private val repository: IRepository<CategoryList> by lazy { initRepository(mode) }

    private fun initRepository(mode: AccessModel): IRepository<CategoryList>//can be done with IoC
    {
        return when(mode)
        {
            AccessModel.Network -> com.example.quickseries.network.repositories.CategoryListRepository()
            AccessModel.Db      -> com.example.quickseries.db.repositories.CategoryListRepository()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return CategoryViewViewModel(repository) as T;
    }
}