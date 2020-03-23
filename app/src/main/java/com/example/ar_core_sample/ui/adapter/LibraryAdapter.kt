package com.example.ar_core_sample.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ar_core_sample.R
import com.example.ar_core_sample.data.model.ImageModel
import kotlinx.android.synthetic.main.item_library.view.*

class LibraryAdapter :
    RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {

    private var libraryImageList = ArrayList<ImageModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_library, parent, false)
        return LibraryViewHolder(root)
    }

    override fun getItemCount(): Int {
        return libraryImageList.size
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.itemView.ivContent.setImageBitmap(libraryImageList[position].image)
    }

    inner class LibraryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setImageData(imageList: ArrayList<ImageModel>) {
        libraryImageList.clear()
        libraryImageList.addAll(imageList)
        notifyDataSetChanged()
        Log.d("adapter", libraryImageList.size.toString())
    }
}