package com.example.learningapp

import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
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
    private lateinit var path:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding.RadioGroupQuestions.addView(null)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        Timber.i("onCreate_Beginn")

        try {
            /*   var xmlReader = this.context?.let { ReadLearningXMl(it, ".xml") }
               var subject: Subject = xmlReader!!.read()*/

            /*   xmlReader.testSubject(subject)
               question = subject.lessons[0].questions[0]*/
            var element = arguments?.let { QuestionFragmentArgs.fromBundle(it).recivedLearning.learningElement }
            path = arguments?.let { QuestionFragmentArgs.fromBundle(it).recivedLearning.path }.toString()
            if (element is Question) {
                question = element

            } else {
                Timber.i("Keine Frage")
            }

            this.context?.let { addButtons(it) }


            binding.buttonFinish.setOnClickListener {
                finshButtonAction()
            }

        } catch (e: Exception) {
            Timber.i("Error")
            Timber.i(e)

        }
        Timber.i("onCreate_Ende")
        // Inflate the layout for this fragment
        return binding.root //inflater.inflate(R.layout.fragment_question, container, false)
    }

    private fun finshButtonAction() {
        binding.textViewResult.visibility = View.VISIBLE
        binding.textViewResult.text = checkAnswer().toString()
        if (checkAnswer()) {
            binding.lilLayout.setBackgroundColor(Color.rgb(50,205,50))
            var updateLearningXML=UpdateLearningXML(this.requireContext())
            updateLearningXML.changeQuestion(this.path,this.question,true)


        } else {
            binding.lilLayout.setBackgroundColor(Color.rgb(255,99,7))
        }
    }

    private fun checkAnswer(): Boolean {
        for (i in 0..question.answer.size - 1) {
            for (atribut in question.answer[i].atributList) {
                if (atribut.name.compareTo("isCorrect") == 0 && atribut.text.compareTo("true") == 0) {

                    var button = binding.RadioGroupQuestions.get(i)

                    if (button is RadioButton) {
                        if (!button.isChecked) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    }


}
