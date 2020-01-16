package com.example.spinner

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_spinner.*

class FinalActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val choice = intent.getStringExtra("Choice")
        val choiceText = findViewById<TextView>(R.id.choice_textView_finalActivity)

        choiceText.text = choice//sets the string to be that choice

        val button = findViewById<Button>(R.id.newDecision_button_finalActivity)

        button.setOnClickListener{//if the user clicks the "new Decision" button, send them back to quantityActivity to start over

            val intent = Intent(this, QuantityActivity::class.java)

            startActivity(intent)
        }


    }

}