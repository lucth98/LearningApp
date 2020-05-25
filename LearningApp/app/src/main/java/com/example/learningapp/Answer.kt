package com.example.learningapp

class Answer( ) : LearningElement() {

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