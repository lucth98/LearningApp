package com.example.learningapp

import timber.log.Timber
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Lesson() : LearningElement() {

    var questions: MutableList<Question> = mutableListOf()
        get() = field
        set(value) {
            field = value
        }

    public fun getImage(): String {
        var value: String = "null"
        for (atribut in this.atributList) {
            if (atribut.name.compareTo("Image") == 0) {
                value = atribut.text.toString()
            }
        }
        return value
    }

    public fun getTime(): String {
        var value: String = "null"
        for (atribut in this.atributList) {
            if (atribut.name.compareTo("endtime") == 0) {
                value = atribut.text.toString()
            }
        }
        return value
    }
    public fun gethasTime():Boolean{
        var string = this.getTime()
        return string.compareTo("null") != 0 && string.compareTo("") != 0
    }

    public fun getTimeDifference():Long {
        var endtime=this.getEndtime()
        var currrentTime: Date = Calendar.getInstance().time
        var diff = endtime.time - currrentTime.time
        return TimeUnit.MILLISECONDS.toDays(diff)
    }
    private fun getEndtime():Date{
        var string = this.getTime()
        val format = SimpleDateFormat("dd-MM-yyyy")
       return format.parse(string)
    }

    public fun hasTimerunout(): Boolean {
        var result: Boolean = false
        try {
            var endtime=this.getEndtime()
            var currrentTime: Date = Calendar.getInstance().time
            Timber.i("tag jetzt=" + currrentTime + "tag ende=" + endtime)

            result = currrentTime.before(endtime)

        } catch (e: Exception) {
            Timber.i(e)
        }

        return result
    }


}