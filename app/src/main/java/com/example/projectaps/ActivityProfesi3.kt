package com.example.projectaps

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profesi3.*
import java.io.ByteArrayOutputStream

class ActivityProfesi3 : AppCompatActivity() {

    lateinit var mLayoutManager: LinearLayoutManager //for sorting
    lateinit var mSharedPref: SharedPreferences //for saving sort settings
    lateinit var mRecyclerView: RecyclerView
    lateinit var mFirebaseDatabase: FirebaseDatabase
    lateinit var mRef: DatabaseReference
    lateinit var databaseReference: DatabaseReference
    lateinit var textBarProfesi:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profesi3)

        setSupportActionBar(toolbarProfesi)
        btnBack.setOnClickListener {
            finish()
        }

        textBarProfesi = findViewById(R.id.textBarProfesi3) as TextView
        databaseReference = FirebaseDatabase.getInstance().getReference()
        val query = databaseReference.child("mhs").orderByChild("nama").equalTo(intent.getStringExtra("nama"))
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot != null)
                {
                    for (snapshot1 in datasnapshot.getChildren())
                    {
                        val allocation = snapshot1.getValue(Akun::class.java)
                        textBarProfesi.setText(allocation!!.email)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE)
        val mSorting = mSharedPref.getString("Sort", "a-z")
        if (mSorting == "a-z")
        {
            mLayoutManager = LinearLayoutManager(this)
            mLayoutManager.setReverseLayout(true)
            mLayoutManager.setStackFromEnd(true)
        }
        else if (mSorting == "z-a")
        {
            mLayoutManager = LinearLayoutManager(this)
            mLayoutManager.setReverseLayout(false)
            mLayoutManager.setStackFromEnd(false)
        }

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(mLayoutManager)

        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mRef = mFirebaseDatabase.getReference("profesi").child("databaseadministrator")
    }

    private fun firebaseSearch(searchText:String) {

        val firebaseSearchQuery = mRef.orderByChild("search").startAt(searchText).endAt(searchText + "\uf8ff")
        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<ListProfesi, ViewHolder>(
            ListProfesi::class.java,
            R.layout.row,
            ViewHolder::class.java,
            firebaseSearchQuery
        ) {
            override fun populateViewHolder(viewHolder:ViewHolder, model:ListProfesi, position:Int) {
                viewHolder.setDetails(getApplicationContext(), model.nama, model.deskripsi, model.image, model.grade, model.standart)
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):ViewHolder {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewHolder.ClickListener {
                    override fun onItemClick(view: View, position:Int) {

                        val mNamaTv = view.findViewById(R.id.rNamaTv) as TextView
                        val mDeskripsiTv = view.findViewById(R.id.rDeskripsiTv) as TextView
                        val mImageView = view.findViewById(R.id.rImageView) as ImageView
                        val mGradeTv = view.findViewById(R.id.rGradeTv) as TextView
                        val mStandartTv = view.findViewById(R.id.rStandartTv) as TextView

                        val mNama = mNamaTv.getText().toString()
                        val mDeskripsi = mDeskripsiTv.getText().toString()
                        val mGrade = mGradeTv.getText().toString()
                        val mStandart = mStandartTv.getText().toString()
                        val mDrawable = mImageView.getDrawable()
                        val mBitmap = (mDrawable as BitmapDrawable).getBitmap()

                        val intent = Intent(view.getContext(), ActivityDetail::class.java)
                        val stream = ByteArrayOutputStream()
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        val bytes = stream.toByteArray()
                        intent.putExtra("image", bytes)
                        intent.putExtra("nama", mNama)
                        intent.putExtra("deskripsi", mDeskripsi)
                        intent.putExtra("grade", mGrade)
                        intent.putExtra("standart", mStandart)
                        intent.putExtra("email", textBarProfesi.getText().toString())
                        startActivity(intent)
                    }
                    override fun onItemLongClick(view:View, position:Int) {

                    }
                })
                return viewHolder
            }
        }
        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter)
    }

    override fun onStart() {
        super.onStart()
        val query = mFirebaseDatabase.getReference("profesi").child("databaseadministrator")
        val firebaseRecyclerAdapter = object:FirebaseRecyclerAdapter<ListProfesi, ViewHolder>(
            ListProfesi::class.java,
            R.layout.row,
            ViewHolder::class.java,
            query
        ) {
            override fun populateViewHolder(viewHolder:ViewHolder, model:ListProfesi, position:Int) {
                viewHolder.setDetails(getApplicationContext(), model.nama, model.deskripsi, model.image, model.grade, model.standart)
            }
            override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewHolder {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewHolder.ClickListener {
                    override fun onItemClick(view:View, position:Int) {

                        val mTitleTv = view.findViewById(R.id.rNamaTv) as TextView
                        val mDescTv = view.findViewById(R.id.rDeskripsiTv) as TextView
                        val mImageView = view.findViewById(R.id.rImageView) as ImageView
                        val mGradeTv = view.findViewById(R.id.rGradeTv) as TextView
                        val mStandartTv = view.findViewById(R.id.rStandartTv) as TextView

                        val mTitle = mTitleTv.getText().toString()
                        val mDesc = mDescTv.getText().toString()
                        val mGrade = mGradeTv.getText().toString()
                        val mStandart = mStandartTv.getText().toString()
                        val mDrawable = mImageView.getDrawable()
                        val mBitmap = (mDrawable as BitmapDrawable).getBitmap()

                        val intent = Intent(view.getContext(), ActivityDetail::class.java)
                        val stream = ByteArrayOutputStream()
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        val bytes = stream.toByteArray()
                        intent.putExtra("image", bytes)
                        intent.putExtra("nama", mTitle)
                        intent.putExtra("deskripsi", mDesc)
                        intent.putExtra("grade", mGrade)
                        intent.putExtra("standart", mStandart)
                        intent.putExtra("email", textBarProfesi.getText().toString())
                        startActivity(intent)
                    }
                    override fun onItemLongClick(view:View, position:Int) {

                    }
                })
                return viewHolder
            }
        }
        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter)
    }
    override fun onCreateOptionsMenu(menu: Menu):Boolean {
        getMenuInflater().inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.getActionView() as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                firebaseSearch(query)
                return false
            }
            override fun onQueryTextChange(newText:String):Boolean {
                firebaseSearch(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        val id = item.getItemId()
        if (id == R.id.action_sort)
        {
            showSortDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun showSortDialog() {
        val sortOptions = arrayOf<String>(" A-Z", " Z-A")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sort by")
            .setIcon(R.drawable.icon_sort)
            .setItems(sortOptions, object: DialogInterface.OnClickListener {
                override fun onClick(dialog:DialogInterface, which:Int) {
                    if (which == 0)
                    {
                        val editor = mSharedPref.edit()
                        editor.putString("Sort", "a-z")
                        editor.apply()
                        recreate() //restart activity to take effect
                    }
                    else if (which == 1)
                    {
                        run {
                            val editor = mSharedPref.edit()
                            editor.putString("Sort", "z-a")
                            editor.apply()
                            recreate() //restart activity to take effect
                        }
                    }
                }
            })
        builder.show()
    }
}