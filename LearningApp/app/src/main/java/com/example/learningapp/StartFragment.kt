package com.example.learningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.learningapp.databinding.FragmentStartBinding
import timber.log.Timber


class StartFragment : Fragment() {
    //Data Binding
    private lateinit var binding: FragmentStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private fun generateFolder(){
      /*  try{
            var extenal=Extenal(this.requireContext())
            extenal.generateImageFolder()
        }catch (e:Exception){
            Timber.i(e)
        }*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        binding.StartButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_fragmentMenue2))
        binding.OptionButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_optionsFragment))

        this.generateFolder()

        return binding.root
    }
}
