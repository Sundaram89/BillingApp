package com.example.billing3app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter (private val mList: List<productlist>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productlist = mList[position]

        // sets the image to the imageview from our itemHolder class


        // sets the text to the textview from our itemHolder class
        holder.itemname.text = productlist.itemname
        holder.qty.text = productlist.qty
        holder.actualprice.text = productlist.actualprice
        holder.discprice.text = productlist.discprice
    }
    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemname: TextView = itemView.findViewById(R.id.itemname)
        val qty: TextView =itemView.findViewById(R.id.qty)
        val actualprice: TextView =itemView.findViewById(R.id.actualprice)
        val discprice: TextView =itemView.findViewById(R.id.discprice)



    }


}