package com.example.learningapp

class Answer( ) : LearningElement() {

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