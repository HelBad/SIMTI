package com.example.projectaps

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class ActivityDetail : AppCompatActivity() {

    lateinit var mNamaTv: TextView
    lateinit var mDetailTv:TextView
    lateinit var mImageIv: ImageView
    lateinit var mGradeTv: TextView
    lateinit var mStandartTv: TextView
    lateinit var bitmap: Bitmap

    lateinit var ipk : TextView
    lateinit var db : TextView
    lateinit var jk : TextView
    lateinit var rpl : TextView
    lateinit var pro : TextView
    lateinit var per : TextView
    lateinit var hsl : TextView
    lateinit var btSubmit : Button
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Action bar
        val actionBar = getSupportActionBar()
        //Actionbar title
        actionBar?.setTitle("Post Detail")
        //set back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        //initialize views
        mNamaTv = findViewById(R.id.namaTv)
        mDetailTv = findViewById(R.id.deskripsiTv)
        mGradeTv = findViewById(R.id.gradeTv)
        mStandartTv = findViewById(R.id.standartTv)
        mImageIv = findViewById(R.id.imageView)
        //get data from intent
        val bytes = getIntent().getByteArrayExtra("image")
        val nama = getIntent().getStringExtra("nama")
        val desc = getIntent().getStringExtra("deskripsi")
        val grade = getIntent().getStringExtra("grade")
        val standart = getIntent().getStringExtra("standart")
        val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        //set data to views
        mNamaTv.setText(nama)
        mDetailTv.setText(desc)
        mGradeTv.setText(grade)
        mStandartTv.setText(standart)
        mImageIv.setImageBitmap(bmp)
        //get image from imageview as bitmap
        bitmap = (mImageIv.getDrawable() as BitmapDrawable).getBitmap()

        btnDaftar.setOnClickListener() {
            Toast.makeText(this@ActivityDetail, "Lanjut Ke Halaman Berkas", Toast.LENGTH_SHORT).show()
        }

        ipk = findViewById(R.id.ipk)
        db = findViewById(R.id.db)
        jk = findViewById(R.id.jk)
        rpl = findViewById(R.id.rpl)
        pro = findViewById(R.id.pro)
        pro.setText(grade)
        per = findViewById(R.id.per)
        per.setText(standart)
        hsl = findViewById(R.id.hsl)
        btSubmit = findViewById(R.id.btSubmit)

        databaseReference = FirebaseDatabase.getInstance().getReference()
        val query = databaseReference.child("mhs").orderByChild("email")
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot != null)
                {
                    for (snapshot1 in datasnapshot.getChildren())
                    {
                        val allocation = snapshot1.getValue(Akun::class.java)
                        ipk.setText(allocation!!.ipk)
                        db.setText(allocation!!.mbasisdata)
                        jk.setText(allocation!!.mjarkom)
                        rpl.setText(allocation!!.mrpl)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        btSubmit.setOnClickListener(){
            val data1 = ipk.text.toString().toDouble()
            val data2 = db.text.toString().toDouble()
            val data3 = jk.text.toString().toDouble()
            val data4 = rpl.text.toString().toDouble()
            val data5 = pro.text.toString().toDouble()
            val data6 = per.text.toString().toDouble()

            if(2.00 >= data1 && data1 >= 0.00){
                ipk.setText("1").toString()
            } else if(2.75 >= data1 && data1 > 2.00){
                ipk.setText("2").toString()
            } else if(3.00 >= data1 && data1 > 2.75){
                ipk.setText("3").toString()
            } else if(3.50 >= data1 && data1 > 3.00){
                ipk.setText("4").toString()
            } else if(4.00 >= data1 && data1 > 3.50){
                ipk.setText("5").toString()
            }
            if(2.00 >= data2 && data2 >= 0.00){
                db.setText("1").toString()
            } else if(2.75 >= data2 && data2 > 2.00){
                db.setText("2").toString()
            } else if(3.00 >= data2 && data2 > 2.75){
                db.setText("3").toString()
            } else if(3.50 >= data2 && data2 > 3.00){
                db.setText("4").toString()
            } else if(4.00 >= data2 && data2 > 3.50){
                db.setText("5").toString()
            }
            if(2.00 >= data3 && data3 >= 0.00){
                jk.setText("1").toString()
            } else if(2.75 >= data3 && data3 > 2.00){
                jk.setText("2").toString()
            } else if(3.00 >= data3 && data3 > 2.75){
                jk.setText("3").toString()
            } else if(3.50 >= data3 && data3 > 3.00){
                jk.setText("4").toString()
            } else if(4.00 >= data3 && data3 > 3.50){
                jk.setText("5").toString()
            }
            if(2.00 >= data4 && data4 >= 0.00){
                rpl.setText("1").toString()
            } else if(2.75 >= data4 && data4 > 2.00){
                rpl.setText("2").toString()
            } else if(3.00 >= data4 && data4 > 2.75){
                rpl.setText("3").toString()
            } else if(3.50 >= data4 && data4 > 3.00){
                rpl.setText("4").toString()
            } else if(4.00 >= data4 && data4 > 3.50){
                rpl.setText("5").toString()
            }
            if(2.00 >= data5 && data5 >= 0.00){
                pro.setText("1").toString()
            } else if(2.75 >= data5 && data5 > 2.00){
                pro.setText("2").toString()
            } else if(3.00 >= data5 && data5 > 2.75){
                pro.setText("3").toString()
            } else if(3.50 >= data5 && data5 > 3.00){
                pro.setText("4").toString()
            } else if(4.00 >= data5 && data5 > 3.50){
                pro.setText("5").toString()
            }
            if(2.00 >= data6 && data6 >= 0.00){
                per.setText("1").toString()
            } else if(2.75 >= data6 && data6 > 2.00){
                per.setText("2").toString()
            } else if(3.00 >= data6 && data6 > 2.75){
                per.setText("3").toString()
            } else if(3.50 >= data6 && data6 > 3.00){
                per.setText("4").toString()
            } else if(4.00 >= data6 && data6 > 3.50){
                per.setText("5").toString()
            }

            val nilai1 = ipk.text.toString().toDouble()
            val nilai2 = db.text.toString().toDouble()
            val nilai3 = jk.text.toString().toDouble()
            val nilai4 = rpl.text.toString().toDouble()
            val nilai5 = pro.text.toString().toDouble()
            val nilai6 = per.text.toString().toDouble()

            if(nilai1 >= nilai2 && nilai1 >= nilai3 && nilai1 >= nilai4 && nilai1 >= nilai5 && nilai1 >= nilai6) {
                val hasil1 = nilai1 / nilai1 * 0.3
                ipk.setText(hasil1.toString())
            } else if(nilai2 >= nilai1 && nilai2 >= nilai3 && nilai2 >= nilai4 && nilai2 >= nilai5 && nilai2 >= nilai6) {
                val hasil1 = nilai1 / nilai2 * 0.3
                ipk.setText(hasil1.toString())
            } else if(nilai3 >= nilai1 && nilai3 >= nilai2 && nilai3 >= nilai4 && nilai3 >= nilai5 && nilai3 >= nilai6) {
                val hasil1 = nilai1 / nilai3 * 0.3
                ipk.setText(hasil1.toString())
            } else if(nilai4 >= nilai1 && nilai4 >= nilai2 && nilai4 >= nilai3 && nilai4 >= nilai5 && nilai4 >= nilai6) {
                val hasil1 = nilai1 / nilai4 * 0.3
                ipk.setText(hasil1.toString())
            } else if(nilai5 >= nilai1 && nilai5 >= nilai2 && nilai5 >= nilai3 && nilai5 >= nilai4 && nilai5 >= nilai6) {
                val hasil1 = nilai1 / nilai5 * 0.3
                ipk.setText(hasil1.toString())
            } else if(nilai6 >= nilai1 && nilai6 >= nilai2 && nilai6 >= nilai3 && nilai6 >= nilai4 && nilai6 >= nilai5) {
                val hasil1 = nilai1 / nilai6 * 0.3
                ipk.setText(hasil1.toString())
            }
            if(nilai1 >= nilai2 && nilai1 >= nilai3 && nilai1 >= nilai4 && nilai1 >= nilai5 && nilai1 >= nilai6) {
                val hasil2 = nilai2 / nilai1 * 0.1
                db.setText(hasil2.toString())
            } else if(nilai2 >= nilai1 && nilai2 >= nilai3 && nilai2 >= nilai4 && nilai2 >= nilai5 && nilai2 >= nilai6) {
                val hasil2 = nilai2 / nilai2 * 0.1
                db.setText(hasil2.toString())
            } else if(nilai3 >= nilai1 && nilai3 >= nilai2 && nilai3 >= nilai4 && nilai3 >= nilai5 && nilai3 >= nilai6) {
                val hasil2 = nilai2 / nilai3 * 0.1
                db.setText(hasil2.toString())
            } else if(nilai4 >= nilai1 && nilai4 >= nilai2 && nilai4 >= nilai3 && nilai4 >= nilai5 && nilai4 >= nilai6) {
                val hasil2 = nilai2 / nilai4 * 0.1
                db.setText(hasil2.toString())
            } else if(nilai5 >= nilai1 && nilai5 >= nilai2 && nilai5 >= nilai3 && nilai5 >= nilai4 && nilai5 >= nilai6) {
                val hasil2 = nilai2 / nilai5 * 0.1
                db.setText(hasil2.toString())
            } else if(nilai6 >= nilai1 && nilai6 >= nilai2 && nilai6 >= nilai3 && nilai6 >= nilai4 && nilai6 >= nilai5) {
                val hasil2 = nilai2 / nilai6 * 0.1
                db.setText(hasil2.toString())
            }
            if(nilai1 >= nilai2 && nilai1 >= nilai3 && nilai1 >= nilai4 && nilai1 >= nilai5 && nilai1 >= nilai6) {
                val hasil3 = nilai3 / nilai1 * 0.1
                jk.setText(hasil3.toString())
            } else if(nilai2 >= nilai1 && nilai2 >= nilai3 && nilai2 >= nilai4 && nilai2 >= nilai5 && nilai2 >= nilai6) {
                val hasil3 = nilai3 / nilai2 * 0.1
                jk.setText(hasil3.toString())
            } else if(nilai3 >= nilai1 && nilai3 >= nilai2 && nilai3 >= nilai4 && nilai3 >= nilai5 && nilai3 >= nilai6) {
                val hasil3 = nilai3 / nilai3 * 0.1
                jk.setText(hasil3.toString())
            } else if(nilai4 >= nilai1 && nilai4 >= nilai2 && nilai4 >= nilai3 && nilai4 >= nilai5 && nilai4 >= nilai6) {
                val hasil3 = nilai3 / nilai4 * 0.1
                jk.setText(hasil3.toString())
            } else if(nilai5 >= nilai1 && nilai5 >= nilai2 && nilai5 >= nilai3 && nilai5 >= nilai4 && nilai5 >= nilai6) {
                val hasil3 = nilai3 / nilai5 * 0.1
                jk.setText(hasil3.toString())
            } else if(nilai6 >= nilai1 && nilai6 >= nilai2 && nilai6 >= nilai3 && nilai6 >= nilai4 && nilai6 >= nilai5) {
                val hasil3 = nilai3 / nilai6 * 0.1
                jk.setText(hasil3.toString())
            }
            if(nilai1 >= nilai2 && nilai1 >= nilai3 && nilai1 >= nilai4 && nilai1 >= nilai5 && nilai1 >= nilai6) {
                val hasil4 = nilai4 / nilai1 * 0.1
                rpl.setText(hasil4.toString())
            } else if(nilai2 >= nilai1 && nilai2 >= nilai3 && nilai2 >= nilai4 && nilai2 >= nilai5 && nilai2 >= nilai6) {
                val hasil4 = nilai4 / nilai2 * 0.1
                rpl.setText(hasil4.toString())
            } else if(nilai3 >= nilai1 && nilai3 >= nilai2 && nilai3 >= nilai4 && nilai3 >= nilai5 && nilai3 >= nilai6) {
                val hasil4 = nilai4 / nilai3 * 0.1
                rpl.setText(hasil4.toString())
            } else if(nilai4 >= nilai1 && nilai4 >= nilai2 && nilai4 >= nilai3 && nilai4 >= nilai5 && nilai4 >= nilai6) {
                val hasil4 = nilai4 / nilai4 * 0.1
                rpl.setText(hasil4.toString())
            } else if(nilai5 >= nilai1 && nilai5 >= nilai2 && nilai5 >= nilai3 && nilai5 >= nilai4 && nilai5 >= nilai6) {
                val hasil4 = nilai4 / nilai5 * 0.1
                rpl.setText(hasil4.toString())
            } else if(nilai6 >= nilai1 && nilai6 >= nilai2 && nilai6 >= nilai3 && nilai6 >= nilai4 && nilai6 >= nilai5) {
                val hasil4 = nilai4 / nilai6 * 0.1
                rpl.setText(hasil4.toString())
            }
            if(nilai1 <= nilai2 && nilai1 <= nilai3 && nilai1 <= nilai4 && nilai1 <= nilai5 && nilai1 <= nilai6) {
                val hasil5 = nilai1 / nilai5 * 0.2
                pro.setText(hasil5.toString())
            } else if(nilai2 <= nilai1 && nilai2 <= nilai3 && nilai2 <= nilai4 && nilai2 <= nilai5 && nilai2 <= nilai6) {
                val hasil5 = nilai2 / nilai5 * 0.2
                pro.setText(hasil5.toString())
            } else if(nilai3 <= nilai1 && nilai3 <= nilai2 && nilai3 <= nilai4 && nilai3 <= nilai5 && nilai3 <= nilai6) {
                val hasil5 = nilai3 / nilai5 * 0.2
                pro.setText(hasil5.toString())
            } else if(nilai4 <= nilai1 && nilai4 <= nilai2 && nilai4 <= nilai3 && nilai4 <= nilai5 && nilai4 <= nilai6) {
                val hasil5 = nilai4 / nilai5 * 0.2
                pro.setText(hasil5.toString())
            } else if(nilai5 <= nilai1 && nilai5 <= nilai2 && nilai5 <= nilai3 && nilai5 <= nilai4 && nilai5 <= nilai6) {
                val hasil5 = nilai5 / nilai5 * 0.2
                pro.setText(hasil5.toString())
            } else if(nilai6 <= nilai1 && nilai6 <= nilai2 && nilai6 <= nilai3 && nilai6 <= nilai4 && nilai6 <= nilai5) {
                val hasil5 = nilai6 / nilai5 * 0.2
                pro.setText(hasil5.toString())
            }
            if(nilai1 <= nilai2 && nilai1 <= nilai3 && nilai1 <= nilai4 && nilai1 <= nilai5 && nilai1 <= nilai6) {
                val hasil6 = nilai1 / nilai6 * 0.2
                per.setText(hasil6.toString())
            } else if(nilai2 <= nilai1 && nilai2 <= nilai3 && nilai2 <= nilai4 && nilai2 <= nilai5 && nilai2 <= nilai6) {
                val hasil6 = nilai2 / nilai6 * 0.2
                per.setText(hasil6.toString())
            } else if(nilai3 <= nilai1 && nilai3 <= nilai2 && nilai3 <= nilai4 && nilai3 <= nilai5 && nilai3 <= nilai6) {
                val hasil6 = nilai3 / nilai6 * 0.2
                per.setText(hasil6.toString())
            } else if(nilai4 <= nilai1 && nilai4 <= nilai2 && nilai4 <= nilai3 && nilai4 <= nilai5 && nilai4 <= nilai6) {
                val hasil6 = nilai4 / nilai6 * 0.2
                per.setText(hasil6.toString())
            } else if(nilai5 <= nilai1 && nilai5 <= nilai2 && nilai5 <= nilai3 && nilai5 <= nilai4 && nilai5 <= nilai6) {
                val hasil6 = nilai5 / nilai6 * 0.2
                per.setText(hasil6.toString())
            } else if(nilai6 <= nilai1 && nilai6 <= nilai2 && nilai6 <= nilai3 && nilai6 <= nilai4 && nilai6 <= nilai5) {
                val hasil6 = nilai6 / nilai6 * 0.2
                per.setText(hasil6.toString())
            }

            val total1 = ipk.text.toString().toDouble()
            val total2 = db.text.toString().toDouble()
            val total3 = jk.text.toString().toDouble()
            val total4 = rpl.text.toString().toDouble()
            val total5 = pro.text.toString().toDouble()
            val total6 = per.text.toString().toDouble()

            if (ipk.text.isNotEmpty() && db.text.isNotEmpty() && jk.text.isNotEmpty()
                && rpl.text.isNotEmpty() && pro.text.isNotEmpty() && per.text.isNotEmpty()) {
                val total = total1 + total2 + total3 + total4 + total5 + total6
                val persen = total * 100
                hsl.setText(String.format("%.2f", persen) + " %")
            }

            btSubmit.isEnabled = false
        }
    }

    //handle onBackPressed(go to previous activity)
    override fun onSupportNavigateUp():Boolean {
        onBackPressed()
        return true
    }
}