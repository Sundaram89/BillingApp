package com.example.billing3app

import android.text.Editable
import java.io.Serializable

class UserProfile : Serializable{
    lateinit var username:String
    lateinit var shopname:String
    lateinit var ownername:String
    lateinit var address:String
    lateinit var instragramaccount:String
    lateinit var mobilenumber:String
    lateinit var password:String
//    lateinit var itemList: ArrayList<ItemModel>

    constructor(){}
    constructor(username:String, shopname:String, ownername:String,address:String,instragramaccount:String,mobilenumber:String,password:String){
        this.username=username
        this.shopname=shopname
        this.ownername=ownername
        this.address=address
        this.instragramaccount=instragramaccount
        this.mobilenumber=mobilenumber
        this.password=password
    }
}