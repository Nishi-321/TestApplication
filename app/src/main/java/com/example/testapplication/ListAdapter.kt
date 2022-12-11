package com.example.testapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.databinding.ListItemLayoutBinding

/**
 * This is the adatpter class of the recyclerview to populate the data in recyclerview through
 * view holder design pattern
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var mList = ArrayList<ListModel>()
    fun setMList(list: List<ListModel>) {
        this.mList = list as ArrayList<ListModel>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mList[position].download_url)
            .into(holder.binding.imgView)
        holder.binding.txtTitle.text = mList[position].author
        holder.binding.txtDescription.text = mList[position].download_url
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}