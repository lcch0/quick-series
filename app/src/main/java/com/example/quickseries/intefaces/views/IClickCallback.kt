package com.example.quickseries.intefaces.views

interface IClickCallback<T>
{
    var onItemClicked: ((T) -> Unit)?
}