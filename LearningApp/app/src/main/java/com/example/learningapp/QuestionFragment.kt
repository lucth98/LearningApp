package com.example.learningapp

import android.content.Context
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment() {
    private lateinit var binding: FragmentQuestionBinding
    private lateinit var question: Question

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


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
            var xmlReader = this.context?.let { ReadLearningXMl(it, "testlearning2.xml") }
            var subject: Subject = xmlReader!!.read()

            xmlReader.testSubject(subject)
            question = subject.lessons[0].questions[0]

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
        binding.textViewResult.text = checkAnswer().toString()

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
