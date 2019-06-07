package com.example.quickseries.ui.fragments.categories

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickseries.R
import com.example.quickseries.extensions.inflate
import com.example.quickseries.intefaces.views.IClickCallback
import com.example.quickseries.models.Category
import com.example.quickseries.models.CategoryList
import kotlinx.android.synthetic.main.list_item.view.*


class CategoryAdapter(private val model: CategoryList)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(), IClickCallback<Category>
{
    override var onItemClicked: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, res: Int): CategoryViewHolder
    {
        val view = viewGroup.inflate(R.layout.list_item, false)
        val md = CategoryViewHolder(view)
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

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener, IClickCallback<View>
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

        fun bindData(data: Category, pos: Int)
        {
            itemView.textDesc.text = data.name
            itemView.textId.text = data.id.toString()
            itemView.tag = pos
        }

    }
}