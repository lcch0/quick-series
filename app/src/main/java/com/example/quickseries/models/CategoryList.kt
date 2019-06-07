package com.example.quickseries.models

import com.example.quickseries.intefaces.data.IModel

class CategoryList : IModel
{
    var categories = mutableListOf<Category>()
}