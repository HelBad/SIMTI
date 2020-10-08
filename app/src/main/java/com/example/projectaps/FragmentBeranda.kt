package com.example.projectaps

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_beranda.*

class FragmentBeranda : Fragment() {

    lateinit var databaseReference: DatabaseReference
    lateinit var textNama:TextView
    lateinit var textEmail:TextView
    lateinit var textIpk: TextView
    lateinit var imgBeranda:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //List Promo
        val listPromo = view!!.findViewById<View>(R.id.listRekom) as RecyclerView
        val image = intArrayOf(R.drawable.beranda_card1, R.drawable.beranda_card2, R.drawable.beranda_card3)
        val manager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        listPromo.setLayoutManager(manager)
        listPromo.adapter = AdapterRekom(image, this.requireContext())
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(listPromo)

        menuBeranda1.setOnClickListener {
            val intent = Intent(activity, ActivityProfesi1::class.java)
            intent.putExtra("nama", textNama.getText().toString())
            startActivity(intent)
        }
        menuBeranda2.setOnClickListener {
            val intent = Intent(activity, ActivityProfesi2::class.java)
            intent.putExtra("nama", textNama.getText().toString())
            startActivity(intent)
        }
        menuBeranda3.setOnClickListener {
            val intent = Intent(activity, ActivityProfesi3::class.java)
            intent.putExtra("nama", textNama.getText().toString())
            startActivity(intent)
        }
        menuBeranda4.setOnClickListener {
            val intent = Intent(activity, ActivityProfesi4::class.java)
            intent.putExtra("nama", textNama.getText().toString())
            startActivity(intent)
        }
        menuBeranda5.setOnClickListener {
            val intent = Intent(activity, ActivityProfesi5::class.java)
            intent.putExtra("nama", textNama.getText().toString())
            startActivity(intent)
        }
        menuBeranda6.setOnClickListener {
            val intent = Intent(activity, ActivityProfesi6::class.java)
            intent.putExtra("nama", textNama.getText().toString())
            startActivity(intent)
        }
        menuBeranda7.setOnClickListener {
            val intent = Intent(activity, ActivityProfesi7::class.java)
            intent.putExtra("nama", textNama.getText().toString())
            startActivity(intent)
        }

        textNama = view!!.findViewById<View>(R.id.namaBeranda) as TextView
        textEmail = view!!.findViewById<View>(R.id.emailBeranda) as TextView
        textIpk = view!!.findViewById<View>(R.id.ipkBeranda) as TextView
        imgBeranda = view!!.findViewById<View>(R.id.imgBeranda) as ImageView

        databaseReference = FirebaseDatabase.getInstance().getReference()
        val query = databaseReference.child("mhs").orderByChild("email").equalTo(activity!!.intent.getStringExtra("email"))
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot != null)
                {
                    for (snapshot1 in datasnapshot.getChildren())
                    {
                        val allocation = snapshot1.getValue(Akun::class.java)
                        textNama.setText(allocation!!.nama)
                        textEmail.setText(allocation!!.email)
                        textIpk.setText(allocation!!.ipk)
                        Picasso.get().load(allocation!!.img).into(imgBeranda)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}
