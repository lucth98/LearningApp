package com.example.learningapp

class Question(override var text: String, override var atribut: Atribut):LearningElement() {
    var answer:List<Answer> = listOf()
        get() = field
        set(value) {
            field = value
        }
    var name: String=""
        get()=field
        set(value) {field=value}

}
/*(
}name: String = "", quest: String = "") {
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


}*/