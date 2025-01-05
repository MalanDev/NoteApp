package lk.malanadev.noteapp.domain.repository

import androidx.lifecycle.LiveData
import lk.malanadev.noteapp.data.remote.NetworkResult
import lk.malanadev.noteapp.domain.model.NoteEntity

interface NoteRepositoryFirebase {

    suspend fun addNote(note:NoteEntity,result:(NetworkResult<NoteEntity>)-> Unit)

    suspend fun updateNote(note: NoteEntity,result:(NetworkResult<NoteEntity>)-> Unit)

    suspend fun deleteNote(note: NoteEntity,result:(NetworkResult<String>)-> Unit)

    suspend fun getNotes(result:(NetworkResult<List<NoteEntity>>)-> Unit)

    suspend fun getNoteById(firebaseId:String,result: (NetworkResult<NoteEntity?>) -> Unit)
}