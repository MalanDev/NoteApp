package lk.malanadev.noteapp.domain.repository

import androidx.lifecycle.LiveData
import lk.malanadev.noteapp.domain.model.NoteEntity

interface NoteRepository {

    suspend fun addNote(note:NoteEntity)

    suspend fun updateNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)

    fun getNotes(): LiveData<List<NoteEntity>>

    suspend fun getNoteById(noteId:Int): NoteEntity
}