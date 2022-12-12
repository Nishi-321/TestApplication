package com.example.testapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
class ListActivity : AppCompatActivity(), ListAdapter.ItemClickListner {
    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var listAdapter: ListAdapter
    private var pagecount = 1
    var alertDialog: AlertDialog? = null
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
        listAdapter = ListAdapter(this)
        binding.rcv.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listAdapter
        }
    }

    /**
     * This is the ovverride method of adapter class where we initialize the item click of the list
     * and shows the alert dialog with app name and description
     *
     * @param description
     * @author Nishikanta
     */
    override fun onItemClicked(description: String) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Test App")
        //set message for alert dialog
        builder.setMessage(description)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Okay") { dialogInterface, which ->
            alertDialog?.cancel()
        }

        // Create the AlertDialog
        alertDialog = builder.create()
        // Set other dialog properties
        alertDialog?.setCancelable(false)
        alertDialog?.show()
    }


}