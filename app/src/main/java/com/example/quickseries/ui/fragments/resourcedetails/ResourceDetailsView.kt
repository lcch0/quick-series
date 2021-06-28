package com.example.quickseries.ui.fragments.resourcedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quickseries.R
import com.example.quickseries.databinding.ResourceDetailsFragmentBinding
import com.example.quickseries.models.AccessModel
import com.example.quickseries.models.ResourceDetails

class ResourceDetailsView : Fragment()
{
    private lateinit var binding: ResourceDetailsFragmentBinding
    private lateinit var viewModel: ResourceDetailsViewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = ResourceDetailsFragmentBinding.inflate(inflater, container, false)

        context?.let {
            val adapter = ResourceDetailsAdapter(ResourceDetails(), it)
            binding.mainView.adapter = adapter
        }

        binding.mainView.layoutManager = LinearLayoutManager(activity)
        return binding.root
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

        viewModel = ViewModelProvider(this, factory)
                .get(ResourceDetailsViewViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, { updateView(it) })
    }

    private fun updateView(resource: ResourceDetails)
    {
        context?.let {
            val adapter = ResourceDetailsAdapter(resource, it)
            binding.mainView.adapter = adapter
            adapter.onItemClicked = { c -> onItemClicked(c)}
            adapter.notifyDataSetChanged()
        }
    }

}
