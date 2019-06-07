package com.example.quickseries.intefaces.network

import com.example.quickseries.intefaces.views.IAppContext

interface IQuery<T>
{
    fun request(app: IAppContext)
    var onSuccess: ((T) -> Unit)?
}