package lk.malanadev.noteapp.presentation.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import lk.malanadev.noteapp.R
import lk.malanadev.noteapp.data.remote.NetworkResult
import lk.malanadev.noteapp.databinding.ActivityMainBinding
import lk.malanadev.noteapp.databinding.FragmentMainBinding
import lk.malanadev.noteapp.presentation.ui.NoteAdapter
import lk.malanadev.noteapp.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var adapter: NoteAdapter

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        adapter = NoteAdapter(onEdit = {
            val navigation = MainFragmentDirections.actionMainFragmentToAddUpdateFragment(it.id,it.firebaseId)
            findNavController().navigate(navigation)
        }, onDelete = {
            val builder = AlertDialog.Builder(context)

            builder
                .setMessage("Do you want to delete Note?")
                .setNegativeButton("No"){dailog,listner->

                }
                .setPositiveButton("Yes"){diaolog,listner->
                    viewModel.deleteNote(it)
                }

            val dialog = builder.create()
            dialog.show()

        })

        viewModel.notes.observe(viewLifecycleOwner){result->

            when(result){
                is NetworkResult.Success ->{
                    adapter.setNotes(result.data)
                    binding.mainProgressBar.visibility = View.GONE
                }
                is NetworkResult.Error ->{
                    Toast.makeText(context,result.message,Toast.LENGTH_SHORT).show()
                    binding.mainProgressBar.visibility = View.GONE
                }

                is NetworkResult.Loading ->{
                    binding.mainProgressBar.visibility = View.VISIBLE
                }
            }


        }

        viewModel.deletedNote.observe(viewLifecycleOwner){result->
            when(result){
                is NetworkResult.Success ->{
                    Toast.makeText(context,"Note Deleted!",Toast.LENGTH_SHORT).show()
                    binding.mainProgressBar.visibility = View.GONE
                   getNotes()
                }
                is NetworkResult.Error ->{
                    Toast.makeText(context,result.message,Toast.LENGTH_SHORT).show()
                    binding.mainProgressBar.visibility = View.GONE
                }

                is NetworkResult.Loading ->{
                    binding.mainProgressBar.visibility = View.VISIBLE
                }
            }
        }

        binding.recycler.layoutManager = LinearLayoutManager(parentFragment?.context)

        binding.recycler.adapter = adapter


        binding.fabAdd.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_addUpdateFragment)
        }

        getNotes()

        return binding.root
    }

    private fun getNotes(){
        binding.mainProgressBar.visibility = View.VISIBLE
        viewModel.getNotes()
    }


}