package com.example.billing3app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.billing3app.productlist as productlist1
import com.example.billing3app.productlist as billing3appProductlist

class CustomAdapter2 (private val mList: ArrayList<billlist>) : RecyclerView.Adapter<CustomAdapter2.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bill_generated, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val current = mList[position]
        holder.billno.text = current.billno
        holder.date.text = current.date
        holder.name.text=current.name
        holder.mobile.text=current.mobileno
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context,Userdetail::class.java)
            intent.putExtra("bill",current.billno)
            intent.putExtra("date",current.date)
            intent.putExtra("name",current.name)
            intent.putExtra("mobile",current.mobileno)
            it.context.startActivity(intent)
        }
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val billno: TextView = itemView.findViewById(R.id.billnu2)
        val date: TextView = itemView.findViewById(R.id.billnum2)
        val name:TextView=itemView.findViewById(R.id.mobNumber)
        val mobile:TextView=itemView.findViewById(R.id.mobno)


    }
}



