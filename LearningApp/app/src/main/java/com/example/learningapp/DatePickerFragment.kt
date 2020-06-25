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
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [DatePickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DatePickerFragment : Fragment() {
    private var year: Int = 0
    private var month: Int = 0
    private var dayOfMonth: Int = 0
    private lateinit var binding: FragmentDatePickerBinding
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
    private fun back(){
        save()
        var action = DatePickerFragmentDirections.actionDatePickerFragment2ToFragmentMenue()
        findNavController().navigate(action)

    }


    private fun save() {

        try {

            Timber.i(binding.datePicker.year.toString())
            Timber.i(binding.datePicker.month.toString())
            Timber.i(binding.datePicker.dayOfMonth.toString())

           /* var calendar = Calendar.getInstance()
            calendar.set(binding.datePicker.year, binding.datePicker.month, binding.datePicker.dayOfMonth)
            var date: Date = calendar.time*/

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
