package com.example.projectaps

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_utama.*

class ActivityUtama : AppCompatActivity() {

    lateinit var alertDialog: AlertDialog.Builder

    //Bottom Navigation
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.beranda -> {
                println("Beranda")
                replaceFragment(FragmentBeranda())
                return@OnNavigationItemSelectedListener true
            }
            R.id.cari -> {
                println("Cari")
                replaceFragment(FragmentCari())
                return@OnNavigationItemSelectedListener true
            }
            R.id.unicorn -> {
                println("Unicorn")
                replaceFragment(FragmentUnicorn())
                return@OnNavigationItemSelectedListener true
            }
            R.id.berkas -> {
                println("Berkas")
                replaceFragment(FragmentBerkas())
                return@OnNavigationItemSelectedListener true
            }
            R.id.akun -> {
                println("Akun")
                replaceFragment(FragmentAkun())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama)

        alertDialog = AlertDialog.Builder(this)

        //Bottom Navigation
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(FragmentBeranda())
    }

    //Bottom Navigation
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }

    override fun onBackPressed() {
        Toast.makeText(this@ActivityUtama, "Back is Clicked", Toast.LENGTH_SHORT).show()
        alertDialog.setTitle("Close Application")
        alertDialog.setMessage("Do you want to close the application ?")
            .setCancelable(false)
            .setPositiveButton("YES", object: DialogInterface.OnClickListener {
                override fun onClick(dialog:DialogInterface, id:Int) {
                    finishAffinity()
                }
            })
            .setNegativeButton("NO", object: DialogInterface.OnClickListener {
                override fun onClick(dialog:DialogInterface, id:Int) {
                    dialog.cancel()
                }
            }).create().show()
    }
}