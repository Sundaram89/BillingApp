package com.example.billing3app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
      private var  SPLASH_SCREEN= 5000

    //variables
    var topAnim: Animation? = null
    var bottomAnim: //variables
    Animation? = null
    var image: ImageView? = null
    var logo: TextView? = null
    var slogan:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        //hooks

        //hooks
        image = findViewById(R.id.imageView2)
        logo = findViewById(R.id.billnum)
        slogan = findViewById<TextView>(R.id.textView3)

        image?.setAnimation(topAnim)
        logo?.setAnimation(bottomAnim)
        slogan?.setAnimation(bottomAnim)

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, DashBoard::class.java)
            startActivity(intent)
            finish()
        },5000)

    }

}
