package com.example.quickseries.ui.fragments.resources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.Category

class ResourceViewViewModelFactory(private val mode: AccessModel, private val categoryId: String) : ViewModelProvider.Factory
{
    private val repository: IRepository<Category> by lazy { initRepository(mode) }

    private fun initRepository(mode: AccessModel): IRepository<Category>//can be done with IoC
    {
        return when(mode)
        {
            AccessModel.Network -> com.example.quickseries.network.repositories.CategoryRepository(categoryId)
            AccessModel.Db      -> com.example.quickseries.db.repositories.CategoryRepository(categoryId)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return ResourceViewViewModel(repository) as T
    }
}