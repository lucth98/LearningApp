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
    public fun getName():String{
        var result="-1"
        for (atr in atributList){
            if(atr.name.compareTo("Name")==0){
                result=atr.text
            }
        }
        return result
    }
}