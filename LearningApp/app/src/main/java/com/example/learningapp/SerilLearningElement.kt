package com.example.learningapp

import java.io.Serializable

class SerilLearningElement :Serializable{
    public  var learningElement:LearningElement=Subject()
        get() = field
        set(value) {
            field = value
        }

}