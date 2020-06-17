package com.example.learningapp

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import kotlin.time.TestTimeSource


class ReadLearningXMl(var context: Context, var path: String = "") {


    private fun genStream(): InputStream {

        var input: InputStream = context.getAssets().open(path)
        return input
    }

    public fun testSubject(subject: Subject) {
        Timber.i("testSubject _Start")
        Timber.i("testSubject Subject text" + subject.text)
        Timber.i("testSubject Subject anz lessond" + subject.lessons.size)
        Timber.i("--------------------------------------------------------------------------------------------------------------")
        for (less in subject.lessons) {
            Timber.i("testSubject lesson Name:" + less.text)
            Timber.i("testSubject lesson anz quest:" + less.questions.size)
            Timber.i("--------------------------------------------------------------------------------------------------------------")
            for (quest in less.questions) {
                Timber.i("testSubject lesson Name:" + less.text + "question Name" + quest.text)
                Timber.i("testSubject quest awnser anz:" + quest.answer.size)
                Timber.i("--------------------------------------------------------------------------------------------------------------")
                for (ans in quest.answer) {
                    Timber.i("testSubject lesson Name:" + less.text + "question Name" + quest.text + "awnser name" + ans.text)
                    Timber.i("--------------------------------------------------------------------------------------------------------------")
                }
            }
        }

    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun genLesson(parser: XmlPullParser): Lesson {
        var lesson: Lesson = Lesson()
        var event = parser.eventType
        lesson.text = ""

        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = parser.name




            if (event == XmlPullParser.START_TAG) {
                Timber.i(tag)

                when (tag) {

                    "lesson"->{
                   /*     for (i in 0 until parser.attributeCount step 1) {
                            var atribut: Atribut = Atribut()
                            atribut.name = parser.getAttributeName(i).trim()
                            atribut.text = parser.getAttributeValue() //parser.getAttributeValue(i).trim()

                            lesson.atributList.add(atribut)
                        }*/
                        var atribut: Atribut = Atribut()

                        atribut.name = "Name"
                        atribut.text = parser.getAttributeValue(null,"Name") //parser.getAttributeValue(i).trim()

                        lesson.atributList.add(atribut)

                        atribut=Atribut()

                        atribut.name = "finished"
                        atribut.text = parser.getAttributeValue(null,"finished") //parser.getAttributeValue(i).trim()

                        lesson.atributList.add(atribut)
                    }
                    "question" -> {
                        lesson.questions.add(genQuestion(parser))

                    }
                }
            }

            if (event == XmlPullParser.TEXT) {

                Timber.i("lesson text=" + parser.text + " tag=" + tag)
                lesson.text += parser.text.trim()

            }

            if (event == XmlPullParser.END_TAG) {
                Timber.i("EndTag =" + tag)
                if (tag == "lesson") {
                    break
                }
            }

            event = parser.next()
        }
        return lesson
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun genQuestion(parser: XmlPullParser): Question {
        var question: Question = Question()
        var event = parser.eventType
        question.text = ""

        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = parser.name




            if (event == XmlPullParser.START_TAG) {
                Timber.i(tag)

                when (tag) {

                    "question"->{
                        /*for (i in 0..parser.attributeCount - 1 step 1) {
                            var atribut: Atribut = Atribut()
                            atribut.name = parser.getAttributeName(i).trim()
                            atribut.text = parser.getAttributeValue(i).trim()

                            question.atributList.add(atribut)
                        }*/
                        var atribut: Atribut = Atribut()
                        atribut.name = "Name"
                        atribut.text = parser.getAttributeValue(null,"Name") //parser.getAttributeValue(i).trim()

                        question.atributList.add(atribut)

                        atribut= Atribut()

                        atribut.name = "finished"
                        atribut.text = parser.getAttributeValue(null,"finished") //parser.getAttributeValue(i).trim()

                        question.atributList.add(atribut)
                    }

                    "answer" -> {
                        question.answer.add(genAnswer(parser))

                    }
                }
            }

            if (event == XmlPullParser.TEXT) {


                Timber.i("qestion text=" + parser.text + " tag=" + tag)
                question.text += parser.text.trim()

            }

            if (event == XmlPullParser.END_TAG) {
                Timber.i("EndTag =" + tag)
                if (tag == "question") {
                    break
                }
            }

            event = parser.next()
        }
        return question
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun genAnswer(parser: XmlPullParser): Answer {
        var answer = Answer()
        var event = parser.eventType
        answer.text = ""

        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = parser.name

          /*  for (i in 0..parser.attributeCount - 1 step 1) {
                var atribut: Atribut = Atribut()
                atribut.name = parser.getAttributeName(i).trim()
                atribut.text = parser.getAttributeValue(i).trim()

                answer.atributList.add(atribut)
            }*/


            if (event == XmlPullParser.START_TAG) {
                Timber.i(tag)
                when (tag) {

                    "answer"->{
                        /*for (i in 0..parser.attributeCount - 1 step 1) {
                            var atribut: Atribut = Atribut()
                            atribut.name = parser.getAttributeName(i).trim()
                            atribut.text = parser.getAttributeValue(i).trim()

                            question.atributList.add(atribut)
                        }*/
                        var atribut: Atribut = Atribut()
                        atribut.name = "isCorrect"
                        atribut.text = parser.getAttributeValue(null,"isCorrect") //parser.getAttributeValue(i).trim()

                        answer.atributList.add(atribut)
                    }


                }



            }

            if (event == XmlPullParser.TEXT) {

                answer.text = parser.text.trim()
                Timber.i("lesson text=" + parser.text + " tag=" + tag)

            }

            if (event == XmlPullParser.END_TAG) {
                Timber.i("EndTag =" + tag)
                if (tag == "answer") {
                    break
                }

            }

            event = parser.next()
        }
        return answer
    }


    @Throws(XmlPullParserException::class, IOException::class)
    public fun read(): Subject {
        val xmlFactoryObject = XmlPullParserFactory.newInstance()
        val myparser = xmlFactoryObject.newPullParser()

        var value = Subject()

        myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        myparser.setInput(this.genStream(), null)
        var event = myparser.eventType
        var text: String = ""


        while (event != XmlPullParser.END_DOCUMENT) {
            var tag = myparser.name



            when (event) {

                XmlPullParser.START_TAG -> {


                    when (tag) {
                        "subject" -> {
                          //  myparser.setProperty()
                          /*  for (i in 0..myparser.attributeCount - 1 step 1) {
                                var atribut: Atribut = Atribut()
                                atribut.name = myparser.getAttributeName(i).trim()
                                atribut.text = myparser.getAttributeValue(i).trim()

                                value.atributList.add(atribut)
                            }*/
                            var atribut: Atribut = Atribut()
                            atribut.name = "Name"
                            atribut.text = myparser.getAttributeValue(null,"Name") //parser.getAttributeValue(i).trim()

                            value.atributList.add(atribut)
                        }

                        "lesson" -> {
                            value.lessons.add(genLesson(myparser))
                        }

                    }
                }

                XmlPullParser.TEXT -> {
                    text = myparser.text
                }

                XmlPullParser.END_TAG -> {

                }
            }
            event = myparser.next()
        }

        return value
    }
}