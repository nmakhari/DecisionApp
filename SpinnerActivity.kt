package com.example.spinner

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_spinner.*

class SpinnerActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val choice = intent.getStringExtra("Choice")
        val segments = intent.getStringExtra("Segments").toInt()
        val index = intent.getStringExtra("Index").toInt()//the index is incremented by 1 before sending

        val image = findViewById<ImageView>(R.id.spinner_imageView_spinnerActivity)

        Log.d("Spinner Activity", "Choice: $choice Segments: $segments Index: $index")

        //later i can try catting the segments to "spinner" and opening that string if segments is withing range to reduce work

        if(segments==2){
            image.setImageResource(R.drawable.spinner2)
        }
        else if(segments==3){
            image.setImageResource(R.drawable.spinner3)
        }
        else if(segments==4){
            image.setImageResource(R.drawable.spinner4)
        }
        else if(segments==5){
            image.setImageResource(R.drawable.spinner5)
        }
        else if(segments==6){
            image.setImageResource(R.drawable.spinner6)
        }
        else if(segments==7){
            image.setImageResource(R.drawable.spinner7)
        }
        else if(segments==8){
            image.setImageResource(R.drawable.spinner8)
        }
        else{
            intent = Intent(this, FinalActivity::class.java)

            intent.putExtra("Choice", choice)

            startActivity(intent)
        }

        val rotation = 7200 + (360*(index-2))/segments + 35 // rotates a default of 20 times plus the necessary amount to get to that index


        image.animate().setDuration(2000).rotationBy(-rotation.toFloat())

        continue_button_spinnerActivity.setOnClickListener {//if the button is pressed, then continue to the next screen, to avoid the need for sleeps

            val intent =
                Intent(this, FinalActivity::class.java)//sends the choice to the next screen

            intent.putExtra("Choice", choice)

            startActivity(intent)
        }

    }

}