package com.example.billing3app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*


class Book : AppCompatActivity() {
    private lateinit var db:DatabaseReference
    private lateinit var recycleview: RecyclerView
    private lateinit var billlist1: ArrayList<billlist>
    private lateinit var item_list1:ArrayList<productlist>
    var bottomNavigationView: BottomNavigationView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        recycleview = findViewById(R.id.recyclerview5)
        recycleview.layoutManager = LinearLayoutManager(this)
        billlist1 = arrayListOf<billlist>()
        item_list1= arrayListOf<productlist>()
        getUserData()
    }

    private fun getUserData() {
        db=FirebaseDatabase.getInstance().getReference("Order Master")

        db.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){
                        val empid = userSnapshot.getValue(billlist::class.java)
                        billlist1.add(empid!!)
                        val count : String = empid.item_list1?.count().toString()
                        Toast.makeText(applicationContext,count + "data recived",Toast.LENGTH_LONG).show()
                    }
                    val adapter = CustomAdapter2(billlist1)
                    recycleview.adapter = adapter

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

        bottomNavigationView = findViewById(R.id.bottom_navigator)
        bottomNavigationView?.setSelectedItemId(R.id.book)

        bottomNavigationView?.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.book -> return@OnNavigationItemSelectedListener true
                R.id.home1 -> {
                    startActivity(Intent(applicationContext, Home::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.addbook -> {
                    startActivity(Intent(applicationContext, addbook::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}
