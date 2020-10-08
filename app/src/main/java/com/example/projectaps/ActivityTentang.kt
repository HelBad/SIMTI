package com.example.projectaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_tentang.*
import me.biubiubiu.justifytext.library.JustifyTextView

class ActivityTentang : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang)

        btnBack.setOnClickListener {
            finish()
        }

        val label = ("PENGGUNAAN APLIKASI INI MENUNJUKKAN PENERIMAAN DAN KEPATUHAN TERHADAP SYARAT DAN KETENTUAN DIBAWAH INI.\n" +
                "\n" +
                "       Selamat datang di Aplikasi SIMTI, suatu aplikasi informasi tentang magang atau praktek industri mahasiswa jurusan Teknik Informatikan Universitas Negeri Surabaya berbasis android.\n" +
                "       Syarat-syarat dan ketentuan penggunaan berikut ini berlaku dalam mengakses, memasang, dan atau menggunakan Aplikasi. Harap diketahui bahwa kami dapat mengubah, memodifikasi, menambah dan menghapus Syarat Penggunaan ini sewaktu-waktu tanpa pemberitahuan sebelumnya. Syarat Penggunaan harus dibaca secara berkala. Dengan terus menggunakan Aplikasi ini setelah perubahan tersebut terhadap Syarat Penggunaan, pengguna terdaftar (baik sebagai dosen ataupun mahasiswa) sepakat dan menyetujui perubahan. Jika Anda menggunakan layanan, maka penggunaan Anda didasarkan pada kepatuhan dan penerimaan terhadap syarat dan ketentuan yang berlaku untuk layanan tersebut.\n" +
                "\n" +
                "PRIVASI\n" +
                "       Kami menghargai kerahasiaan Pengguna. Kami akan berusaha keras untuk mematuhi persyaratan perundangan perlindungan data yang relevan saat melakukan kewajibannya menurut Syarat Penggunaan ini.\n" +
                "\n" +
                "HUKUM YANG MENGATUR\n" +
                "       Syarat Penggunaan ini akan diatur oleh dan ditafsirkan sesuai dengan peraturan yang berlaku di jurusan Teknik Informatika Universitas Negeri Surabaya.\n" +
                "\n" +
                "KETENTUAN\n" +
                "       Aplikasi ini merupakan aplikasi yang bersifat local area. Jika Anda memiliki pertanyaan lebih lanjut ataupun keluhan, harap menghubungi Admin.\n")
        val txtLabel = findViewById(R.id.textBantuan) as JustifyTextView
        txtLabel.setText(label)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }
}