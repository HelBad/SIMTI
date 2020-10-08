package com.example.projectaps

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    internal var mView:View
    private var mClickListener: ClickListener? = null

    init{
        mView = itemView
        //item click
        itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view:View) {
                mClickListener!!.onItemClick(view, getAdapterPosition())
            }
        })
        //item long click
        itemView.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(view:View):Boolean {
                mClickListener!!.onItemLongClick(view, getAdapterPosition())
                return true
            }
        })
    }
    //set details to recycler view row
    fun setDetails(ctx: Context, nama:String, deskripsi:String, image:String, grade:String, standart:String) {
        //Views
        val mNamaTv = mView.findViewById(R.id.rNamaTv) as TextView
        val mDetailTv = mView.findViewById(R.id.rDeskripsiTv) as TextView
        val mImageIv = mView.findViewById(R.id.rImageView) as ImageView
        val mGradeTv = mView.findViewById(R.id.rGradeTv) as TextView
        val mStandartTv = mView.findViewById(R.id.rStandartTv) as TextView
        //set data to views
        mNamaTv.setText(nama)
        mDetailTv.setText(deskripsi)
        mGradeTv.setText(grade)
        mStandartTv.setText(standart)
        Picasso.get().load(image).into(mImageIv)
    }
    //interface to send callbacks
    interface ClickListener {
        fun onItemClick(view:View, position:Int)
        fun onItemLongClick(view:View, position:Int)
    }
    fun setOnClickListener(clickListener:ViewHolder.ClickListener) {
        mClickListener = clickListener
    }
}