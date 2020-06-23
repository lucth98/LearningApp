package com.example.learningapp

import java.io.Serializable

abstract class LearningElement:Serializable {



     var text: String=""
        get() = field
        set(value) {
            field = value
        }



     fun getfinished():Boolean{
        var result=false
        for (atr in atributList){
            if(atr.name.compareTo("finished")==0){
                result=atr.text.toBoolean()
            }
        }
        return result
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


    var path: String = ""
        get() = field
        set(value) {
            field = value
        }

}