package com.example.learningapp


import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.learningapp.databinding.FragmentInfoBinding
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_info.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_info : Fragment() {
    //Data Binding
    private lateinit var binding: FragmentInfoBinding

    //lesson
    private lateinit var lesson: Lesson

    //path der lesson
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
            generateImage()

            binding.SetEndTimeButton.setOnClickListener {
                todateBicker()
            }
            this.checkDate()
            this.changestatus()
        } catch (e: Exception) {
            Timber.i(e)
        }

        return binding.root
    }

    //  ändert das UI je nachdem ob Zeit abgelaufen/nicht abgelaufen ist oder nicht gesetzt ist
    private fun checkDate() {

        var string = lesson.getTime()
        if (lesson.gethasTime()) {
            try {


                if (lesson.hasTimerunout()) {

                    var daydifference = lesson.getTimeDifference()

                    binding.warnigTextView.visibility = View.VISIBLE
                    binding.warnigTextView.text = "nach " + daydifference.toString() + " Tage verfügbar"
                    binding.warnigTextView.setBackgroundColor(Color.GREEN)

                } else {
                    binding.imageViewDescribtion.visibility = View.GONE
                    binding.buttonQuestions.visibility = View.GONE
                    binding.imageViewDescribtion.visibility = View.GONE
                    binding.textViewInfo.visibility = View.GONE
                    binding.textViewHeading.visibility = View.GONE

                    binding.warnigTextView.visibility = View.VISIBLE
                    binding.warnigTextView.text = "Zeit abgelaufen"
                    binding.warnigTextView.setBackgroundColor(Color.RED)

                }


            } catch (e: Exception) {
                Timber.i(e)
            }
        } else {
            binding.SetEndTimeButton.visibility = View.VISIBLE
        }
    }

    //Methode für den Zeitsetzen Button
    private fun todateBicker() {
        var action = Fragment_infoDirections.actionFragmentInfoToDatePickerFragment2(lesson)
        findNavController().navigate(action)
    }

    //Methode für den Menue Button
    private fun generateMenue() {
        binding.buttonQuestions.setOnClickListener {
            genPopupMenue(it)
        }
    }

    //erstellt das Popup Menue
    private fun genPopupMenue(view: View) {
        var popupMenu: PopupMenu = PopupMenu(requireContext(), view)
        var itemList: MutableList<String> = mutableListOf()
        for (i in 0 until lesson.questions.size) {

            var menueString: String = lesson.questions[i].getName()
            if (lesson.questions[i].getfinished()) {
                menueString += " Wiederholung"
            }
            popupMenu.menu.add(1, i, i, menueString)
            if (lesson.questions[i].getfinished()) {
                var menuitem = popupMenu.menu.getItem(i)
                var spannableString: SpannableString = SpannableString(menueString)
                spannableString.setSpan(ForegroundColorSpan(Color.GREEN), 0, spannableString.length, 0)
                menuitem.setTitle(spannableString)
            }

            itemList.add(lesson.questions[i].getName())

        }
        popupMenu.setOnMenuItemClickListener {
            onMenuItemClick(it, itemList)
        }

        popupMenu.show()
    }

    //erstellt das Bild wenn es in der lesson definiert ist
    private fun generateImage() {
        var imagesrc: String = lesson.getImage()
        if (!(imagesrc.compareTo("null") == 0) && !(imagesrc.compareTo("") == 0)) {
            try {
                Timber.i("imagesrc " + imagesrc)
                val bImage = BitmapFactory.decodeStream(requireContext().assets.open("Images/" + imagesrc))//.decodeFile("assets/Images/"+imagesrc)

                binding.imageViewDescribtion.setImageBitmap(bImage)
                binding.imageViewDescribtion.visibility = View.VISIBLE

            } catch (e: java.lang.Exception) {
                Timber.i(e)
            }
        }
    }

    //setzt das File der Lesson auf fertig
    private fun changestatus() {
        if (this.checkiffinshed()) {
            var updateLearningXML: UpdateLearningXML = UpdateLearningXML(this.requireContext())
            updateLearningXML.changeLearnigElement(path, this.lesson, true, updateLearningXML.lessonntag)
            this.lesson.setfinished(true)
        }
    }

    //überprüft ob die Lesson erledigt wurde
    private fun checkiffinshed(): Boolean {
        for (question in this.lesson.questions) {
            if (!question.getfinished()) {
                return false
            }
        }
        return true
    }


    //wird dem klicken des Menues aufgerufen
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
