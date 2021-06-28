package com.example.quickseries.ui.fragments.categories

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quickseries.databinding.CategoriesFragmentBinding
import com.example.quickseries.intefaces.views.ISortCallback
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.Category
import com.example.quickseries.models.CategoryList

class CategoryView : Fragment()
{
    private lateinit var viewModel: CategoryViewViewModel
    private lateinit var binding: CategoriesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = CategoriesFragmentBinding.inflate(inflater, container, false)

        context?.let {
            val adapter = CategoryAdapter(CategoryList(), it)
            adapter.onItemClicked = {a -> onCategoryClicked(a)}
            binding.mainView.adapter = adapter
        }

        binding.mainView.layoutManager = LinearLayoutManager(activity)
        return binding.root
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

        viewModel = ViewModelProvider(this, factory)
                .get(CategoryViewViewModel::class.java)

        viewModel.categories.observe(viewLifecycleOwner, { updateView(it) })

        attachSortCallback(activity)
    }

    private fun updateView(data: CategoryList)
    {
        context?.let {
            val adapter = CategoryAdapter(data, it)
            adapter.onItemClicked = { c -> onCategoryClicked(c) }
            binding.mainView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun onCategoryClicked(category: Category)
    {
            val action=
                CategoryViewDirections.actionCategoryViewToResourceView()

            action.categoryId = category.id.toString()
            findNavController().navigate(action)
    }

}
