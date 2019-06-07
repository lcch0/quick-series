package com.example.quickseries.ui.fragments.resourcedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.ResourceDetails

class ResourceDetailsViewViewModelFactory(private val mode: AccessModel,
                                          private val resource: ResourceDetails) : ViewModelProvider.Factory
{
    private val repository: IRepository<ResourceDetails> by lazy { initRepository(mode) }

    private fun initRepository(mode: AccessModel): IRepository<ResourceDetails>//can be done with IoC
    {
        return when(mode)
        {
            AccessModel.Network -> com.example.quickseries.network.repositories.ResourceDetailsRepository(resource)
            AccessModel.Db      -> com.example.quickseries.db.repositories.ResourceDetailsRepository(resource)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return ResourceDetailsViewViewModel(repository) as T
    }
}