package com.example.learningapp

class Question():LearningElement() {
    var answer:MutableList<Answer> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }



}
