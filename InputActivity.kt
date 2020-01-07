package com.example.spinner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_input.*
import kotlin.random.Random

class InputActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val listView = findViewById<ListView>(R.id.options_listView_inputActivity)
        val extraIntent = intent.getStringExtra("Quantity")
        var quantity = extraIntent.toInt()

        //above i try getting the extra as a string and converting it to int here
        Log.d("MainActivityInput", "The recieved quantity is $quantity")
        listView.adapter = MyCustomAdapter(this, quantity)//this is a custom adapter that tells this particular list view what to render
        //the adapter above passes quantity as an argument

        Choose_button_inputActivity.setOnClickListener{

            var options = mutableListOf<String>()
            //quantity has to be passed
            for(num in 0..quantity){
                //this is my attempt at trying to get the item from the listView
                //so long as the text has some contents add it to the options list
                if(listView.getChildAt(num).findViewById<TextView>(R.id.input_textView_options).toString()!=""){
                    options.add(listView.getChildAt(num).findViewById<TextView>(R.id.input_textView_options).toString())
                }
            }
            //makes a choice randomly
            val choice: String = options[Random.nextInt(options.size)]
            choice.toUpperCase()//converts the choice to all uppers
            //passes the choice as extra in the intent and launches the choice activity with that choice
            var intent = Intent(this, ChoiceActivity::class.java)
            intent.putExtra("Choice", choice)
            startActivity(intent)
        }
    }

    private class MyCustomAdapter(context: Context, quantity: Int): BaseAdapter(){
        private val count = quantity
        private val mContext:Context

        init{
            mContext= context
        }

        //renders out each row, this is where you put your own custom view if you decide to use one which I do
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowOption = layoutInflater.inflate(R.layout.row_listview_options, parent, false)
            //below the text is set to be empty so that when accessed it returns something
            val emptyText = rowOption.findViewById<TextView>(R.id.input_textView_options)
            emptyText.text = ""
            return rowOption

        }

        override fun getItem(position: Int): Any {
            return ""
            //this needs to be fixed so that it doesnt return a null, find out how to access the element at that position
        }

        override fun getItemId(position: Int): Long {
                return position.toLong()
        }
        //responsible for the number of rows
        override fun getCount(): Int {
            return count// temporary row number, should be changed later with .getExtraInt
        }
    }

}

