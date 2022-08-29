package com.example.billing3app


import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.saveable.autoSaver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.billing3app.databinding.ActivityAddbookBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class addbook : AppCompatActivity() {
    private lateinit var binding: ActivityAddbookBinding
    private var bottomNavigationView: BottomNavigationView? = null
    private var adapter: CustomAdapter? = null
    private lateinit var additem: Button
    private var data: product? = null
    val item_list = ArrayList<productlist>()
    lateinit var mdialog:Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddbookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val billno2 = findViewById<TextView>(R.id.billnu)
        val random = Random()
        val genNum: String = java.lang.String.format("%04d", random.nextInt(1000))
        billno2.text = "Bill No : #" + genNum
        val date = findViewById<TextView>(R.id.billnum)
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDateFormat: String = simpleDateFormat.format(Date())
        date.text = "Date : " + currentDateFormat
        bottomNavigationView = findViewById(R.id.bottom_navigator)
        bottomNavigationView?.setSelectedItemId(R.id.addbook)
        bottomNavigationView?.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home1 -> {
                    startActivity(Intent(applicationContext, Home::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.book -> {
                    startActivity(Intent(applicationContext, Book::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.addbook -> return@OnNavigationItemSelectedListener true
            }
            false
        })

        val recyclerview4 = findViewById<RecyclerView>(R.id.recyclerview4)
        recyclerview4.layoutManager = LinearLayoutManager(this)
        item_list.add(productlist("",  "", "",""))
        adapter = CustomAdapter(item_list)
        recyclerview4.adapter = adapter
        additem = findViewById(R.id.button2)
        additem.setOnClickListener {
            val itemname = findViewById<EditText>(R.id.itemname).text.toString()
            val qty = findViewById<EditText>(R.id.qty).text.toString()
            val actualprice = findViewById<EditText>(R.id.actualprice).text.toString()
            val discprice = findViewById<EditText>(R.id.discprice).text.toString()

            for (i in 0 until 1) {
                if (itemname.isEmpty()) {
                    Toast.makeText(applicationContext, "Enter item name", Toast.LENGTH_SHORT).show()

                } else if (qty.isEmpty()) {

                    Toast.makeText(applicationContext, "Enter item quantity", Toast.LENGTH_SHORT)
                        .show()
                } else if (actualprice.isEmpty()) {
                    Toast.makeText(applicationContext, "Enter actual price", Toast.LENGTH_SHORT)
                        .show()
                } else if (discprice.isEmpty()) {
                    Toast.makeText(applicationContext, "Enter disc price", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    item_list.add(productlist(itemname, qty, actualprice, discprice))
                    // data_list
                    adapter?.notifyItemInserted(i)
//
                }
            }
        }
        mdialog= Dialog(this)
        binding.button3.setOnClickListener {
            val itemname = findViewById<EditText>(R.id.itemname).text.toString()
            val itemname1 = findViewById<EditText>(R.id.itemname)
            val qty = findViewById<EditText>(R.id.qty).text.toString()
            val qty1 = findViewById<EditText>(R.id.qty)
            val actualprice = findViewById<EditText>(R.id.actualprice).text.toString()
            val actualprice1 = findViewById<EditText>(R.id.actualprice)
            val discprice = findViewById<EditText>(R.id.discprice).text.toString()
            val discprice1 = findViewById<EditText>(R.id.discprice)
            val name = binding.mobNumber.text.toString()
            val mobileno = binding.mobno.text.toString()
            if (mobileno.isEmpty()) {
                Toast.makeText(this, "Enter your mobile no", Toast.LENGTH_SHORT).show()
            } else if (name.isEmpty()) {
                Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
            } else if (itemname1.text.isEmpty()) {
                Toast.makeText(this, "Enter Item name", Toast.LENGTH_SHORT).show()
            } else if (qty1.text.isEmpty()) {
                Toast.makeText(this, "Enter quantity", Toast.LENGTH_SHORT).show()
            } else if (actualprice1.text.isEmpty()) {
                Toast.makeText(this, "Enter Actual price", Toast.LENGTH_SHORT).show()
            } else if (discprice1.text.isEmpty()) {
                Toast.makeText(this, "Enter disc price", Toast.LENGTH_SHORT).show()
            } else {
                var date = binding.billnum.text.toString()
                val billno = binding.billnu.text.toString()
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                val currentDateFormat: String = simpleDateFormat.format(Date())
                date = "Date : " + currentDateFormat
                val billno1 = findViewById<TextView>(R.id.billnu)
                val random1 = Random()
                val genNum1: String = java.lang.String.format("%04d", random.nextInt(1000))
                billno1.text = "Bill No : #" + genNum1
                item_list.add(productlist(itemname, qty, actualprice,
                    discprice))
                val firebaseDatabase1 = FirebaseDatabase.getInstance().getReference("Order Master")
                val empid= firebaseDatabase1.push().key!!
                val Product = product(name, mobileno, date, billno, item_list)

                firebaseDatabase1.child(empid).setValue(Product).addOnSuccessListener {
                    mdialog.setContentView(R.layout.popup)
                    mdialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    mdialog.show()
                    mdialog.setCancelable(false)
                    val ok =mdialog.findViewById<TextView>(R.id.ok)
                    ok.setOnClickListener {
                        val intent=Intent(this,Home::class.java)
                        startActivity(intent)
                    }
                    binding.mobNumber.text?.clear()
                    binding.mobno.text?.clear()
                    item_list.clear().toString()

                }.addOnFailureListener {

                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}















