package com.example.quickseries.ui.fragments.resources

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickseries.databinding.ListItemBinding
import com.example.quickseries.intefaces.views.IClickCallback
import com.example.quickseries.models.Category
import com.example.quickseries.models.Resource


class ResourceAdapter(private val model: Category, private val context: Context)
    : RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>(), IClickCallback<Resource>
{
    override var onItemClicked: ((Resource) -> Unit)? = null
    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, res: Int): ResourceViewHolder
    {
        binding = ListItemBinding.inflate(LayoutInflater.from(context), viewGroup, false)
        val md = ResourceViewHolder(binding)
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

    class ResourceViewHolder(private val binding1: ListItemBinding) : RecyclerView.ViewHolder(binding1.root), View.OnClickListener, IClickCallback<View>
    {
        override var onItemClicked: ((View) -> Unit)? = null
        init
        {
            binding1.root.setOnClickListener(this)
        }

        override fun onClick(v: View?)
        {
            val view = v ?: return
            onItemClicked?.invoke(view)
        }

        fun bindData(data: Resource, pos: Int)
        {
            binding1.textDesc.text = data.name
            binding1.textId.text = Html.fromHtml(data.htmlDesc)
            binding1.root.tag = pos
        }

    }
}