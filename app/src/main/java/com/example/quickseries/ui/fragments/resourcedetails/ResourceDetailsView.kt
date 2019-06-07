package com.example.quickseries.ui.fragments.resourcedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quickseries.R
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.ResourceDetails
import kotlinx.android.synthetic.main.resourses_fragment.*
import kotlinx.android.synthetic.main.resourses_fragment.view.*

class ResourceDetailsView : Fragment()
{

    private lateinit var viewModel: ResourceDetailsViewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.resource_details_fragment, container, false)
        val adapter = ResourceDetailsAdapter(ResourceDetails())
        view.mainView.adapter = adapter
        view.mainView.layoutManager = LinearLayoutManager(activity)
        return view
    }

    private fun onItemClicked(view: View)
    {
        when (view.id)
        {
            R.id.callBtn -> viewModel.call(view.tag)
            R.id.locationBtn -> viewModel.locate(view.tag)
        }
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        viewModel.saveTo(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { ResourceDetailsViewArgs.fromBundle(it) } ?:return

        //here is the place for injection
        val factory = ResourceDetailsViewViewModelFactory(AccessModel.Network, safeArgs.resourceDetails)

        viewModel = ViewModelProviders
                .of(this, factory)
                .get(ResourceDetailsViewViewModel::class.java)

        viewModel.data.observe(this, Observer { updateView(it) })
    }

    private fun updateView(resource: ResourceDetails)
    {
        val adapter = ResourceDetailsAdapter(resource)
        mainView.adapter = adapter
        adapter.onItemClicked = { c -> onItemClicked(c)}
        adapter.notifyDataSetChanged()
    }

}
