package com.example.learningapp

class Subject():LearningElement() {
     var lessons:MutableList<Lesson> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }



    public var path: String = ""
        get() = field
        set(value) {
            field = value
        }

}