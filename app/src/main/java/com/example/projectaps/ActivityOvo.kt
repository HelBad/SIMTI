package com.example.projectaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_ovo.*
import me.biubiubiu.justifytext.library.JustifyTextView

class ActivityOvo : AppCompatActivity() {

    lateinit var linkOvo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ovo)

        linkOvo = findViewById(R.id.linkOvo) as TextView
        btnBack.setOnClickListener {
            finish()
        }

        val label =
            ("       OVO adalah sebuah aplikasi smart yang memberikan layanan pembayaran dan transaksi secara online (OVO Cash). Anda juga bisa berkesempatan untuk mengumpulkan poin setiap kali Anda melakukan transaksi pembayaran melalui OVO.\n" +
                    "       Secara umum, OVO Cash dapat digunakan untuk berbagai macam pembayaran yang telah bekerja sama dengan OVO menjadi lebih cepat. Sedangkan OVO Points adalah loyalty rewards bagi yang melakukan transaksi dengan menggunakan OVO Cash di merchant-merchant rekanan OVO. Untuk OVO Points sendiri, dapat ditukarkan dengan berbagai penawaran menarik hingga ditukarkan dengan transaksi di merchant rekanan OVO.\n" +
                    "       OVO menawarkan kemudahan transaksi tanpa mengharuskan nasabahnya membawa cash terlalu banyak. Salah satunya cukup dengan menunjukkan aplikasi OVO yang didalamnya terdapat saldo cash maupun point. OVO juga menawarkan loyalty rewards yang dapat Anda peroleh setiap melakukan transaksi di berbagai merchant rekanan OVO.\n" +
                    "\n" +
                    "Kantor Pusat\n" +
                    "Lippo Kuningan Lt. 20,\n" +
                    "Jl. HR. Rasuna Said Kav. B-12 Setiabudi,\n" +
                    "Jakarta 12940\n")
        val txtLabel = findViewById(R.id.textOvo) as JustifyTextView
        txtLabel.setText(label)

        linkOvo.setText("Informasi lebih lanjut tentang Magang, klik link berikut https://www.ovo.id/career/")
        Linkify.addLinks(linkOvo, Linkify.ALL)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }
}