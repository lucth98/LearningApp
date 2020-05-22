package com.example.learningapp

class Lesson(override var text: String, override var atribut: Atribut):LearningElement() {

     var questions:List<Question> = listOf()
        get() = field
        set(value) {
            field = value
        }
}