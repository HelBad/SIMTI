package com.example.projectaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_gojek.*
import me.biubiubiu.justifytext.library.JustifyTextView

class ActivityGojek : AppCompatActivity() {

    lateinit var linkGojek: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gojek)

        linkGojek = findViewById(R.id.linkGojek) as TextView
        btnBack.setOnClickListener {
            finish()
        }

        val label =
            ("       Gojek (sebelumnya ditulis GO-JEK) merupakan sebuah perusahaan teknologi asal Indonesia yang melayani angkutan melalui jasa ojek. Perusahaan ini didirikan pada tahun 2010 di Jakarta oleh Nadiem Makarim. Saat ini, Gojek telah tersedia di 50 kota di Indonesia. Hingga bulan Juni 2016, aplikasi Gojek sudah diunduh sebanyak hampir 10 juta kali di Google Play pada sistem operasi Android, dan telah tersedia di App Store. Gojek juga mempunyai layanan pembayaran digital yang bernama Gopay. Layanan Gojek kini telah tersedia di Thailand, Vietnam dan Singapura.\n" +
                    "       Berawal dari layanan transportasi, sekarang aplikasi Gojek memiliki lebih dari 20 layanan yang menjadi solusi buat tantangan sehari-hari. Berkat itu juga, Gojek menjadi salah satu platform teknologi terbesar yang melayani jutaan pengguna di Asia Tenggara dengan mengembangkan tiga Super-app: untuk customer, untuk mitra driver, dan juga mitra merchant.\n" +
                    "\n" +
                    "Kantor Pusat\n" +
                    "Pasaraya Blok M Gedung B Lt. 6,\n" +
                    "Jalan Iskandarsyah II No.7, RW. 2,\n" +
                    "Melawai, Kebayoran Baru, RT.3/RW.1,\n" +
                    "Melawai, Kby. Baru, Kota Jakarta Selatan,\n" +
                    "Daerah Khusus Ibukota Jakarta 12160\n")
        val txtLabel = findViewById(R.id.textGojek) as JustifyTextView
        txtLabel.setText(label)

        linkGojek.setText("Informasi lebih lanjut tentang Magang, klik link berikut https://career.go-jek.com/")
        Linkify.addLinks(linkGojek, Linkify.ALL)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }
}