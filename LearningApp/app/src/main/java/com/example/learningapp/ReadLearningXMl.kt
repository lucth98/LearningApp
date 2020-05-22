package com.example.learningapp

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import timber.log.Timber
import java.io.IOException
import java.io.InputStream


class ReadLearningXMl(var context: Context, var path: String = "") {


    private fun genStream(): InputStream {

        var input: InputStream = context.getAssets().open(path)
        return input
    }


    @Throws(XmlPullParserException::class, IOException::class)
    public fun Read(): Subject {
        val xmlFactoryObject = XmlPullParserFactory.newInstance()
        val myparser = xmlFactoryObject.newPullParser()

        var value = Subject("", Atribut())

        var presentElement: LearningElement = Subject("", Atribut())
        var previusElement: LearningElement = Subject("", Atribut())

        myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        myparser.setInput(this.genStream(), null)
        var event = myparser.eventType
        var text: String = ""


        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = myparser.name
            when (event) {

                XmlPullParser.START_TAG -> {
                    Timber.i(tag)

                    when (tag) {
                        "text" -> {
                            presentElement.text = myparser.text
                        }

                        "question" -> {
                            presentElement = Question("", Atribut())
                        }

                        "lesson" -> {
                            presentElement = Lesson("", Atribut())
                        }

                        "answer" -> {
                            presentElement = Answer("", Atribut())
                        }


                    }
                }

                XmlPullParser.TEXT -> {
                    text = myparser.text
                    Timber.i(myparser.text)


                }

                XmlPullParser.END_TAG -> {
                    Timber.i(tag)
                    when (tag) {
                        "text" -> {

                        }

                        "question" -> {
                            previusElement=presentElement


                        }

                        "lesson" -> {
                            previusElement=presentElement

                        }

                        "answer" -> {
                            previusElement=presentElement

                        }


                    }
                }


            }

            event = myparser.next()
        }






        return value
    }


}