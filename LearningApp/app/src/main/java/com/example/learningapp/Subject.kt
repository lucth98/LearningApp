package com.example.learningapp

class Subject {
     var lessons:List<Lesson> = listOf()
        get() = field
        set(value) {
            field = value
        }
     var name: String=""
     get()=field
    set(value) {field=value}
}