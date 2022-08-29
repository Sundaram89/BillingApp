package com.example.billing3app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.billing3app.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.io.Serializable

class Home : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    private lateinit var shopname:TextView
    private lateinit var ownername:TextView
    private lateinit var mobilenumber:TextView
    private lateinit var instragramaccount:TextView
    private lateinit var address:TextView
    var bottomNavigationView: BottomNavigationView? = null
    var userProfile: UserProfile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, DashBoard::class.java)
            startActivity(intent)
            finish()
        }

        shopname=findViewById(R.id.billnum)
        ownername=findViewById(R.id.textView4)
        mobilenumber=findViewById(R.id.textView6)
        instragramaccount=findViewById(R.id.textView26)
        address=findViewById(R.id.textView16)
        val databaseReference : DatabaseReference=FirebaseDatabase.getInstance().getReference(firebaseAuth!!.uid!!)
        //val myReference=firebaseDatabase.child(currentUser?.uid.toString())
        databaseReference.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                userProfile=snapshot.getValue(UserProfile::class.java)
                if(userProfile != null)
                {
                    shopname.text=userProfile?.shopname
                    ownername.text= userProfile?.ownername
                    mobilenumber.text=userProfile?.mobilenumber
                    instragramaccount.text=userProfile?.instragramaccount
                    address.text=userProfile?.address


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Error while displaying the data",Toast.LENGTH_SHORT).show()

            }

        })
        bottomNavigationView = findViewById(R.id.bottom_navigator)
        bottomNavigationView?.setSelectedItemId(R.id.home1)
        bottomNavigationView?.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home1 -> return@OnNavigationItemSelectedListener true
                R.id.book -> {
                    startActivity(Intent(applicationContext, Book::class.java))
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
        binding.editform.setOnClickListener {

            val intent=Intent(this,editform::class.java)
            intent.putExtra("userProfile",userProfile as Serializable)
            startActivity(intent)
        }
    }

}


