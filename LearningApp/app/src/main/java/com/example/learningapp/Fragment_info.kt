package com.example.learningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.learningapp.databinding.FragmentInfoBinding
import timber.log.Timber
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_info.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_info : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var lesson: Lesson

    private lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        try {
            var element = arguments?.let { Fragment_infoArgs.fromBundle(it).lernElement }

            path = arguments?.let { Fragment_infoArgs.fromBundle(it).lernElement.path }.toString()
            if (element is Lesson) {
                lesson = element
                binding.textViewHeading.text = lesson.getName()
                binding.textViewInfo.text = lesson.text
            }
            generateMenue()
            this.changestatus()
        } catch (e: Exception) {
            Timber.i(e)
        }

        return binding.root
    }


    private fun generateMenue() {

        binding.buttonQuestions.setOnClickListener {
            genPopupMenue(it)
        }
    }

    private fun genPopupMenue(view: View) {
        var popupMenu: PopupMenu = PopupMenu(requireContext(), view)
        var itemList: MutableList<String> = mutableListOf()
        for (i in 0 until lesson.questions.size) {

            var menueString:String=lesson.questions[i].getName()
            if(lesson.questions[i].getfinished())
            {
                menueString+=" Wiederholung"
            }
            popupMenu.menu.add(1, i, i, menueString)

            itemList.add(lesson.questions[i].getName())

        }
        popupMenu.setOnMenuItemClickListener {
            onMenuItemClick(it, itemList)
        }

        popupMenu.show()
    }

    private fun changestatus(){
        if(this.checkiffinshed()){
            var updateLearningXML:UpdateLearningXML= UpdateLearningXML(this.requireContext())
            updateLearningXML.changeLearnigElement(path,this.lesson,true,updateLearningXML.lessonntag)
        }
    }



    private fun checkiffinshed():Boolean{
        for (question in this.lesson.questions){
            if(!question.getfinished()){
                return false
            }
        }
        return true
    }

    @Override
    private fun onMenuItemClick(item: MenuItem, itemList: MutableList<String>): Boolean {

        if (item.itemId < itemList.size) {


            for (quest in lesson.questions) {
                if (quest.getName() == itemList[item.itemId]) {

                    quest.path = this.path

                    var action = Fragment_infoDirections.actionFragmentInfoToQuestionFragment(quest)
                    findNavController().navigate(action)
                }
            }

            return true
        }
        return false

    }


}
