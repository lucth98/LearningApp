package com.example.learningapp

class Question():LearningElement() {
    var answer:MutableList<Answer> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }
    var name: String=""
        get()=field
        set(value) {field=value}

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