package com.example.news.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.news.databinding.ActivityDetailBinding
import com.example.news.extention.getTimeAgo
import com.example.news.extention.loadImage
import com.example.news.extention.parseHtml
import com.example.news.helper.LoadingDialog
import com.example.news.model.DataItem

class DetailActivity : AppCompatActivity() {
    // binding
    private lateinit var binding: ActivityDetailBinding
    // view model
    private lateinit var detailViewModel: DetailViewModel
    private var dataItem: DataItem? = null
    // dialog
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back to MainActivity
        binding.backBtn.setOnClickListener {
            finish()
        }

        // loading dialog
        loadingDialog = LoadingDialog(this) // Initialize loading dialog

        // Setting up ViewModel
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        // Show loading dialog before making the API call
        val newsId = intent.getStringExtra("news_id")
        newsId?.let {
            loadingDialog.show()
            detailViewModel.getDetail(it)
        }

        detailViewModel.response.observe(this) { newsData ->
            loadingDialog.dismiss() // Hide dialog after getting a response
            showData(newsData)
        }
    }

    private fun showData(data: DataItem) {
        dataItem = data
        // title
        binding.title2.text = data.title
        // image
        binding.image2.loadImage(data.thumb)
        // category
        data.newsCategory.let {
            binding.category.text = it.title
        }
        // date
        binding.date.text = data.createdAt.getTimeAgo()
        // description
        binding.description2.text = data.content.parseHtml()
    }
}