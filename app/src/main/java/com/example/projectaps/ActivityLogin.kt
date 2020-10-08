package com.example.projectaps

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*

class ActivityLogin : AppCompatActivity(), View.OnClickListener {

    lateinit var alertDialog: AlertDialog.Builder
    lateinit var btnLogin: Button
    lateinit var textUsername: EditText
    lateinit var textPassword:EditText
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        alertDialog = AlertDialog.Builder(this)
        textUsername = findViewById(R.id.textUsername) as EditText
        textPassword = findViewById(R.id.textPassword) as EditText
        btnLogin = findViewById(R.id.btnLogin) as Button
        btnLogin.setOnClickListener(this)
        databaseReference = FirebaseDatabase.getInstance().getReference()
    }
    override fun onClick(view: View) {
        if (view === btnLogin)
        {
            loginAkun()
        }
    }
    private fun loginAkun() {
        val email = textUsername.getText().toString().trim()
        val password = textPassword.getText().toString().trim()


        val query = databaseReference.child("mhs").orderByChild("email").equalTo(textUsername.getText().toString().trim())
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists())
                {
                    for (user in dataSnapshot.getChildren())
                    {
                        val usersBean = user.getValue(Akun::class.java)
                        if (usersBean!!.password.equals(textPassword.getText().toString().trim()))
                        {
                            val intent = Intent(getApplicationContext(), ActivityUtama::class.java)
                            intent.putExtra("email", textUsername.getText().toString())
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this@ActivityLogin, "Password is wrong", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                else
                {
                    Toast.makeText(this@ActivityLogin, "User not found", Toast.LENGTH_LONG).show()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    override fun onBackPressed() {
        Toast.makeText(this@ActivityLogin, "Back is Clicked", Toast.LENGTH_SHORT).show()
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