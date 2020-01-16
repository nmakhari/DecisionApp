package com.example.spinner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class QuantityActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        Log.d("MainActivity", "started main")

        Choose_button_mainAcitivity.setOnClickListener{

            if(Quantity_edittext_mainActivity.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter a quantity", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else if(Quantity_edittext_mainActivity.text.toString().toInt()<=0){
                Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //gets the quantity, and saves it under number
            val number = Quantity_edittext_mainActivity.text.toString()

            Log.d("MainActivity", "Quantity is $number")
            //passes that number as extra in the intent
            var intent = Intent(this, InputActivity::class.java)
            intent.putExtra("Quantity", number)
            startActivity(intent)


        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    }





