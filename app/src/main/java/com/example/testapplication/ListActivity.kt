package com.example.testapplication

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityListBinding

/**
 *
 * This is the list activity that is created to populate the list that is being fetched from the API
 * through view model
 *
 * @author Nishikanta
 */
class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var listAdapter: ListAdapter
    private var pagecount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        binding.idPBLoading.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.getPopularMovies(pagecount.toString(), "20")
        viewModel.observeMovieLiveData().observe(this, Observer { mList ->
            listAdapter.setMList(mList)
            binding.idPBLoading.visibility = View.GONE
            binding.swipeToRefresh.isRefreshing = false
        })
        binding.swipeToRefresh.setOnRefreshListener {
            if (pagecount <= 20) {
                pagecount++
                binding.idPBLoading.visibility = View.VISIBLE
                viewModel.getPopularMovies(pagecount.toString(), "20")
            } else {
                Toast.makeText(
                    this,
                    "No More Data To Load. Taking to the first 20 item",
                    Toast.LENGTH_SHORT
                ).show()
                pagecount = 1
                binding.idPBLoading.visibility = View.VISIBLE
                viewModel.getPopularMovies(pagecount.toString(), "20")
            }
        }
    }

    /**
     * This method is used to prepare the recyclerview with set the adapter to recyclerview
     *
     * @author Nishikanta
     */
    private fun prepareRecyclerView() {
        listAdapter = ListAdapter()
        binding.rcv.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listAdapter
        }
    }
}