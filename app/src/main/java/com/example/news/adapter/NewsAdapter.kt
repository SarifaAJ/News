package com.example.news.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.GridItemBinding
import com.example.news.databinding.ListItemBinding
import com.example.news.adapter.holder.NewsGridItemHolder
import com.example.news.adapter.holder.NewsHolderInterface
import com.example.news.adapter.holder.NewsListItemHolder
import com.example.news.model.DataItem
import com.example.news.ui.detail.DetailActivity

class NewsAdapter(private var initialData: ArrayList<DataItem> = ArrayList(), private val viewType: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = ArrayList(initialData)
    private val filteredData = ArrayList<DataItem>()

    init {
        filteredData.addAll(initialData)
    }

    companion object {
        const val VIEW_TYPE_LIST = 1
        const val VIEW_TYPE_GRID = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            //  binding for list view
            VIEW_TYPE_LIST -> {
                val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewsListItemHolder(binding.root)
            }
            // //  binding for grid view
            VIEW_TYPE_GRID -> {
                val binding = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewsGridItemHolder(binding.root)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = filteredData[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("news_id", dataItem.id) // Pass the level ID to DetailActivity
            holder.itemView.context.startActivity(intent)
        }

        if (holder is NewsHolderInterface) {
            holder.bindData(dataItem)
        }
    }

    // filter data for search view
    fun filter(query: String) {
        filteredData.clear()

        if (query.isEmpty()) {
            filteredData.addAll(data)
        } else {
            val lowerCaseQuery = query.toLowerCase()
            for (item in data) {
                if (item.title.toLowerCase().contains(lowerCaseQuery)) {
                    filteredData.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }
    // set data
    fun setData(newData: ArrayList<DataItem>) {
        data.clear()
        data.addAll(newData)

        filteredData.clear()
        filteredData.addAll(data)

        notifyDataSetChanged()
    }
}