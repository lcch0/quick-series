package com.example.quickseries.ui.fragments.resourcedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickseries.databinding.DetailsItemBinding
import com.example.quickseries.intefaces.views.IClickCallback
import com.example.quickseries.models.Address
import com.example.quickseries.models.ResourceDetails


class ResourceDetailsAdapter(private val model: ResourceDetails, private val context: Context)
    : RecyclerView.Adapter<ResourceDetailsAdapter.ResourceDetailsViewHolder>(), IClickCallback<View>
{
    private lateinit var binding: DetailsItemBinding

    override var onItemClicked: ((View) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, res: Int): ResourceDetailsViewHolder
    {
        binding = DetailsItemBinding.inflate(LayoutInflater.from(context), viewGroup, false)
        return ResourceDetailsViewHolder(binding, onItemClicked)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ResourceDetailsViewHolder, pos: Int)
    {
        holder.bindData(model)
    }

    class ResourceDetailsViewHolder(private val binding: DetailsItemBinding,
                                    private var callback: ((View) -> Unit)?) : RecyclerView.ViewHolder(binding.root)
    {
        fun bindData(data: ResourceDetails)
        {
            //this is not completed and should be redone
            val localAddress = data.address?: mutableListOf()
            if(localAddress.isEmpty())
                localAddress.add(Address())

            val address = localAddress[0]
            binding.textDetailsTitle.text = address.label
            binding.addressPart.textValue.text = data.getAddressString()

            initButton(binding.addressPart.locationBtn, address.geoCoordinates)

            binding.contactsPart.textValue.text = data.getContactsString()

            var value = data.contactInfo?.website
            initButton(binding.contactsPart.webBtn, value)

            value = data.contactInfo?.email
            initButton(binding.contactsPart.emailBtn, value)

            value = data.contactInfo?.phoneNumber
            initButton(binding.contactsPart.msgBtn, value)
            initButton(binding.contactsPart.callBtn, value)
        }

        private fun <T: Any?> initButton(view: View, value: T?)
        {
            if (value == null)
            {
                view.visibility = View.GONE
            }
            else
            {
                view.visibility = View.VISIBLE
                view.tag = value
                if(callback != null)
                    view.setOnClickListener(callback)
            }
        }

    }
}