package com.example.learningapp

class Subject(override var text: String, override var atribut: Atribut):LearningElement() {
     var lessons:List<Lesson> = listOf()
        get() = field
        set(value) {
            field = value
        }

}