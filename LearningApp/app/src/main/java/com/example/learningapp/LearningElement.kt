package com.example.learningapp

abstract class LearningElement {



     var text: String=""
        get() = field
        set(value) {
            field = value
        }


     fun getName():String{
        var result="-1"
        for (atr in atributList){
            if(atr.name.compareTo("Name")==0){
                result=atr.text
            }
        }
        return result
    }

    var atributList: MutableList<Atribut> = mutableListOf()
        get() =field
        set(value:MutableList<Atribut>) {
            field=(value)
        }

}