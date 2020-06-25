package com.example.learningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.learningapp.databinding.FragmentOptionsBinding
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [OptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionsFragment : Fragment() {
    private lateinit var binding: FragmentOptionsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


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

        return binding.root
    }


}