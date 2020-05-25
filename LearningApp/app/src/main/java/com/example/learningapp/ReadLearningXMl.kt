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
    private fun genLesson( parser: XmlPullParser):Lesson
    {
        var lesson:Lesson= Lesson()
       var event = parser.eventType

        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = parser.name
            //when (event) {

                if(event==XmlPullParser.START_TAG)  {
                    Timber.i(tag)

                    when (tag) {
                        "text" -> {

                        }
                        "subject"->{

                        }

                        "question" -> {
                         lesson.questions.plus(   genQuestion(parser))

                        }

                        "lesson" -> {

                        }

                        "answer" -> {

                        }

                    }
                }

                if(event==XmlPullParser.TEXT ){
                if(tag =="text")
                {
                    lesson.text=parser.text
                }
                    Timber.i(parser.text)
                }

               if(event== XmlPullParser.END_TAG ) {
                    Timber.i(tag)
                    if(tag=="lesson"){
                        break
                    }

                }
          // }
            event = parser.next()
        }
        return lesson
    }
    @Throws(XmlPullParserException::class, IOException::class)
    private fun genQuestion( parser: XmlPullParser):Question
    {
        var question:Question= Question()
        var event = parser.eventType

        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = parser.name
            //when (event) {

            if(event==XmlPullParser.START_TAG)  {
                Timber.i(tag)

                when (tag) {
                    "text" -> {

                    }
                    "subject"->{

                    }

                    "question" -> {


                    }

                    "lesson" -> {

                    }

                    "answer" -> {
                        question.answer.plus(   genAnswer(parser))

                    }

                }
            }

            if(event==XmlPullParser.TEXT ){
                if(tag =="text")
                {
                    question.text=parser.text
                }
                Timber.i(parser.text)
            }

            if(event== XmlPullParser.END_TAG ) {
                Timber.i(tag)
                if(tag=="question"){
                    break
                }

            }
            // }
            event = parser.next()
        }
        return question
    }
    @Throws(XmlPullParserException::class, IOException::class)
    private fun genAnswer( parser: XmlPullParser):Answer
    {
        var answer= Answer()
        var event = parser.eventType

        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = parser.name
            //when (event) {

            if(event==XmlPullParser.START_TAG)  {
                Timber.i(tag)

                when (tag) {
                    "text" -> {

                    }
                    "subject"->{

                    }

                    "question" -> {


                    }

                    "lesson" -> {

                    }

                    "answer" -> {


                    }

                }
            }

            if(event==XmlPullParser.TEXT ){
                if(tag =="text")
                {
                    answer.text=parser.text
                }
                Timber.i(parser.text)
            }

            if(event== XmlPullParser.END_TAG ) {
                Timber.i(tag)
                if(tag=="answer"){
                    break
                }

            }
            // }
            event = parser.next()
        }
        return answer
    }


    @Throws(XmlPullParserException::class, IOException::class)
    public fun Read(): Subject {
        val xmlFactoryObject = XmlPullParserFactory.newInstance()
        val myparser = xmlFactoryObject.newPullParser()

        var value = Subject()

       // var presentElement: LearningElement = Subject()
        //var previusElement: LearningElement = Subject()

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

                        }
                        "subject"->{

                        }

                        "question" -> {

                        }

                        "lesson" -> {
                            value.lessons.plus(genLesson(myparser))

                        }

                        "answer" -> {

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


                        }

                        "lesson" -> {

                        }

                        "answer" -> {


                        }
                    }
                }
            }
            event = myparser.next()
        }

        return value
    }
}