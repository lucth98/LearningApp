package com.example.learningapp

class Subject():LearningElement() {
    //lesson des Subjects
     var lessons:MutableList<Lesson> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }
}