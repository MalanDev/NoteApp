package lk.malanadev.noteapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lk.malanadev.noteapp.R
import lk.malanadev.noteapp.databinding.FragmentAddUpdateBinding


class AddUpdateFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddUpdateBinding.inflate(inflater,container,false)



        // Inflate the layout for this fragment
        return binding.root
    }


}