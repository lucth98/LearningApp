package com.example.learningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.DatePickerDialog
import android.app.Dialog

import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
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

            var calendar = Calendar.getInstance()
            calendar.set(binding.datePicker.year, binding.datePicker.month, binding.datePicker.dayOfMonth)
            var date: Date = calendar.time


            var updateLearningXML=UpdateLearningXML(this.requireContext())
            updateLearningXML.changeTimeLesson(lesson.path,lesson,date.toString())




        }catch (e:Exception){
            Timber.i(e)
        }


    }
    /*
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(this.requireContext(), this, year, month, day)

    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.dayOfMonth=dayOfMonth
        this.month=month
        this.year=year
        Timber.i("year= "+year)
        Timber.i("day= "+dayOfMonth)
        Timber.i("month= "+month)
    }
*/

}
