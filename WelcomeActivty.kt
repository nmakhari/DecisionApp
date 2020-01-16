package com.example.spinner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button


import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random


class WelcomeActivty: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        val button = findViewById<Button>(R.id.proceed_button_welcomeActivity)

        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        }



    }

