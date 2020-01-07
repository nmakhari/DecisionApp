package com.example.spinner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_input.*
import kotlin.random.Random

class InputActivity: AppCompatActivity(){

    lateinit var editModelArrayList: ArrayList<EditModel>
    //declares this editModelArrayList variable late and globally while protecting its private class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        //setting up the list view
        var listView = findViewById<ListView>(R.id.options_listView_inputActivity)

        //getting the desired number of options from the previous activity
        val extraIntent = intent.getStringExtra("Quantity")
        var quantity = extraIntent.toInt()
        Log.d("MainActivityInput", "The recieved quantity is $quantity")

        //initializing the information array
        editModelArrayList = populateList(quantity-1)


        listView.adapter = MyCustomAdapter(this, editModelArrayList)//this is a custom adapter that tells this particular list view what to render
        //the adapter above passes just the editModelArrayList variable since the quantity is embedded in its size

        //once the button is clicked, assume the user is done, then proceed to choose the options and change activity
        Choose_button_inputActivity.setOnClickListener{
            Log.d("MainActivity", "Button clicked, choosing result and switching views")
            var options = mutableListOf<String>()
            Log.d("MainActivity", "The editModelArrayList has size ${editModelArrayList.size}")

            for(num in 0 until editModelArrayList.size){
                //this is my attempt at trying to get the item from the listView
                //so long as the text has some contents add it to the options list
                if(editModelArrayList[num].getEditTextValues()!=""){
                    options.add(editModelArrayList[num].getEditTextValues())
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

    //I marked this as inner, if it causes problems remove the inner marking and line 72, as it was all done to make 72 work
    private inner class MyCustomAdapter(context: Context, editModelArrayList2: ArrayList<EditModel>): BaseAdapter(){

        private val mContext:Context

        init{
            editModelArrayList = editModelArrayList2
            mContext= context

        }

        //renders out each row, this is where you put your own custom view if you decide to use one which I do
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            //we create a layout inflater
            val rowOption = layoutInflater.inflate(R.layout.row_listview_options, parent, false)
            //the row layout is then inflated with the created inflater
            val emptyText = rowOption.findViewById<EditText>(R.id.input_EditText_options)
            //we set emptyText to be the editText of the row
            emptyText.setText(editModelArrayList[position].getEditTextValues())
            emptyText.setHint("Option ${position+1}")//setting the hint to instruct the user and display the option number
            //we set the text to be whats stored in the information array

            //below the editText listens for a change in its text and preforms the necessary updates to the info array

            emptyText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //empty
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    editModelArrayList[position].setEditTextValues(emptyText.text.toString())
                    //the line above seems to work well
                }

                override fun afterTextChanged(s: Editable?) {
                    //empty
                }

            })

            return rowOption

        }

        override fun getItem(position: Int): Any {
            return editModelArrayList[position]
            //this has seemingly been fixed through the use of the information array, this returns an object of type EditModel
        }

        override fun getItemId(position: Int): Long {
                return position.toLong()
        }
        //responsible for the number of rows
        override fun getCount(): Int {
            return editModelArrayList.size// the number of rows comes from the quantity argument which is passes as extra
        }
    }

    private fun populateList(count: Int):ArrayList<EditModel>{

        val list = ArrayList<EditModel>()

        for(i in 0..count){
            val editModel = EditModel()
            editModel.setEditTextValues("")//sets the text to be empty initially
            list.add(editModel)
        }

        return list
        //this returns the populated list of EditModels with the setText executed on them
    }

    class EditModel{//this class is responsible for manipulating and retrieving the users input
        var editTextValue: String? = null

        fun getEditTextValues():String {//gets the string in the selected edit text
            return editTextValue.toString()
        }
            // note that in order for the setter function (below) to not clash with the initialization, the names must differ
        fun setEditTextValues(editTextValue: String){//sets a selected edit text to some string
            this.editTextValue = editTextValue
        }
    }

}

