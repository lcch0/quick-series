package com.example.quickseries.intefaces.views

interface ISortCallback
{
    var onSort: ((SortDirection) -> Unit)?
}