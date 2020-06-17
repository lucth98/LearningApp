package com.example.learningapp

abstract class LearningElement {

    abstract var text:String



    abstract  var atributList:MutableList<Atribut>

     fun getName():String{
        var result="-1"
        for (atr in atributList){
            if(atr.name.compareTo("Name")==0){
                result=atr.text
            }
        }
        return result
    }

}