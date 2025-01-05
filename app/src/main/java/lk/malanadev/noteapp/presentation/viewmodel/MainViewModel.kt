package lk.malanadev.noteapp.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lk.malanadev.noteapp.data.remote.NetworkResult
import lk.malanadev.noteapp.domain.model.NoteEntity
import lk.malanadev.noteapp.domain.repository.NoteRepository
import lk.malanadev.noteapp.domain.repository.NoteRepositoryFirebase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val noteRepository: NoteRepository,
    private val noteRepositoryFirebase: NoteRepositoryFirebase
) :ViewModel() {

//    val notes: LiveData<List<NoteEntity>> = noteRepository.getNotes()

    val notes = MutableLiveData<NetworkResult<List<NoteEntity>>>()

    val selectedNote = MutableLiveData<NetworkResult<NoteEntity?>>()

    val addedNote = MutableLiveData<NetworkResult<NoteEntity?>>()

    val updatedNote = MutableLiveData<NetworkResult<NoteEntity?>>()

    val deletedNote = MutableLiveData<NetworkResult<String>>()

    fun getNotes(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                noteRepositoryFirebase.getNotes {result->
                   notes.postValue(result)
                }
            }
        }
    }

    fun addNote(note:NoteEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepositoryFirebase.addNote(note,{result->
                    addedNote.postValue(result)
                })
            }
        }
    }

    fun getNoteById(firebaseId:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               noteRepositoryFirebase.getNoteById(firebaseId,{result ->
                   selectedNote.postValue(result)
               })

            }
        }
    }

    fun updateNote(note:NoteEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepositoryFirebase.updateNote(note,{result->
                    updatedNote.postValue(result)
                })
            }

        }



    }

    fun deleteNote(note:NoteEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepositoryFirebase.deleteNote(note,{result ->
                  deletedNote.postValue(result)
                })
            }
        }
    }


}