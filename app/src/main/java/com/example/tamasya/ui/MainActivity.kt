package com.example.tamasya.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tamasya.R
import com.example.tamasya.adapter.NewsAdapter
import com.example.tamasya.databinding.ActivityMainBinding
import com.example.tamasya.helper.LoadingDialog

class MainActivity : AppCompatActivity() {
    // binding
    private lateinit var binding: ActivityMainBinding
    // view model
    private lateinit var mainViewModel: MainViewModel
    // adapter
    private lateinit var newsAdapter: NewsAdapter
    private var isListView = true
    // dialog
    private lateinit var loadingDialog: LoadingDialog

    private fun clearSearchView() {
        binding.searchView.setQuery("", false)
        newsAdapter.filter("") // Show all data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toggle view between list and grid when btn_list is clicked
        binding.toggleButton.setOnClickListener {
            isListView = !isListView
            toggleRecyclerViewLayout()
        }

        // Setting up the RecyclerView for displaying data
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(viewType = NewsAdapter.VIEW_TYPE_LIST) // you can also use use viewType = KanjiAdapter.VIEW_TYPE_GRID
        recyclerView.adapter = newsAdapter

        // loading dialog
        loadingDialog = LoadingDialog(this) // Initialize loading dialog

        // Setting up ViewModel
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Show loading dialog before making the API call
        loadingDialog.show()
        mainViewModel.getNews()

        mainViewModel.response.observe(this) { newsList ->
            loadingDialog.dismiss() // Hide dialog after getting a response
            newsAdapter.setData(ArrayList(newsList))
        }

        // Clear search view and show all data initially
        clearSearchView()

        // Set up search view listener
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    newsAdapter.filter(it)
                }
                return true
            }
        })
    }

    private fun toggleRecyclerViewLayout() {
        val recyclerView = binding.recyclerView

        if (isListView) {
            recyclerView.layoutManager = LinearLayoutManager(this)
            binding.toggleButton.setImageResource(R.drawable.round_list_view)
            newsAdapter = NewsAdapter(initialData = ArrayList(), viewType = NewsAdapter.VIEW_TYPE_LIST)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            binding.toggleButton.setImageResource(R.drawable.round_grid_view)
            newsAdapter = NewsAdapter(initialData = ArrayList(), viewType = NewsAdapter.VIEW_TYPE_GRID)
        }

        recyclerView.adapter = newsAdapter
        newsAdapter.setData(ArrayList(mainViewModel.response.value!!)) // reset data to adapter
    }

}