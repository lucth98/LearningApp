package com.example.learningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.learningapp.databinding.FragmentDatePickerBinding
import timber.log.Timber




class DatePickerFragment : Fragment() {
    //data Binding
    private lateinit var binding: FragmentDatePickerBinding
    // lesson in der das Datum gespeichert wird
    private lateinit var lesson: Lesson


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_date_picker, container, false)
        binding.backToLessonbutton.setOnClickListener{
            back()
        }
        try {
            var element = arguments?.let { Fragment_infoArgs.fromBundle(it).lernElement}
            if (element is Lesson) {
                lesson = element
            }
        }catch (e:Exception){
            Timber.i(e)
        }
        return binding.root
    }

    //speichert und geht zurück zum Menue
    private fun back(){
        save()
        var action = DatePickerFragmentDirections.actionDatePickerFragment2ToFragmentMenue()
        findNavController().navigate(action)

    }

    //speichert das Datum in dem File der Lesson
    private fun save() {
        try {

            Timber.i(binding.datePicker.year.toString())
            Timber.i(binding.datePicker.month.toString())
            Timber.i(binding.datePicker.dayOfMonth.toString())


            var updateLearningXML=UpdateLearningXML(this.requireContext())
            var month=binding.datePicker.month
            var day=binding.datePicker.dayOfMonth
            var year=binding.datePicker.year
            month+=1
            updateLearningXML.changeTimeLesson(lesson.path,lesson,day.toString()+"-"+month.toString()+"-"+year.toString())

        }catch (e:Exception){
            Timber.i(e)
        }


    }


}
