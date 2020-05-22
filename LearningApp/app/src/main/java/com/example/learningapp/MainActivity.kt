package com.example.learningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var readLearningXMl:ReadLearningXMl= ReadLearningXMl(this,"TestLearning2.xml")
        readLearningXMl.Read()

    }
}
