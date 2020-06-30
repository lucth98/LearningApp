package com.example.learningapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.learningapp.databinding.FragmentQuestionBinding
import timber.log.Timber


class QuestionFragment : Fragment() {
    //Data Binding
    private lateinit var binding: FragmentQuestionBinding

    //Questoin
    private lateinit var question: Question

    //phat des Files der Question
    private lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //Fügt für jede Antwort in der Frage einen Antwort Radio Button hinzu
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
            // binding.RadioGroupQuestions.addView(radioButton)
            binding.QuestionsLayout.addView(radioButton)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        Timber.i("onCreate_Beginn")

        try {
            var element = arguments?.let { QuestionFragmentArgs.fromBundle(it).lernElement }
            path = arguments?.let { QuestionFragmentArgs.fromBundle(it).lernElement.path }.toString()

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
            binding.buttonClear.setOnClickListener { clear() }
        } catch (e: Exception) {
            Timber.i("Error")
            Timber.i(e)
        }
        Timber.i("onCreate_Ende")

        return binding.root
    }

    //Methode für den Zurück zur lesonn Button
    private fun backToLesson() {
        val navController = this.findNavController()
        navController.navigateUp()
    }

    //editier das UI je nach der richtigkeit der Antwort
    private fun finshButtonAction() {
        try {

            binding.textViewResult.text = checkAnswer().toString()
            var answerisRight: Boolean? = checkAnswer()
            if (answerisRight != null) {
                makeButtonGone()
                binding.buttonClear.visibility=View.GONE
                binding.RadioGroupQuestions.visibility = View.GONE
                binding.textViewResult.visibility = View.VISIBLE
                if (answerisRight) {
                    binding.lilLayout.setBackgroundColor(Color.rgb(50, 205, 50))
                    var updateLearningXML = UpdateLearningXML(this.requireContext())
                    updateLearningXML.changeLearnigElement(this.path, this.question, true, updateLearningXML.questiontag)
                    question.setfinished(true)
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

    private fun clear(){
        var layout = binding.QuestionsLayout

        for (i in 0..layout.childCount) {
            var view = layout.getChildAt(i)
            if (view is RadioButton) {
                view.isChecked=false
            }
        }

    }

    private fun makeButtonGone(){
        var layout = binding.QuestionsLayout

        for (i in 0..layout.childCount) {
            var view = layout.getChildAt(i)
            if (view is RadioButton) {
                view.visibility=View.GONE
            }
        }

    }
    private fun makeButtonVissible(){
        var layout = binding.QuestionsLayout

        for (i in 0..layout.childCount) {
            var view = layout.getChildAt(i)
            if (view is RadioButton) {
                view.visibility=View.VISIBLE
            }
        }

    }

    //setzt das UI zurück
    private fun reset() {
        clear()
        makeButtonVissible()
        binding.buttonFinish.visibility = View.VISIBLE
        binding.RadioGroupQuestions.visibility = View.VISIBLE
        binding.textViewResult.visibility = View.GONE
        binding.menueButton.visibility = View.GONE
        binding.resetButton.visibility = View.GONE
        binding.lilLayout.setBackgroundColor(Color.WHITE)
        binding.buttonClear.visibility=View.VISIBLE
    }

    //überprüft die Antwort
    private fun checkAnswer(): Boolean? {
        /*   var index: Int = binding.RadioGroupQuestions.checkedRadioButtonId
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
           return value*/
        var layout = binding.QuestionsLayout
        if (this.chekifanswerd()) {
            for (i in 0 until question.answer.size) {
                var view = layout.getChildAt(i)
                if (view is RadioButton) {
                        for (atribut in question.answer[i].atributList) {
                            if (atribut.name.compareTo("isCorrect") == 0 ) {
                               var isRight:Boolean = atribut.text.toBoolean()
                                if((view.isChecked&&!isRight)||(isRight&&!view.isChecked)){
                                    return false
                                }
                            }
                        }
                }
            }
            return true
        } else {
            return null
        }

    }


    private fun chekifanswerd(): Boolean {
        var layout = binding.QuestionsLayout

            for (i in 0..layout.childCount) {
                var view = layout.getChildAt(i)
                if (view is RadioButton) {
                    if (view.isChecked) {
                        return true
                    }
                }
        }
        return false
    }
}