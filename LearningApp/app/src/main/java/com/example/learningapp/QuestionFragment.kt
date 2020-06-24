package com.example.learningapp

import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.learningapp.databinding.FragmentQuestionBinding
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment() {
    private lateinit var binding: FragmentQuestionBinding
    private lateinit var question: Question
    private lateinit var path: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    private fun addButtons(context: Context) {
        var radioButton: RadioButton
        Timber.i("abbButtons_Beginn")
        Timber.i(question.text)

        binding.textViewQuestionText.text = question.text

        for (i in 0..question.answer.size - 1) {
            radioButton = RadioButton(context)
            radioButton.text = question.answer[i].text

            radioButton.id = i

            Timber.i("Antwort NR= " + i + " text= " + question.answer[i].text)

            binding.RadioGroupQuestions.addView(radioButton)


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        Timber.i("onCreate_Beginn")

        try {

            var element = arguments?.let { QuestionFragmentArgs.fromBundle(it).lernElement }
            path = arguments?.let { QuestionFragmentArgs.fromBundle(it).lernElement.path }.toString()
            // Timber.i("path ="+path)
            if (element is Question) {
                question = element

            } else {
                Timber.i("Keine Frage")
            }

            this.context?.let { addButtons(it) }


            binding.buttonFinish.setOnClickListener {
                finshButtonAction()
            }
            binding.resetButton.setOnClickListener { reset() }
            binding.menueButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_questionFragment_to_startFragment))
            binding.backToLessonButton.setOnClickListener { backToLesson() }

        } catch (e: Exception) {
            Timber.i("Error")
            Timber.i(e)

        }
        Timber.i("onCreate_Ende")
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun backToLesson() {
        val navController = this.findNavController()
        navController.navigateUp()
    }

    private fun finshButtonAction() {
        try {

            binding.textViewResult.text = checkAnswer().toString()
            var answerisRight: Boolean? = checkAnswer()
            if (answerisRight != null) {
                binding.RadioGroupQuestions.visibility = View.GONE
                binding.textViewResult.visibility = View.VISIBLE
                if (answerisRight) {
                    binding.lilLayout.setBackgroundColor(Color.rgb(50, 205, 50))
                    var updateLearningXML = UpdateLearningXML(this.requireContext())
                    //updateLearningXML.changeQuestion(this.path, this.question, true)
                    updateLearningXML.changeLearnigElement(this.path, this.question, true, updateLearningXML.questiontag)
                    binding.menueButton.visibility = View.VISIBLE
                    binding.backToLessonButton.visibility = View.VISIBLE
                    binding.buttonFinish.visibility = View.GONE


                } else {
                    binding.lilLayout.setBackgroundColor(Color.rgb(255, 99, 7))

                    binding.menueButton.visibility = View.VISIBLE
                    binding.resetButton.visibility = View.VISIBLE
                    binding.buttonFinish.visibility = View.GONE


                }
            }

        } catch (e: Exception) {
            Timber.i(e)
        }
    }


    private fun reset() {
        binding.buttonFinish.visibility = View.VISIBLE
        binding.RadioGroupQuestions.visibility = View.VISIBLE
        binding.textViewResult.visibility = View.GONE
        binding.menueButton.visibility = View.GONE
        binding.resetButton.visibility = View.GONE
        binding.lilLayout.setBackgroundColor(Color.WHITE)


    }

    private fun checkAnswer(): Boolean? {
        var index: Int = binding.RadioGroupQuestions.checkedRadioButtonId
        var value: Boolean? = null

        if (index == -1) {
            value = null
        } else {
            for (i in 0 until question.answer.size) {
                for (atribut in question.answer[i].atributList) {

                    if (atribut.name.compareTo("isCorrect") == 0 && atribut.text.compareTo("true") == 0) {
                        value = index == i
                        break
                    }
                }
            }
        }

        return value
    }
}