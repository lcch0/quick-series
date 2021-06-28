package com.example.quickseries.ui.fragments.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickseries.databinding.ListItemBinding
import com.example.quickseries.intefaces.views.IClickCallback
import com.example.quickseries.models.Category
import com.example.quickseries.models.CategoryList


class CategoryAdapter(private val model: CategoryList, private val context: Context)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(), IClickCallback<Category>
{
    private lateinit var binding: ListItemBinding

    override var onItemClicked: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, res: Int): CategoryViewHolder
    {
        binding = ListItemBinding.inflate(LayoutInflater.from(context), viewGroup, false)
        val md = CategoryViewHolder(binding)
        md.onItemClicked = {v -> onItemClicked(v)}

        return md
    }

    private fun onItemClicked(view: View)
    {
        val pos = view.tag as? Int ?: return

        val data = model.categories[pos]
        onItemClicked?.invoke(data)
    }

    override fun getItemCount(): Int = model.categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, pos: Int)
    {
        val data = model.categories[pos]
        holder.bindData(data, pos)
    }

    class CategoryViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener, IClickCallback<View>
    {
        override var onItemClicked: ((View) -> Unit)? = null
        init
        {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?)
        {
            val view = v ?: return
            onItemClicked?.invoke(view)
        }

        fun bindData(data: Category, pos: Int)
        {
            binding.textDesc.text = data.name
            binding.textId.text = data.id.toString()
            binding.root.tag = pos
        }

    }
}