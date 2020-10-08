package com.example.projectaps

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterRekom(private val image : IntArray, private val mContext : Context) :
    RecyclerView.Adapter<ListRekom>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRekom {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_rekom, parent, false)
        return ListRekom(v, mContext)
    }

    override fun onBindViewHolder(holder: ListRekom, position: Int) {
        holder.index(image[position])
    }

    override fun getItemCount(): Int {
        return image.size
    }
}