package com.example.firebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import com.example.firebase1.databinding.ActivityEmailPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EmailPasswordActivity : AppCompatActivity() {
    lateinit var Binding: ActivityEmailPasswordBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityEmailPasswordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        auth = Firebase.auth

        Binding.Button2.setOnClickListener {

            var email = Binding.Email.text.toString()
            var password = Binding.Password.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(this, "Your Account Crete Succesfully", Toast.LENGTH_SHORT)
                            .show()

                    } else {


                        Log.e("TAG", "initview: " + task.exception)
                    }
                }

        }

        Binding.Button.setOnClickListener {

            var email = Binding.Email.text.toString()
            var password = Binding.Password.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        var i = Intent(this, DisplayIdActivity::class.java)
                        startActivity(i)

                        Toast.makeText(this, "Login Succesfull", Toast.LENGTH_SHORT).show()

                    } else {

                        Toast.makeText(this, "Please Enter 6 Digits", Toast.LENGTH_SHORT).show()
                    }
                }


        }

    }
}