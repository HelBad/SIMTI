package com.example.projectaps

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_profil.*
import kotlinx.android.synthetic.main.fragment_akun.*
import java.lang.Exception

class FragmentBerkas : Fragment() {

    lateinit var databaseReference: DatabaseReference
    lateinit var mbinggris:TextView
    lateinit var mbasisdata:TextView
    lateinit var mimk:TextView
    lateinit var mjarkom:TextView
    lateinit var mmtk:TextView
    lateinit var mpbo: TextView
    lateinit var mpdasar:TextView
    lateinit var mpvisual:TextView
    lateinit var mpweb:TextView
    lateinit var mpti:TextView
    lateinit var mrpl:TextView
    lateinit var mso:TextView
    lateinit var mstrukturdata:TextView
    lateinit var textPdf11:TextView

    val PDF:Int = 1
    lateinit var uri:Uri
    lateinit var storageReference: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_berkas, container, false)
    }

    @SuppressLint("WrongViewCast")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mbinggris = view!!.findViewById<View>(R.id.textMatkul1) as TextView
        mbasisdata = view!!.findViewById<View>(R.id.textMatkul2) as TextView
        mimk = view!!.findViewById<View>(R.id.textMatkul3) as TextView
        mjarkom = view!!.findViewById<View>(R.id.textMatkul4) as TextView
        mmtk = view!!.findViewById<View>(R.id.textMatkul5) as TextView
        mpbo = view!!.findViewById<View>(R.id.textMatkul6) as TextView
        mpdasar = view!!.findViewById<View>(R.id.textMatkul7) as TextView
        mpvisual = view!!.findViewById<View>(R.id.textMatkul8) as TextView
        mpweb = view!!.findViewById<View>(R.id.textMatkul9) as TextView
        mpti = view!!.findViewById<View>(R.id.textMatkul10) as TextView
        mrpl = view!!.findViewById<View>(R.id.textMatkul11) as TextView
        mso = view!!.findViewById<View>(R.id.textMatkul12) as TextView
        mstrukturdata = view!!.findViewById<View>(R.id.textMatkul13) as TextView
        textPdf11 = view!!.findViewById<View>(R.id.textPdf11) as TextView

        databaseReference = FirebaseDatabase.getInstance().getReference()
        val query = databaseReference.child("mhs").orderByChild("email").equalTo(activity!!.intent.getStringExtra("email"))
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot != null)
                {
                    for (snapshot1 in datasnapshot.getChildren())
                    {
                        val allocation = snapshot1.getValue(Akun::class.java)
                        mbinggris.setText(allocation!!.mbinggris)
                        mbasisdata.setText(allocation.mbasisdata)
                        mimk.setText(allocation.mimk)
                        mjarkom.setText(allocation.mjarkom)
                        mmtk.setText(allocation.mmtk)
                        mpbo.setText(allocation.mpbo)
                        mpdasar.setText(allocation.mpdasar)
                        mpvisual.setText(allocation.mpvisual)
                        mpweb.setText(allocation.mpweb)
                        mpti.setText(allocation.mpti)
                        mrpl.setText(allocation.mrpl)
                        mso.setText(allocation.mso)
                        mstrukturdata.setText(allocation.mstrukturdata)
                        textPdf11.setText(allocation.email)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        storageReference = FirebaseStorage.getInstance().getReference("berkas")
        val uploadProposal = view!!.findViewById<View>(R.id.btnupload1) as Button
        val uploadCv = view!!.findViewById<View>(R.id.btnSave1) as Button
        uploadProposal.setOnClickListener(View.OnClickListener {
            view:View -> val intent = Intent()
            intent.setType("*/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), PDF)
        })
        uploadCv.setOnClickListener(View.OnClickListener {
                view:View -> val intent = Intent()
            intent.setType("*/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), PDF)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == RESULT_OK) {
            if(requestCode == PDF) {
                uri = data!!.data
                var email = textPdf11.text.toString()
                var mStorage = storageReference.child(email).child(uri.lastPathSegment)
                try {
                    mStorage.putFile(uri).addOnSuccessListener {
                            taskSnapshot: UploadTask.TaskSnapshot? -> var url = taskSnapshot!!.storage.downloadUrl
                        val uri2Proposal = view!!.findViewById<View>(R.id.textPdf12) as TextView
                        uri2Proposal.text = url.toString()
                        uri2Proposal.text = ""
                        Toast.makeText(activity, "Successfully Uploaded", Toast.LENGTH_SHORT).show()
                    }
                } catch(ex:Exception) {
                    Toast.makeText(activity, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}