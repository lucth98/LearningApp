package com.example.learningapp

class Lesson():LearningElement() {

     var questions:List<Question> = listOf()
        get() = field
        set(value) {
            field = value
        }
    override var text: String=""
        get() = field
        set(value) {
            field = value
        }
    override var atribut= Atribut()
        get() = field
        set(value) {
            field = value
        }
}