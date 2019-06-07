package com.example.quickseries.ui.fragments.resourcedetails

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickseries.R
import com.example.quickseries.extensions.inflate
import com.example.quickseries.intefaces.views.IClickCallback
import com.example.quickseries.models.Address
import com.example.quickseries.models.ResourceDetails
import kotlinx.android.synthetic.main.address_item.view.*
import kotlinx.android.synthetic.main.contacts_item.view.*
import kotlinx.android.synthetic.main.contacts_item.view.textValue
import kotlinx.android.synthetic.main.details_item.view.*


class ResourceDetailsAdapter(private val model: ResourceDetails)
    : RecyclerView.Adapter<ResourceDetailsAdapter.ResourceDetailsViewHolder>(), IClickCallback<View>
{
    override var onItemClicked: ((View) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, res: Int): ResourceDetailsViewHolder
    {
        val view = viewGroup.inflate(R.layout.details_item, false)
        val md = ResourceDetailsViewHolder(view, onItemClicked)

        return md
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ResourceDetailsViewHolder, pos: Int)
    {
        holder.bindData(model)
    }

    class ResourceDetailsViewHolder(view: View, private var callback: ((View) -> Unit)?) : RecyclerView.ViewHolder(view)
    {
        fun bindData(data: ResourceDetails)
        {
            //this is not completed and should be redone
            val localAddress = data.address?: mutableListOf()
            if(localAddress.isEmpty())
                localAddress.add(Address())

            val address = localAddress[0]
            itemView.textDetailsTitle.text = address.label
            itemView.addressPart.textValue.text = data.getAddressString()

            initButton(itemView.addressPart.locationBtn, address.geoCoordinates)

            itemView.contactsPart.textValue.text = data.getContactsString()

            var value = data.contactInfo?.website
            initButton(itemView.contactsPart.webBtn, value)

            value = data.contactInfo?.email
            initButton(itemView.contactsPart.emailBtn, value)

            value = data.contactInfo?.phoneNumber
            initButton(itemView.contactsPart.msgBtn, value)
            initButton(itemView.contactsPart.callBtn, value)
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