package com.example.quickseries.ui.fragments.resources

import android.text.Html
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickseries.R
import com.example.quickseries.extensions.inflate
import com.example.quickseries.intefaces.views.IClickCallback
import com.example.quickseries.models.Category
import com.example.quickseries.models.Resource
import kotlinx.android.synthetic.main.list_item.view.*


class ResourceAdapter(private val model: Category)
    : RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>(), IClickCallback<Resource>
{
    override var onItemClicked: ((Resource) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, res: Int): ResourceViewHolder
    {
        val view = viewGroup.inflate(R.layout.list_item, false)
        val md = ResourceViewHolder(view)
        md.onItemClicked = {v -> onItemClicked(v)}

        return md
    }

    private fun onItemClicked(view: View)
    {
        val pos = view.tag as? Int ?: return

        val data = model.resources[pos]
        onItemClicked?.invoke(data)
    }

    override fun getItemCount(): Int = model.resources.size

    override fun onBindViewHolder(holder: ResourceViewHolder, pos: Int)
    {
        val data = model.resources[pos]
        holder.bindData(data, pos)
    }

    class ResourceViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener, IClickCallback<View>
    {
        override var onItemClicked: ((View) -> Unit)? = null
        init
        {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?)
        {
            val view = v ?: return
            onItemClicked?.invoke(view)
        }

        fun bindData(data: Resource, pos: Int)
        {
            itemView.textDesc.text = data.name
            itemView.textId.text = Html.fromHtml(data.htmlDesc)
            itemView.tag = pos
        }

    }
}