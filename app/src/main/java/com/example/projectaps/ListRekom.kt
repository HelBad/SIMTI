package com.example.projectaps

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListRekom(itemView: View, private val mContext : Context) : RecyclerView.ViewHolder(itemView) {
    private val imgPromo : ImageView
    init {
        imgPromo = itemView.findViewById<View>(R.id.imgPromo) as ImageView
    }
    fun index(item : Int) {
        Glide.with(mContext).load(item).into(imgPromo)
    }
}