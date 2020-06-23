package com.example.learningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.learningapp.databinding.FragmentStartBinding


/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)

        binding.StartButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_fragmentMenue2))
        binding.OptionButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_optionsFragment))

        return binding.root//inflater.inflate(R.layout.fragment_start, container, false)
    }


}
