package com.example.spinner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_choice.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        Choose_button_mainAcitivity.setOnClickListener{


            //gets the quantity, and saves it under number
            val number = Quantity_edittext_mainActivity.text.toString()

            Log.d("MainActivity", "Quantity is $number")
            //passes that number as extra in the intent
            var intent = Intent(this, InputActivity::class.java)
            intent.putExtra("Quantity", number)
            startActivity(intent)



            /*val input = Input_edittext_mainActivity.text.toString()

            fun Separate():List<String> {

                input.replace('\n', ',')//replaces all new lines with commas

                val ret = input.split(",")//splits the input at commas

                return ret//returns the List<String> of the separate inputs

            }

            val options = Separate()

            Log.d("MainActivity", "Number: $number")
            Log.d("MainActivity", "Options: $options")

            val choice:String = options[Random.nextInt(options.size)]
            Log.d("MainActivity", "Choice: $choice")
            choice.toUpperCase()*/





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
