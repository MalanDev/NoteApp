package lk.malanadev.noteapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import lk.malanadev.noteapp.R
import lk.malanadev.noteapp.databinding.FragmentAddUpdateBinding
import lk.malanadev.noteapp.domain.model.NoteEntity
import lk.malanadev.noteapp.presentation.viewmodel.MainViewModel


@AndroidEntryPoint
class AddUpdateFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private val args: AddUpdateFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddUpdateBinding.inflate(inflater,container,false)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var currentNote = NoteEntity()
        activity.addMenuProvider(object:MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    android.R.id.home -> {
                        findNavController().navigate(R.id.action_addUpdateFragment_to_mainFragment)
                        true
                    }
                    else -> false
                }
            }

        })

        if(args.noteId != 0){
            binding.btnUpdate.setText("Update")
            viewModel.getNoteById(args.noteId)
        }else{
            binding.btnUpdate.setText("Add")
        }

        viewModel.selectedNote.observe(viewLifecycleOwner){
            binding.txtTitle.setText(it.title.toString())
            binding.txtContent.setText(it.content.toString())
            currentNote = it
        }




        binding.btnUpdate.setOnClickListener {
            if (binding.txtTitle.text.isEmpty()) {
                Toast.makeText(context, "Please add title", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (binding.txtContent.text.isEmpty()) {
                Toast.makeText(context, "Please add content", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            if (args.noteId == 0) {
                currentNote = NoteEntity(
                    title = binding.txtTitle.text.toString(),
                    content = binding.txtContent.text.toString()
                )
                viewModel.addNote(currentNote)
                Toast.makeText(context,"Succesfully Added!",Toast.LENGTH_LONG).show()
            } else {
                currentNote.apply {
                    title = binding.txtTitle.text.toString()
                    content = binding.txtContent.text.toString()
                }
                viewModel.updateNote(currentNote)
                Toast.makeText(context,"Succesfully Updated!",Toast.LENGTH_LONG).show()
            }


        }

        // Inflate the layout for this fragment
        return binding.root
    }


}