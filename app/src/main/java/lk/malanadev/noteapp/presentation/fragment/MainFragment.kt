package lk.malanadev.noteapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import lk.malanadev.noteapp.R
import lk.malanadev.noteapp.databinding.FragmentMainBinding
import lk.malanadev.noteapp.presentation.ui.NoteAdapter
import lk.malanadev.noteapp.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var adapter: NoteAdapter

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        adapter = NoteAdapter(onEdit = {
            val navigation = MainFragmentDirections.actionMainFragmentToAddUpdateFragment(it.id)
            findNavController().navigate(navigation)
        }, onDelete = {
            viewModel.deleteNote(it)
        })

        viewModel.notes.observe(viewLifecycleOwner){notes->
            adapter.setNotes(notes)
        }

        binding.recycler.layoutManager = LinearLayoutManager(parentFragment?.context)

        binding.recycler.adapter = adapter


        binding.fabAdd.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_addUpdateFragment)
        }


        return binding.root
    }


}