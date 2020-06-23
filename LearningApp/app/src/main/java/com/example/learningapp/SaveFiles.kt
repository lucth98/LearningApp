package com.example.learningapp

import android.content.Context
import android.content.res.AssetManager
import androidx.core.content.ContentProviderCompat.requireContext
import timber.log.Timber
import java.io.File
import java.lang.Exception

class SaveFiles(var context: Context) {

    private fun getFilenames(): MutableList<String> {
        var result: MutableList<String> = mutableListOf()
        var assetManager: AssetManager = context.assets

        for (file in assetManager.list("LearnigFiles")!!) {
            if (!(file.compareTo("subject.dtd") == 0)) {
                result.add(file)
                Timber.i(file)
            }
        }
        return result
    }

    public fun moveXMLtoInternalStorage(){
        try {
            var updateLearningXML = UpdateLearningXML(context)
            for (filename in this.getFilenames()) {
                if(!this.checkifFileexist(filename)) {
                    updateLearningXML.saveInInternalStorage("LearnigFiles/" + filename, filename)
                    Timber.i("file "+filename+"existiert nicht")
                }else
                {
                    Timber.i("file "+filename+"existiert ")
                }
            }
        }catch(e:Exception){
            Timber.i(e)
        }
    }
    private fun checkifFileexist(filname:String):Boolean{
        for(filenameInternal in this.getFilnamesInternalStorage()){
            if (filname.compareTo(filenameInternal.toString())==0)
            {
                return true
            }
        }
        return false

    }

    public fun getFilnamesInternalStorage() = context.fileList()

    public fun deleteFilesinInternalStorage(){
        for(file in context.getFilesDir().listFiles())
        {

            file.delete()
        }
    }

}