package com.example.quickseries.ui.fragments.categories

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quickseries.R
import com.example.quickseries.intefaces.views.ISortCallback
import com.example.quickseries.intefaces.views.SortDirection
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.Category
import com.example.quickseries.models.CategoryList
import kotlinx.android.synthetic.main.categories_fragment.*
import kotlinx.android.synthetic.main.categories_fragment.view.*

class CategoryView : Fragment()
{
    private lateinit var viewModel: CategoryViewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.categories_fragment, container, false)

        val adapter = CategoryAdapter(CategoryList())
        adapter.onItemClicked = {onCategoryClicked(it)}
        view.mainView.adapter = adapter
        view.mainView.layoutManager = LinearLayoutManager(activity)
        return view
    }

    private fun attachSortCallback(activity: Activity?)
    {
        val sortCallback = activity as? ISortCallback
        sortCallback?.onSort = { viewModel.sort(it) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        val factory = CategoryViewViewModelFactory(AccessModel.Network)

        viewModel = ViewModelProviders
                .of(this, factory)
                .get(CategoryViewViewModel::class.java)

        viewModel.categories.observe(this, Observer { updateView(it) })

        attachSortCallback(activity)
    }

    private fun updateView(data: CategoryList)
    {
        val adapter = CategoryAdapter(data)
        adapter.onItemClicked = { c -> onCategoryClicked(c) }
        mainView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun onCategoryClicked(category: Category)
    {
            val action=
                CategoryViewDirections.actionCategoryViewToResourceView()

            action.categoryId = category.id.toString()
            findNavController().navigate(action)
    }

}
