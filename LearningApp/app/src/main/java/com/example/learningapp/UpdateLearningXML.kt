package com.example.learningapp

import android.content.Context
import org.w3c.dom.Document
import timber.log.Timber
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class UpdateLearningXML(var context: Context) {
    private val atributeNameIndexQuestion: Int = 0
    private val atributefinishedIndexQuestion: Int = 1
    private val atributeNameIndexLesson: Int = 0
    private val atributefinishedIndexLesson: Int = 1
    private val questiontag = "question"
    private val lessonntag = "lesson"

    private fun generateDoc_Assets(path: String): Document {
        var factory = DocumentBuilderFactory.newInstance()
        var builder = factory.newDocumentBuilder()
        Timber.i("path= " + path)


        var doc = builder.parse(context.assets.open(path))//.parse(context.openFileInput(path))//getAssets().open(path))//context.openFileInput(path))
        // doc.documentURI = path
        if (doc != null) {
            Timber.i("doc uri= " + doc.documentURI)
            Timber.i("xml= " + doc.xmlEncoding)
        } else {
            Timber.i("Error")
        }
        return doc
    }

    private fun generateDoc_InternalStorage(path: String): Document {
        var factory = DocumentBuilderFactory.newInstance()
        var builder = factory.newDocumentBuilder()
        Timber.i("path= " + path)

        var doc = builder.parse(context.openFileInput(path))
        // doc.documentURI = path
        if (doc != null) {
            Timber.i("doc uri= " + doc.documentURI)
            Timber.i("xml= " + doc.xmlEncoding)
        } else {
            Timber.i("Error")
        }
        return doc
    }

    public fun saveInInternalStorage(path: String, name: String) {
        var doc = this.generateDoc_Assets(path)
        this.transform(doc, name)
    }

    public fun changeQuestion(path: String, question: Question, setFinish: Boolean) {

        Timber.i("changeQuestion")
        var doc = this.generateDoc_Assets(path)

        var nodeList = doc.getElementsByTagName(questiontag)

        for (i in 0 until nodeList.length step 1) {
            var node = nodeList.item(i)
            var atributes = node.attributes

            if (atributes.item(atributeNameIndexQuestion).nodeValue.compareTo(question.getName()) == 0) {

                Timber.i("Wert = " + atributes.item(atributefinishedIndexQuestion).nodeValue)
                atributes.item(atributefinishedIndexQuestion).nodeValue = setFinish.toString()
                Timber.i("Wert = " + atributes.item(atributefinishedIndexQuestion).nodeValue)
            }
        }
        this.transform(doc, "new2")

    }

    public fun changeLesson(path: String, question: Question, setFinish: Boolean) {
        var doc = this.generateDoc_Assets(path)

        var nodeList = doc.getElementsByTagName(lessonntag)

        for (i in 0 until nodeList.length step 1) {
            var node = nodeList.item(i)
            var atributes = node.attributes

            if (atributes.item(atributeNameIndexLesson).nodeValue.compareTo(question.getName()) == 0) {
                Timber.i("Wert = " + atributes.item(atributefinishedIndexLesson).nodeValue)
                atributes.item(atributefinishedIndexLesson).nodeValue = setFinish.toString()
                Timber.i("Wert = " + atributes.item(atributefinishedIndexLesson).nodeValue)
            }
        }
        this.transform(doc, "new")
    }


    private fun transform(doc: Document, name: String) {
        try {

            val transformerFactory: TransformerFactory = TransformerFactory.newInstance()
            val transformer: Transformer = transformerFactory.newTransformer()
            val dSource = DOMSource(doc)

            Timber.i("file output path= " + context.filesDir.absolutePath)


            val result = StreamResult(context.openFileOutput(name, Context.MODE_PRIVATE))
            Timber.i("name= " + name)
            if (doc.doctype != null) {
                var systemvalue = (File(doc.doctype.systemId)).name
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemvalue)
                Timber.i("system values" + systemvalue)
            } else {
                Timber.i("Error Doctype =null")
            }
            transformer.transform(dSource, result)

            tetst()
        } catch (e: Exception) {
            Timber.i(e)
        }
    }

    private fun tetst() {
        for (strin in context.fileList()) {
            Timber.i("Test Filename =" + strin)
        }
    }


}