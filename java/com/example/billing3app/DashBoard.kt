package com.example.billing3app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.billing3app.databinding.ActivityDashBoardBinding
import com.google.firebase.auth.FirebaseAuth

class DashBoard : AppCompatActivity(){
    private lateinit var binding: ActivityDashBoardBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding= ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()


      binding.button1.setOnClickListener{
       val intent=Intent(this,Registration::class.java)
          startActivity(intent)
          finish()
      }
        binding.sign.setOnClickListener{
     val username=binding.username.text.toString()
            val password=binding.password.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
        binding.home.setOnClickListener{
            val intent=Intent(this,Home::class.java)
            startActivity(intent)
            finish()
        }

        }
    }
