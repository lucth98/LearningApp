package com.example.learningapp

import timber.log.Timber
import java.io.Serializable

abstract class LearningElement : Serializable {
    //der Text des Lern Elements
    var text: String = ""
        get() = field
        set(value) {
            field = value
        }

    //gibt zurück ob das Element eledigt wurde
    fun getfinished(): Boolean {
        var result = false
        for (atr in atributList) {
            if (atr.name.compareTo("finished") == 0) {
                result = atr.text.toBoolean()


                Timber.i("fished= " + atr.text)
            }
        }
        return result
    }

    //gibt den Namen des Elements zurückt
    fun getName(): String {
        var result = "-1"
        for (atr in atributList) {
            if (atr.name.compareTo("Name") == 0) {
                result = atr.text
            }
        }
        return result
    }

    //Atribute des elents
    var atributList: MutableList<Atribut> = mutableListOf()
        get() = field
        set(value: MutableList<Atribut>) {
            field = (value)
        }

    //phat des File des Elements
    var path: String = ""
        get() = field
        set(value) {
            field = value
        }

}