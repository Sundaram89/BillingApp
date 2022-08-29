package com.example.billing3app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

class Userdetail : AppCompatActivity() {
    private lateinit var billlist1:billlist
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)

        val name =findViewById<TextView>(R.id.name2)
        val date =findViewById<TextView>(R.id.date)
        val billno = findViewById<TextView>(R.id.bill)
        val mobile=findViewById<TextView>(R.id.mobile2)
        name.text = intent.getStringExtra("name")
        date.text = intent.getStringExtra("date")
        billno.text =intent.getStringExtra("bill")
        mobile.text=intent.getStringExtra("mobile")

    }
}

