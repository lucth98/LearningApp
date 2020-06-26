package com.example.learningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.learningapp.databinding.FragmentOptionsBinding
import timber.log.Timber

class OptionsFragment : Fragment() {
    //Data Binding
    private lateinit var binding: FragmentOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //löscht alle Files im Internen Speicher
    private fun restFiles() {
        try {
            var saveFiles: SaveFiles = SaveFiles(this.requireContext())
            saveFiles.deleteFilesinInternalStorage()
        } catch (e: Exception) {
            Timber.i(e)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_options, container, false)

        binding.buttonReset.setOnClickListener {
            restFiles()
        }


        binding.buttonBacktoMenue.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_optionsFragment_to_startFragment))
        this.generateResetButtons()
        return binding.root
    }

    //löscht ein File im Internen Speicher
    private fun delete(name: String) {
        var saveFiles = SaveFiles(this.requireContext())
        saveFiles.deleteFile(name)
    }

    //erstellt für jedes File im Internen Speicher einen Reset Button
    private fun generateResetButtons() {
        try {
            var saveFiles = SaveFiles(this.requireContext())
            for (file in saveFiles.getFilnamesInternalStorage()) {
                var Button = Button(this.requireContext())
                Button.text = "reset " + file.toString()
                Button.setOnClickListener {
                    delete(file)

                }
                binding.linLayout.addView(Button)
            }
        } catch (e: Exception) {
            Timber.i(e)
        }
    }


}
