package com.example.quickseries.ui.fragments.resources

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quickseries.databinding.ResoursesFragmentBinding
import com.example.quickseries.intefaces.views.ISortCallback
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.Category
import com.example.quickseries.models.Resource

class ResourceView : Fragment()
{
    private lateinit var viewModel: ResourceViewViewModel
    private lateinit var binding: ResoursesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?)
            : View
    {
        binding = ResoursesFragmentBinding.inflate(inflater, container, false)

        context?.let {
            val adapter = ResourceAdapter(Category(), it)
            adapter.onItemClicked = { a->onResourceClicked(a) }
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
        val safeArgs = arguments?.let { ResourceViewArgs.fromBundle(it) } ?:return

        //here is the place for injection
        val factory = ResourceViewViewModelFactory(AccessModel.Network, safeArgs.categoryId)

        viewModel = ViewModelProvider(this, factory)
                .get(ResourceViewViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, { updateView(it) })

        attachSortCallback(activity)
    }

    private fun updateView(data: Category)
    {
        context?.let {
            val adapter = ResourceAdapter(data, it)
            adapter.onItemClicked = { r -> onResourceClicked(r) }
            binding.mainView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun onResourceClicked(resource: Resource)
    {
        val action=
            ResourceViewDirections.actionResourceViewToResourceDetailsView(resource.resourceDetails)

        findNavController().navigate(action)
    }

}
