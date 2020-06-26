package com.example.learningapp

import android.content.Context
import org.w3c.dom.Document
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
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
    private val atributeTime: Int = 2
    public val questiontag = "question"
    public val lessonntag = "lesson"



    private fun generateDoc(path: String, fromAssets: Boolean): Document {
        var factory = DocumentBuilderFactory.newInstance()
        var builder = factory.newDocumentBuilder()
        Timber.i("path= " + path)
        return if (fromAssets) {
            builder.parse(context.assets.open(path))
        } else {
            builder.parse(context.openFileInput(path))
        }


    }
    private fun generateDocfromEx(path: String): Document {
        var factory = DocumentBuilderFactory.newInstance()
        var builder = factory.newDocumentBuilder()
        Timber.i("path= " + path)
        var fileInputStream=FileInputStream(context.getExternalFilesDir(null)!!.absolutePath+"/"+path)
        return             builder.parse(fileInputStream)



    }
    public fun saveInInternalStoragefromExtrnal(path: String, name: String) {
        var doc = this.generateDocfromEx(path)//&this.generateDoc_Assets(path)
        this.transform(doc, name)
    }


    public fun saveInInternalStorage(path: String, name: String) {
        var doc = this.generateDoc(path,true)//&this.generateDoc_Assets(path)
        this.transform(doc, name)
    }

    public fun changeTimeLesson(path: String, LearningElement: Lesson, setTime: String) {
        var doc = this.generateDoc(path,false)//this.generateDoc_InternalStorage(path)

        var nodeList = doc.getElementsByTagName(lessonntag)

        for (i in 0 until nodeList.length step 1) {
            var node = nodeList.item(i)
            var atributes = node.attributes

            if (atributes.item(atributeNameIndexLesson).nodeValue.compareTo(LearningElement.getName()) == 0) {
                Timber.i("Wert = " + atributes.item(atributeTime).nodeValue)
                atributes.item(atributeTime).nodeValue = setTime
                Timber.i("Wert = " + atributes.item(atributeTime).nodeValue)
            }
        }
        this.transform(doc, path)
    }


    public fun changeLearnigElement(path: String, LearningElement: LearningElement, setFinish: Boolean, tag: String) {
        var doc = this.generateDoc(path,false)//this.generateDoc_InternalStorage(path)

        var nodeList = doc.getElementsByTagName(tag)

        for (i in 0 until nodeList.length step 1) {
            var node = nodeList.item(i)
            var atributes = node.attributes

            if (atributes.item(atributeNameIndexLesson).nodeValue.compareTo(LearningElement.getName()) == 0) {
                Timber.i("Wert = " + atributes.item(atributefinishedIndexLesson).nodeValue)
                atributes.item(atributefinishedIndexLesson).nodeValue = setFinish.toString()
                Timber.i("Wert = " + atributes.item(atributefinishedIndexLesson).nodeValue)
            }
        }
        this.transform(doc, path)
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

        } catch (e: Exception) {
            Timber.i(e)
        }
    }

    private fun tetst() {
        for (string in context.fileList()) {
            Timber.i("Test Filename =" + string)
        }

    }


}