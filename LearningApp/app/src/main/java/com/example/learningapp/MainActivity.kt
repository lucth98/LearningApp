package com.example.learningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /*  var readLearningXMl:ReadLearningXMl= ReadLearningXMl(this,"<?xml version=\"1.0\" encoding=\"utf-8\"?><!DOCTYPE subject [<!--    -->\n    <!ELEMENT subject (text,lesson)>\n    <!ELEMENT text  (#PCDATA)><!ELEMENT lesson (text, question*)>\n\n    <!ELEMENT answer (#PCDATA)><!ELEMENT question (text,answer*)>\n    <!ATTLIST answer\n        isCorrect (true|false) #REQUIRED\n        >\n\n    ]>\n<subject>\n    <text> testfach</text>\n    <lesson>\n        <text>Bla Bla</text>\n        <question>\n            <text>\n                5*5=?\n            </text>\n            <answer isCorrect=\"true\">\n                25\n            </answer>\n            <answer isCorrect=\"false\">\n                5\n            </answer>\n            <answer isCorrect=\"false\">\n                -10\n            </answer>\n        </question>\n    </lesson>\n</subject>")
        readLearningXMl.read()*/
        val navController = this.findNavController(R.id.myNavFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)


    }
    override fun onBackPressed() {
        val navController = this.findNavController(R.id.myNavFragment)
        navController.navigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavFragment)
        return navController.navigateUp()
    }
}
