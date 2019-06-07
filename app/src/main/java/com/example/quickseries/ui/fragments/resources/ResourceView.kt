package com.example.quickseries.ui.fragments.resources

import android.app.Activity
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
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.Category
import com.example.quickseries.models.Resource
import kotlinx.android.synthetic.main.resourses_fragment.*
import kotlinx.android.synthetic.main.resourses_fragment.view.*

class ResourceView : Fragment()
{
    private lateinit var viewModel: ResourceViewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?)
            : View?
    {
        val view = inflater.inflate(R.layout.resourses_fragment, container, false)

        val adapter = ResourceAdapter(Category())
        adapter.onItemClicked = { onResourceClicked(it) }
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
        val safeArgs = arguments?.let { ResourceViewArgs.fromBundle(it) } ?:return

        //here is the place for injection
        val factory = ResourceViewViewModelFactory(AccessModel.Network, safeArgs.categoryId)

        viewModel = ViewModelProviders
                .of(this, factory)
                .get(ResourceViewViewModel::class.java)

        viewModel.data.observe(this, Observer { updateView(it) })

        attachSortCallback(activity)
    }

    private fun updateView(data: Category)
    {
        val adapter = ResourceAdapter(data)
        adapter.onItemClicked = { r -> onResourceClicked(r) }
        mainView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun onResourceClicked(resource: Resource)
    {
        val action=
            ResourceViewDirections.actionResourceViewToResourceDetailsView(resource.resourceDetails)

        findNavController().navigate(action)
    }

}
