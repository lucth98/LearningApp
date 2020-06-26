package com.example.learningapp

class Question():LearningElement() {
    //Antworten
    var answer:MutableList<Answer> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }



}
