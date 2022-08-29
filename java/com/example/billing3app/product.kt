package com.example.billing3app
import kotlin.collections.ArrayList

class product  {
    lateinit var name: String
    lateinit var mobileno:String
    lateinit var date:String
    lateinit var billno:String
    lateinit var item_list:ArrayList<productlist>


    constructor(){}
    constructor(name: String, mobileno: String, date: String, billno: String, item_list: ArrayList<productlist>){
        this.name=name
        this.mobileno=mobileno
        this.date=date
        this.billno=billno
        this.item_list=item_list


    }

}