package lk.malanadev.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lk.malanadev.noteapp.domain.model.NoteEntity
import lk.malanadev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val noteRepository: NoteRepository
) :ViewModel() {

    val notes: LiveData<List<NoteEntity>> = noteRepository.getNotes()

    val selectedNote = MutableLiveData<NoteEntity>()

    fun addNote(note:NoteEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepository.addNote(note)
            }
        }
    }

    fun getNoteById(noteId:Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               val note =  noteRepository.getNoteById(noteId)
                selectedNote.postValue(note)
            }
        }
    }

    fun updateNote(note:NoteEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepository.updateNote(note)
            }

        }



    }

    fun deleteNote(note:NoteEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepository.deleteNote(note)
            }
        }
    }


}