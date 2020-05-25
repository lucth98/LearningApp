package com.example.learningapp

class Subject():LearningElement() {
     var lessons:List<Lesson> = listOf()
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