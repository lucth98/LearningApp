package com.example.learningapp

class Question(name: String = "", quest: String = "") {
     var answers: List<String> = listOf()
        get() = field
    set(value) {
        field = value
    }
     var indexes_of_rigth_answers:List<Int> = listOf()
        get() = field
        set(value) {
            field = value
        }
    val ismultiplechoice:Boolean
    get()=this.indexes_of_rigth_answers.lastIndex>0


}