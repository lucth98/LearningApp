package com.example.learningapp

import android.content.res.AssetManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.learningapp.databinding.FragmentMenueBinding
import com.example.learningapp.databinding.FragmentStartBinding
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentMenue.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentMenue : Fragment() {
   // private lateinit var binding: FragmentStartBinding
    private lateinit var binding: FragmentMenueBinding



    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_menue,container,false)
        // Inflate the layout for this fragment

        for (name:String in getFilenames()){

            binding.textViewTest.text = binding.textViewTest.text.toString() + name
        }



        return binding.root//inflater.inflate(R.layout.fragment_menue, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentMenue.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentMenue().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }










    private fun getFilenames():MutableList<String>{
       /* var file:File=File("assets")
        var files=file.listFiles()
        var result:MutableList<String> = mutableListOf()

        for(i in 0 until files.size){
            result.add(files[i.toInt()].name)
        }
            return result*/


        var result:MutableList<String> = mutableListOf()

        var assetManager:AssetManager= requireContext().getAssets()

      for(file in assetManager.list("LearnigFiles")!!)
      {
          result.add(file)
          result.add("\n")
      }





        return result



    }











}
