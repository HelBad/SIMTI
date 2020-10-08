package com.example.projectaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_tokopedia.*
import me.biubiubiu.justifytext.library.JustifyTextView

class ActivityTokopedia : AppCompatActivity() {

    lateinit var linkTokopedia: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tokopedia)

        linkTokopedia = findViewById(R.id.linkTokopedia) as TextView
        btnBack.setOnClickListener {
            finish()
        }

        val label =
            ("       Tokopedia merupakan perusahaan teknologi Indonesia dengan misi mencapai pemerataan ekonomi secara digital. Sejak didirikan pada tahun 2009, Tokopedia telah bertransformasi menjadi sebuah unicorn yang berpengaruh tidak hanya di Indonesia tetapi juga di Asia Tenggara.\n" +
                    "       Tokopedia memiliki bisnis marketplace terdepan di Indonesia yang memungkinkan setiap individu, toko kecil, dan brand untuk membuka dan mengelola toko daring. Hingga saat ini, Tokopedia menjadi marketplace yang paling banyak dikunjungi oleh masyarakat Indonesia. Sejak diluncurkan, layanan dasar Tokopedia dapat digunakan oleh semua orang secara gratis.\n" +
                    "       Dengan visi : Membangun sebuah ekosistem dimana siapa pun bisa memulai dan menemukan apapun, Tokopedia telah memberdayakan jutaan pedagang dan konsumen untuk berpartisipasi dalam masa depan perekonomian. Tokopedia secara konsisten mendukung para pelaku Usaha Mikro Kecil dan Menengah (UMKM) dan perorangan untuk mengembangkan usaha mereka dengan memasarkan produk secara daring.\n" +
                    "\n" +
                    "Tokopedia\n" +
                    "Tokopedia Tower Ciputra World 2,\n" +
                    "Jl. Prof. DR. Satrio No.Kav. 11, RT.3/RW.3,\n" +
                    "Karet Semanggi, Setia Budi,\n" +
                    "Kota Jakarta Selatan,\n" +
                    "Daerah Khusus Ibukota Jakarta 12950\n")
        val txtLabel = findViewById(R.id.textTokopedia) as JustifyTextView
        txtLabel.setText(label)

        linkTokopedia.setText("Informasi lebih lanjut tentang Magang, klik link berikut https://www.tokopedia.com/careers/function/internship/")
        Linkify.addLinks(linkTokopedia, Linkify.ALL)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }
}