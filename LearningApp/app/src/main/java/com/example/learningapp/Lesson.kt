package com.example.learningapp

class Lesson():LearningElement() {

     var questions:MutableList<Question> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }


}