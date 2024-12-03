package com.example.testtask

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.adapter.PostAdapter
import com.example.testtask.databinding.ActivityMainBinding
import com.example.testtask.network.ApiServiceFactory
import com.example.testtask.viewmodel.PostViewModel
import com.example.testtask.viewmodel.PostViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    // Create ApiService instance
    private val apiService = ApiServiceFactory.create()

    // Initialize ViewModel with the factory
    private val postViewModel: PostViewModel by viewModels {
        PostViewModelFactory(apiService)  // Pass ApiService to the factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        setupRecyclerView()

        // Observe LiveData from ViewModel
        observeViewModel()

        // Fetch posts from API
        postViewModel.fetchPosts()
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter(emptyList())
        binding.rvPosts.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = postAdapter
        }
    }

    private fun observeViewModel() {
        // Observe the posts data
        postViewModel.posts.observe(this) { posts ->
            postAdapter.updatePosts(posts)
        }

        // Observe the error messages
        postViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

