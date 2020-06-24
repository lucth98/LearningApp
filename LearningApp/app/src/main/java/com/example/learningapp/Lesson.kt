package com.example.learningapp

import java.lang.NullPointerException

class Lesson():LearningElement() {

     var questions:MutableList<Question> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }
    public fun getImage():String{
        var value:String="null"
        for(atribut in this.atributList){
            if(atribut.name.compareTo("Image")==0){
                value=atribut.text.toString()
            }
        }
        return value
    }


}