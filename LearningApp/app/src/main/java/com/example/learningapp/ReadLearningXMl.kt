package com.example.learningapp

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import timber.log.Timber
import java.io.IOException
import java.io.InputStream


class ReadLearningXMl(var context: Context    ,var path:String="") {



    private fun genStream():InputStream{

       var input:InputStream= context.getAssets().open(path)
        return input
    }



    @Throws(XmlPullParserException::class, IOException::class)
    public fun Read(): Subject {
         val xmlFactoryObject = XmlPullParserFactory.newInstance()
         val myparser = xmlFactoryObject.newPullParser()

        var subject = Subject()

        myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        myparser.setInput(this.genStream(), null)
        var event = myparser.eventType
        var text:String=""
        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = myparser.name
            when (event) {

                XmlPullParser.START_TAG -> {
                    Timber.i(tag)
                }

                XmlPullParser.TEXT -> {
                    text=myparser.text
                    Timber.i(myparser.text)
                }

                XmlPullParser.END_TAG -> {
                    when (tag) {
                        "text"->{Timber.i(tag) }

                        "question"->{Timber.i(tag) }

                        "lesson"->{Timber.i(tag) }

                        "answer"->{ Timber.i(tag)}


                    }
                }


            }

            event = myparser.next()
        }






        return Subject()
    }


}