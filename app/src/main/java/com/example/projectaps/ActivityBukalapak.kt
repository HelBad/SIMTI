package com.example.projectaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_bukalapak.*
import me.biubiubiu.justifytext.library.JustifyTextView

class ActivityBukalapak : AppCompatActivity() {

    lateinit var linkBukalapak: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bukalapak)

        linkBukalapak = findViewById(R.id.linkBukalapak) as TextView
        btnBack.setOnClickListener {
            finish()
        }

        val label =
            ("       Bukalapak merupakan salah satu pasar daring (online marketplace) terkemuka di Indonesia yang dimiliki dan dijalankan oleh PT. Bukalapak. Seperti halnya situs layanan jual-beli online dengan model bisnis consumer-to-consumer (C2C), Bukalapak menyediakan sarana penjualan dari konsumen ke konsumen di mana pun. Siapa pun bisa  membuka toko daring untuk kemudian melayani calon pembeli dari seluruh Indonesia baik satuan ataupun dalam jumlah banyak. Pengguna perorangan ataupun perusahaan dapat membeli dan menjual produk, baik baru maupun bekas, seperti sepeda, ponsel,  perlengkapan bayi, gadget, aksesoris gadget, komputer, tablet, perlengkapan rumah tangga, busana, elektronik, dan lain-lain.\n" +
                    "       Bukalapak didirikan oleh Achmad Zaky pada awal tahun 2010 sebagai divisi agensi digital bernama Suitmedia yang berbasis di Jakarta. Namun, Bukalapak baru berstatus sebagai sebuah Perseroan Terbatas (PT) pada September 2011 dan dikelola oleh manajemen yang dipimpin oleh Achmad Zaky sebagai CEO (Chief Executive Office) dan Nugroho Herucahyono sebagai CTO (Chief Technology Officer).\n" +
                    "       Bukalapak memiliki program untuk memfasilitasi para UKM yang ada di Indonesia untuk melakukan transaksi jual beli secara online. Hal ini dikarenakan transaksi melalui online dapat mempermudah UKM dalam menjual produk-produk yang mereka miliki tanpa harus memiliki toko offline.Untuk yang telah memiliki toko offline, Bukalapak mengharapkan dengan adanya situs tersebut dapat membantu meningkatkan penjualan toko offline tersebut.\n" +
                    "\n" +
                    "PT Bukalapak.com\n" +
                    "Plaza City View\n" +
                    "Jl. Kemang Timur No. 22, Pasar Minggu\n" +
                    "Pejaten Barat\n" +
                    "Jakarta, Indonesia 12510\n")
        val txtLabel = findViewById(R.id.textBukalapak) as JustifyTextView
        txtLabel.setText(label)

        linkBukalapak.setText("Informasi lebih lanjut tentang Magang, klik link berikut https://careers.bukalapak.com/")
        Linkify.addLinks(linkBukalapak, Linkify.ALL)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }
}