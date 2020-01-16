package com.example.spinner

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.os.PersistableBundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_input.*
import kotlin.random.Random

class InputActivity: AppCompatActivity(){

    lateinit var editModelArrayList: ArrayList<EditModel>
    //declares this editModelArrayList variable late and globally for access in functions/inner functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        //setting up the list view
        var listView = findViewById<ListView>(R.id.options_listView_inputActivity)

        //getting the desired number of options from the previous activity
        val extraIntent = intent.getStringExtra("Quantity")
        var quantity = extraIntent.toInt()
        Log.d("InputActivity", "The received quantity is $quantity")

        //initializing the information array
        editModelArrayList = populateList(quantity-1)


        listView.adapter = MyCustomAdapter(this, editModelArrayList)//this is a custom adapter that tells this particular list view what to render
        //the adapter above passes just the editModelArrayList variable since the quantity is embedded in its size

        //once the button is clicked, assume the user is done, then proceed to choose the options and change activity
        Choose_button_inputActivity.setOnClickListener{

            var options = mutableListOf<String>()
            Log.d("InputActivity", "The editModelArrayList has size ${editModelArrayList.size}")

            for(num in 0 until editModelArrayList.size){
                //this is my attempt at trying to get the item from the listView
                //so long as the text has some contents add it to the options list
                if(editModelArrayList[num].getEditTextValues()!=""){
                    options.add(editModelArrayList[num].getEditTextValues())
                }
            }

            if(options.size==0){
                Toast.makeText(this, "Please complete one entry", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Log.d("InputActivity", "Options has size: ${options.size}")

            if( options.size != quantity ){//if the options list doesn't have as many entries as the quantity variable

                Log.d("InputActivity", "Opening popup")

                val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var view =  inflater.inflate(R.layout.popup_window, null)

                val popUp = PopupWindow(
                    view, // Custom view to show in popup window
                    LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                    LinearLayout.LayoutParams.WRAP_CONTENT // Window height
                )

                popUp.showAtLocation(view, Gravity.CENTER,0,0)
                /*

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    // Create a new slide animation for popup window enter transition
                    val slideIn = Slide()
                    slideIn.slideEdge = Gravity.TOP
                    popUp.enterTransition = slideIn

                    // Slide animation for popup window exit transition
                    val slideOut = Slide()
                    slideOut.slideEdge = Gravity.RIGHT
                    popUp.exitTransition = slideOut

                }

                TransitionManager.beginDelayedTransition(getWindow().getDecorView().findViewById(android.R.id.content))
                popUp.showAtLocation(view, Gravity.CENTER,0, 0 )

                */

                var x = view.findViewById<ImageView>(R.id.x_imageView_popupWindow)
                var no = view.findViewById<Button>(R.id.no_button_popupWindow)
                var yes = view.findViewById<Button>(R.id.yes_button_popupWindow)



                yes.setOnClickListener{//if the yes button is pressed, it continues in the code
                    //makes a choice randomly
                    val index = Random.nextInt(options.size)
                    val segments = options.size
                    val choice: String = options[index]
                    choice.toUpperCase()//converts the choice to all uppers
                    //passes the choice as extra in the intent and launches the choice activity with that choice
                    var intent = Intent(this, SpinnerActivity::class.java)
                    intent.putExtra("Choice", choice)
                    intent.putExtra("Segments", segments.toString())
                    intent.putExtra("Index", (index+1).toString())
                    startActivity(intent)
                }

                no.setOnClickListener{//else when the yes button isnt pressed it goes back to the original
                    popUp.dismiss()
                }

                x.setOnClickListener{
                    popUp.dismiss()
                }


            }
            else{
                val index = Random.nextInt(options.size)
                val segments = options.size
                val choice: String = options[index]
                choice.toUpperCase()//converts the choice to all uppers
                //passes the choice as extra in the intent and launches the choice activity with that choice
                var intent = Intent(this, SpinnerActivity::class.java)
                intent.putExtra("Choice", choice)
                intent.putExtra("Segments", segments.toString())
                intent.putExtra("Index", (index+1).toString())
                startActivity(intent)
            }
            Log.d("InputActivity", "Resetting click listener")
            return@setOnClickListener
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

