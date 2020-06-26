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

    //gibt den Namen des Bildes der Lesson zurück
    public fun getImage(): String {
        var value: String = "null"
        for (atribut in this.atributList) {
            if (atribut.name.compareTo("Image") == 0) {
                value = atribut.text.toString()
            }
        }
        return value
    }

    //gibt die End Zeit zurück
    public fun getTime(): String {
        var value: String = "null"
        for (atribut in this.atributList) {
            if (atribut.name.compareTo("endtime") == 0) {
                value = atribut.text.toString()
            }
        }
        return value
    }

    //gibt zurück ob die Zeit abgelaufen ist
    public fun gethasTime(): Boolean {
        var string = this.getTime()
        return string.compareTo("null") != 0 && string.compareTo("") != 0
    }

    //gibt die Zeitdiffernz zwichen jetzt Zeit und End Zeit zurück
    public fun getTimeDifference(): Long {
        var endtime = this.getEndtime()
        var currrentTime: Date = Calendar.getInstance().time
        var diff = endtime.time - currrentTime.time
        return TimeUnit.MILLISECONDS.toDays(diff)
    }

    //formatiert die Endzeit zu Date
    private fun getEndtime(): Date {
        var string = this.getTime()
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.parse(string)
    }

    //prüft ob die Zeit abgelaufen ist
    public fun hasTimerunout(): Boolean {
        var result: Boolean = false
        try {
            var endtime = this.getEndtime()
            var currrentTime: Date = Calendar.getInstance().time
            Timber.i("tag jetzt=" + currrentTime + "tag ende=" + endtime)

            result = currrentTime.before(endtime)

        } catch (e: Exception) {
            Timber.i(e)
        }
        return result
    }


}