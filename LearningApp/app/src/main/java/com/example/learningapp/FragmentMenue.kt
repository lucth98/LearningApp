package com.example.learningapp

import android.content.res.AssetManager
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.learningapp.databinding.FragmentMenueBinding
import timber.log.Timber


class FragmentMenue : Fragment() {
    // private lateinit var binding: FragmentStartBinding
    private lateinit var binding: FragmentMenueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menue, container, false)
        // Inflate the layout for this fragment
        generateMenue()

        return binding.root
    }


    private fun generateMenue() {

        var saveFiles = SaveFiles(this.requireContext())
       // saveFiles.deleteFilesinInternalStorage()   //löscht die files wieder
        saveFiles.moveXMLtoInternalStorage()


        var filenameslist = saveFiles.getFilnamesInternalStorage()
        var subjectlist: MutableList<Subject> = mutableListOf()
        try {
            for (name in filenameslist) {
                var path: String = name.toString()// "LearnigFiles/" + name.toString()
                var readLearningXMl: ReadLearningXMl = ReadLearningXMl(this.requireContext(), path)

                var subject = readLearningXMl.read()
                subject.path = path
                subjectlist.add(subject)
            }

            for ((index, value) in subjectlist.withIndex()) {

                var button: Button = Button(this.requireContext())
                button.text = subjectlist[index].getName().toString()
                button.setOnClickListener { genPopupMenue(it, subjectlist[index]) }
                binding.menueLayout.addView(button)
            }
        } catch (e: Exception) {
            Timber.i(e)
        }
    }

    private fun genPopupMenue(view: View, subject: Subject) {
        var popupMenu: PopupMenu = PopupMenu(requireContext(), view)
        var itemList: MutableList<String> = mutableListOf()
        for (i in 0 until subject.lessons.size) {
            var menuinput:String=subject.lessons[i].getName()
            if(subject.lessons[i].getfinished())
            {
                menuinput+=" Wiederholung"
            }
            popupMenu.menu.add(1, i, i, menuinput)

            if(subject.lessons[i].getfinished())
            {
                var menuitem=popupMenu.menu.getItem(i)
                var spannableString: SpannableString = SpannableString(menuinput)
                spannableString.setSpan(ForegroundColorSpan(Color.GREEN),0,spannableString.length,0)
                menuitem.setTitle(spannableString)
            }
            itemList.add(subject.lessons[i].getName())
        }
        popupMenu.setOnMenuItemClickListener {
            onMenuItemClick(it, itemList, subject)
        }
        popupMenu.show()
    }




    @Override
    private fun onMenuItemClick(item: MenuItem, itemList: MutableList<String>, subject: Subject): Boolean {

        if (item.itemId < itemList.size) {
            binding.textViewTest.text = itemList[item.itemId]


            for (less in subject.lessons) {
                if (less.getName() == itemList[item.itemId]) {
                    less.path=subject.path
                    //Timber.i("path= "+less.path)

                    var action = FragmentMenueDirections.actionFragmentMenueToFragmentInfo(less)//FragmentMenueDirections.actionFragmentMenueToFragmentInfo(serilLearningElement)
                    findNavController().navigate(action)
                }
            }
            return true
        }
        return false
    }


}
