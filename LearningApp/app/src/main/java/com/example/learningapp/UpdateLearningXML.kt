package com.example.learningapp

import android.content.Context
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class UpdateLearningXML(var context: Context) {
     private  val atributeNameIndexQuestion:Int=0
    private  val atributefinishedIndexQuestion:Int=0
    private  val atributeNameIndexLesson:Int=0
    private  val atributefinishedIndexLesson:Int=0
    private val questiontag="question"
    private val lessonntag="lesson"

    private fun generateDoc(path:String): Document {
        var factory=DocumentBuilderFactory.newInstance()
        var builder=factory.newDocumentBuilder()
        var doc=builder.parse(context.openFileInput(path))

        return doc
    }

    public fun changeQuestion(path:String, question: Question,setFinish:Boolean) {

        var doc=this.generateDoc(path)

        var nodeList=doc.getElementsByTagName(questiontag)

        for (i in  0 until nodeList.length step 1 ){
            var node =nodeList.item(i)
            var atributes =node.attributes


            if( atributes.item(atributeNameIndexQuestion).nodeValue.compareTo(question.getName())==0){

                atributes.item(atributefinishedIndexQuestion).nodeValue=setFinish.toString()

            }


    }
        this.transform(doc)

    }

    public fun changeLesson(path:String, question: Question,setFinish:Boolean){
        var doc=this.generateDoc(path)

        var nodeList=doc.getElementsByTagName(lessonntag)

        for (i in  0 until nodeList.length step 1 ){
            var node =nodeList.item(i)
            var atributes =node.attributes


            if( atributes.item(atributeNameIndexLesson).nodeValue.compareTo(question.getName())==0){

                atributes.item(atributefinishedIndexLesson).nodeValue=setFinish.toString()

            }


        }
        this.transform(doc)
    }


    private fun transform(doc:Document) {
        val transformerFactory: TransformerFactory = TransformerFactory.newInstance()
        val transformer: Transformer = transformerFactory.newTransformer()
        val dSource = DOMSource(doc)
        val result = StreamResult(
            context.openFileOutput(
                "MyFileName.xml",
                Context.MODE_PRIVATE
            )
        ) // To save it in the Internal Storage

        transformer.transform(dSource, result)
    }


}