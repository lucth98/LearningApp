package com.example.learningapp

class Lesson():LearningElement() {

     var questions:MutableList<Question> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }
    override var text: String=""
        get() = field
        set(value) {
            field = value
        }
    override  var atributList: MutableList<Atribut> = mutableListOf()
        get() =field
        set(value:MutableList<Atribut>) {
            field=(value)
        }
}