package com.example.testapplication

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testapplication.databinding.ListItemLayoutBinding


/**
 * This is the adatpter class of the recyclerview to populate the data in recyclerview through
 * view holder design pattern
 */
class ListAdapter(private val listner: ItemClickListner) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
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
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_menu_report_image)
        requestOptions.error(R.drawable.ic_delete)
        Glide.with(holder.itemView)
            .setDefaultRequestOptions(requestOptions)
            .load(mList[position].download_url)
            .into(holder.binding.imgView);
        holder.binding.txtTitle.text = mList[position].author
        holder.binding.txtDescription.text = mList[position].download_url
        holder.binding.container.setOnClickListener{
            listner.onItemClicked(mList[position].download_url)
        }
    }

    /**
     * This is the override method of the list adapter which returns the size of the list that is
     * being populated
     *
     * @author Nishikanta
     */
    override fun getItemCount(): Int {
        return mList.size
    }

    /**
     * This is the interface class we used to communicate between list adapter and our list activity
     * Through this interface we pass hte description message to our activity
     *
     * @author Nishikanta
     */
    interface ItemClickListner {
        fun onItemClicked(description : String)
    }

}