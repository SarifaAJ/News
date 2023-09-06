package com.example.tamasya.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tamasya.databinding.GridItemBinding
import com.example.tamasya.databinding.ListItemBinding
import com.example.tamasya.extention.getTimeAgo
import com.example.tamasya.extention.loadImage
import com.example.tamasya.model.DataItem

interface NewsHolderInterface {
    fun bindData(data: DataItem)
}

// holder for list view
class NewsListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), NewsHolderInterface {
    private val binding = ListItemBinding.bind(itemView)

    override fun bindData(data: DataItem) {
        with(binding) {
            // title
            title.text = data.title
            // category
            data.newsCategory.let {
                category.text = it.title
            } ?: run {
                category.text = "-"
            }
            // date
            date.text = data.updatedAt.getTimeAgo()
            // image
            image.loadImage(data.thumb)
        }
    }
}

// holder for grid view
class NewsGridItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), NewsHolderInterface {
    private val binding = GridItemBinding.bind(itemView)

    override fun bindData(data: DataItem) {
        with(binding) {
            // title
            title.text = data.title
            // category
            data.newsCategory.let {
                category.text = it.title
            }
            // date
            date.text = data.createdAt.getTimeAgo()
            // image
            image.loadImage(data.thumb)
        }
    }
}