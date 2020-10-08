package com.example.projectaps

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import java.io.ByteArrayOutputStream

class FragmentCari : Fragment() {

    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mRecyclerView: RecyclerView
    lateinit var mFirebaseDatabase: FirebaseDatabase
    lateinit var mRef: DatabaseReference
    lateinit var databaseReference: DatabaseReference
    lateinit var textBarCari:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_cari, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val actionBar = getActivity()!!.findViewById(R.id.toolbarCari) as Toolbar
        (getActivity() as AppCompatActivity).setSupportActionBar(actionBar)
        textBarCari = getActivity()!!.findViewById(R.id.textBarCari) as TextView
        textBarCari.setText(activity!!.intent.getStringExtra("email"))

        mLayoutManager = LinearLayoutManager(activity!!)
        //this will load the items from bottom means z-a first
        mLayoutManager.setReverseLayout(false)
        mLayoutManager.setStackFromEnd(false)

        //RecyclerView
        mRecyclerView = view!!.findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager)
        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mRef = mFirebaseDatabase.getReference("perusahaan")
    }
    //search data
    private fun firebaseSearch(searchText:String) {
        //convert string entered in SearchView to lowercase
        val firebaseSearchQuery = mRef.orderByChild("search").startAt(searchText).endAt(searchText + "\uf8ff")
        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<ListProfesi, ViewHolder>(
            ListProfesi::class.java,
            R.layout.row,
            ViewHolder::class.java,
            firebaseSearchQuery
        ) {
            override fun populateViewHolder(viewHolder:ViewHolder, model:ListProfesi, position:Int) {
                viewHolder.setDetails(activity!!.getApplicationContext(), model.nama, model.deskripsi, model.image, model.grade, model.standart)
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):ViewHolder {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewHolder.ClickListener {
                    override fun onItemClick(view:View, position:Int) {
                        //Views
                        val mNamaTv = view.findViewById(R.id.rNamaTv) as TextView
                        val mDeskripsiTv = view.findViewById(R.id.rDeskripsiTv) as TextView
                        val mImageView = view.findViewById(R.id.rImageView) as ImageView
                        val mGradeTv = view.findViewById(R.id.rGradeTv) as TextView
                        val mStandartTv = view.findViewById(R.id.rStandartTv) as TextView
                        //get data from views
                        val mNama = mNamaTv.getText().toString()
                        val mDeskripsi = mDeskripsiTv.getText().toString()
                        val mGrade = mGradeTv.getText().toString()
                        val mStandart = mStandartTv.getText().toString()
                        val mDrawable = mImageView.getDrawable()
                        val mBitmap = (mDrawable as BitmapDrawable).getBitmap()
                        //pass this data to new activity
                        val intent = Intent(view.getContext(), ActivityDetail::class.java)
                        val stream = ByteArrayOutputStream()
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        val bytes = stream.toByteArray()
                        intent.putExtra("image", bytes) //put bitmap image as array of bytes
                        intent.putExtra("nama", mNama) // put title
                        intent.putExtra("deskripsi", mDeskripsi)
                        intent.putExtra("grade", mGrade)
                        intent.putExtra("standart", mStandart)//put description
                        intent.putExtra("email", textBarCari.getText().toString())
                        startActivity(intent) //start activity
                    }
                    override fun onItemLongClick(view:View, position:Int) {
                        //TODO do your own implementaion on long item click
                    }
                })
                return viewHolder
            }
        }
        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter)
    }
    //load data into recycler view onStart
    override fun onStart() {
        super.onStart()
        val query = mFirebaseDatabase.getReference("perusahaan")
        val firebaseRecyclerAdapter = object:FirebaseRecyclerAdapter<ListProfesi, ViewHolder>(
            ListProfesi::class.java,
            R.layout.row,
            ViewHolder::class.java,
            query
//            mRef
        ) {
            override fun populateViewHolder(viewHolder:ViewHolder, model:ListProfesi, position:Int) {
                viewHolder.setDetails(activity!!.getApplicationContext(), model.nama, model.deskripsi, model.image, model.grade, model.standart)
            }
            override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewHolder {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewHolder.ClickListener {
                    override fun onItemClick(view:View, position:Int) {
                        //Views
                        val mTitleTv = view.findViewById(R.id.rNamaTv) as TextView
                        val mDescTv = view.findViewById(R.id.rDeskripsiTv) as TextView
                        val mImageView = view.findViewById(R.id.rImageView) as ImageView
                        val mGradeTv = view.findViewById(R.id.rGradeTv) as TextView
                        val mStandartTv = view.findViewById(R.id.rStandartTv) as TextView
                        //get data from views
                        val mTitle = mTitleTv.getText().toString()
                        val mDesc = mDescTv.getText().toString()
                        val mGrade = mGradeTv.getText().toString()
                        val mStandart = mStandartTv.getText().toString()
                        val mDrawable = mImageView.getDrawable()
                        val mBitmap = (mDrawable as BitmapDrawable).getBitmap()
                        //pass this data to new activity
                        val intent = Intent(view.getContext(), ActivityDetail::class.java)
                        val stream = ByteArrayOutputStream()
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        val bytes = stream.toByteArray()
                        intent.putExtra("image", bytes) //put bitmap image as array of bytes
                        intent.putExtra("nama", mTitle) // put title
                        intent.putExtra("deskripsi", mDesc) //put description
                        intent.putExtra("grade", mGrade)
                        intent.putExtra("standart", mStandart)
                        intent.putExtra("email", textBarCari.getText().toString())
                        startActivity(intent) //start activity
                    }
                    override fun onItemLongClick(view:View, position:Int) {
                        //TODO do your own implementaion on long item click
                    }
                })
                return viewHolder
            }
        }
        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //inflate the menu; this adds items to the action bar if it present
        activity!!.getMenuInflater().inflate(R.menu.menu1, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.getActionView() as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                firebaseSearch(query)
                return false
            }
            override fun onQueryTextChange(newText:String):Boolean {
                //Filter as you type
                firebaseSearch(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }
}