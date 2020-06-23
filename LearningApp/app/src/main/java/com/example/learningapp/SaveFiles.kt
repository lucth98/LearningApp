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
            for (filename in this.getFilenames()) {
                var updateLearningXML = UpdateLearningXML(context)
                updateLearningXML.saveInInternalStorage("LearnigFiles/" + filename,filename)
            }
        }catch(e:Exception){
            Timber.i(e)
        }
    }

    public fun getFilnamesInternalStorage() = context.fileList()

    public fun deleteFilesinInternalStorage(){
        for(fileuri in this.getFilnamesInternalStorage())
        {
           var file=File(fileuri)
            file.delete()
        }
    }

}