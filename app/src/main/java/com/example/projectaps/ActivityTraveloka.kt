package com.example.projectaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_traveloka.*
import me.biubiubiu.justifytext.library.JustifyTextView

class ActivityTraveloka : AppCompatActivity() {

    lateinit var linkTraveloka: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traveloka)

        linkTraveloka = findViewById(R.id.linkTraveloka) as TextView
        btnBack.setOnClickListener {
            finish()
        }

        val label =
            ("       Traveloka adalah perusahaan travel terkemuka di Asia Tenggara yang menyediakan berbagai kebutuhan perjalanan dalam satu platform, memungkinkan Anda untuk menciptakan momen bersama orang-orang terkasih. Kami menawarkan tiket pesawat, hotel, tiket kereta, paket pesawat + hotel, aktivitas & rekreasi, produk-produk konektivitas, transportasi bandara, dan bus.\n" +
                    "       Bekerja sama dengan lebih dari 100 maskapai domestik dan internasional, Traveloka melayani lebih dari 200.000 rute penerbangan ke seluruh dunia. Kami pun memiliki inventori pemesanan akomodasi terbesar, bervariasi mulai dari hotel, apartemen, guest house, homestay, vila, dan resor. Semua itu didukung oleh lebih dari 40 metode pembayaran untuk seluruh pelanggan di di Indonesia, Thailand, Vietnam, Malaysia, Singapura, dan Filipina, serta customer service yang siap melayani selama 24 jam dalam bahasa lokal.\n" +
                    "\n" +
                    "Traveloka\n" +
                    "Jakarta Office\n" +
                    "Wisma 77 Tower 1, 7th floor\n" +
                    "Jl. S. Parman Kav. 77\n" +
                    "Jakarta 11410\n" +
                    "Indonesia\n")
        val txtLabel = findViewById(R.id.textTraveloka) as JustifyTextView
        txtLabel.setText(label)

        linkTraveloka.setText("Informasi lebih lanjut tentang Magang, klik link berikut https://www.traveloka.com/en/internship/")
        Linkify.addLinks(linkTraveloka, Linkify.ALL)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }
}