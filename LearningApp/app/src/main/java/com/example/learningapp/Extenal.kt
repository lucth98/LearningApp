package com.example.learningapp

import android.content.Context
import android.os.Environment
import timber.log.Timber
import java.io.File

class Extenal(var context: Context) {

    //kopiert alle neuen Files von Externen Speicher in den Internen Speicher
    public fun saveExternalFiles() {
        try {
            var saveFiles = SaveFiles(context)
            var updateLearningXML = UpdateLearningXML(context)
            for (file in this.getFilenames()) {
                if (!saveFiles.checkifFileexist(file)) {
                    updateLearningXML.saveInInternalStoragefromExtrnal(file, file)
                }
            }

        } catch (e: Exception) {
            Timber.i(e)
        }
    }
    //gibt alle Filenamen im External Speicher zurück
    public fun getFilenames(): MutableList<String> {
        var result = mutableListOf<String>()

        val path = context.getExternalFilesDir(null)!!.absolutePath
        val yourDir = File(path)
        Timber.i(path)
        Timber.i(yourDir.name)
        for (f in yourDir.listFiles()) {

            Timber.i(f.toString())

            var name = f.name
            Timber.i(name)
            result.add(name)
        }
        return result
    }


}