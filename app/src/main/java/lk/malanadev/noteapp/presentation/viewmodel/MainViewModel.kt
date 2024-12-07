package lk.malanadev.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lk.malanadev.noteapp.domain.model.NoteEntity
import lk.malanadev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val noteRepository: NoteRepository
) :ViewModel() {

    val notes: LiveData<List<NoteEntity>> = noteRepository.getNotes()

    fun addNote(note:NoteEntity){
        viewModelScope.launch {
            noteRepository.addNote(note)
        }
    }

    fun updateNote(note:NoteEntity){
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun deleteNote(note:NoteEntity){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }


}