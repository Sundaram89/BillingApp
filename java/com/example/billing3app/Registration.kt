package com.example.billing3app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.billing3app.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Registration : AppCompatActivity() {
    private lateinit var binding:ActivityRegistrationBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var username: String
    lateinit var shopname: String
    lateinit var ownername: String
    lateinit var address: String
    lateinit var instragramaccount: String
    lateinit var mobilenumber: String
    lateinit var password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
            setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.already.setOnClickListener {
             val intent=Intent(this,DashBoard::class.java)
            startActivity(intent)
        }
          binding.button.setOnClickListener {
           username=binding.username.text.toString()
              shopname=binding.ShopName.text.toString()
              ownername=binding.ownerName.text.toString()
              address=binding.address.text.toString()
              instragramaccount=binding.instagram.text.toString()
              mobilenumber=binding.mobno.text.toString()
              password = binding.password.text.toString()
              val confirmPass = binding.cnfpassword.text.toString()
              if(username.isNotEmpty() && shopname.isNotEmpty() && ownername.isNotEmpty() && address.isNotEmpty() && instragramaccount.isNotEmpty() && mobilenumber.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()){
                  if(password==confirmPass)
                  {
                      firebaseAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(){
                            sendData()
                          if (it.isSuccessful) {
                              val intent = Intent(this, Home::class.java)
                              startActivity(intent)
                          } else {
                              Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                          }
                      }
                  } else {
                      Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                  }
              } else {
                  Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

              }
          }
    }
    private fun  sendData(){
        val firebaseDatabase=FirebaseDatabase.getInstance()
        val myReference=firebaseDatabase.getReference(firebaseAuth?.uid.toString())
        val userProfile=UserProfile(username,shopname,ownername,address,instragramaccount,mobilenumber,password)
        myReference.setValue(userProfile)

    }
}

