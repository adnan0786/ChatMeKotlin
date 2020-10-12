package com.example.chatmekotlin.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatmekotlin.Fragment.GetUserNumber
import com.example.chatmekotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, GetUserNumber())
            .commit()
    }
}