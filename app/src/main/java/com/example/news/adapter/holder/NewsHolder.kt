package com.example.news.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.GridItemBinding
import com.example.news.databinding.ListItemBinding
import com.example.news.extention.getTimeAgo
import com.example.news.extention.loadImage
import com.example.news.model.DataItem

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