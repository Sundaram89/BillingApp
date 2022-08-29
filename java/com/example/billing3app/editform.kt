package com.example.billing3app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.billing3app.databinding.ActivityEditformBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class editform : AppCompatActivity() {
    private lateinit var binding: ActivityEditformBinding
    private var userProfile: UserProfile? = null
    private lateinit var database: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditformBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userProfile = intent.getSerializableExtra("userProfile") as UserProfile
        if (userProfile != null) {
            val name1 = findViewById<TextInputEditText>(R.id.username)
            name1.setText(userProfile?.shopname)
            val name2 = findViewById<TextInputEditText>(R.id.ownerName)
            name2.setText(userProfile?.ownername)
            val name3 = findViewById<TextInputEditText>(R.id.address)
            name3.setText(userProfile?.address)
            val name4 = findViewById<TextInputEditText>(R.id.instagram)
            name4.setText(userProfile?.instragramaccount)
            val name5 = findViewById<TextInputEditText>(R.id.mobno)
            name5.setText(userProfile?.mobilenumber)
            val name6=findViewById<TextInputEditText>(R.id.password)
            name6.setText(userProfile?.password)
        }

        binding.updates.setOnClickListener {
            val shopname=binding.username.text.toString()
            val ownername=binding.ownerName.text.toString()
            val address=binding.address.text.toString()
            val instragramaccount=binding.instagram.text.toString()
            val mobilenumber=binding.mobno.text.toString()
            val password=binding.password.text.toString()
            updateData(shopname,ownername,address,instragramaccount,mobilenumber,password)
            val intent=Intent(this,Home::class.java)
            startActivity(intent)

        }
    }

    private fun updateData(
        shopname: String,
        ownername: String,
        address: String,
        instragramaccount: String,
        mobilenumber: String,
        password: String
    ) {
        val firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference(firebaseAuth.uid!!)
        val user= mapOf<String,String>(
         "shopname" to shopname,
        "ownername" to ownername,
        "address" to address,
        "instragramaccount" to instragramaccount,
        "mobilenumber" to mobilenumber,
            "password" to password)
        database.updateChildren(user).addOnSuccessListener {
            binding.username.text?.clear()
            binding.ownerName.text?.clear()
            binding.address.text?.clear()
            binding.instagram.text?.clear()
            binding.mobno.text?.clear()
            binding.password.text?.clear()
            Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }

    }
}





