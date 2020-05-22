package com.example.learningapp

class Lesson {
     var text:String=""
        get() = field
        set(value) {
            field = value
        }
     var questions:List<Question> = listOf()
        get() = field
        set(value) {
            field = value
        }
}