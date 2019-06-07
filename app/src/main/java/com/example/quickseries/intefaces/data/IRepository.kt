package com.example.quickseries.intefaces.data

import com.example.quickseries.intefaces.views.IAppContext

interface IRepository<T: IModel>
{
    fun loadModel(app: IAppContext)
    var onModelLoaded: ((T) -> Unit)?
    var onError: ((T) -> Exception)?
}