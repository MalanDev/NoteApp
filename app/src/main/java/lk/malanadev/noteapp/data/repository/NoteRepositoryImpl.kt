package lk.malanadev.noteapp.data.repository

import androidx.lifecycle.LiveData
import lk.malanadev.noteapp.data.local.NoteDao
import lk.malanadev.noteapp.domain.model.NoteEntity
import lk.malanadev.noteapp.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteDao: NoteDao):NoteRepository {
    override suspend fun addNote(note: NoteEntity) {
        return noteDao.addNote(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        return noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        return noteDao.deleteNote(note)
    }

    override fun getNotes(): LiveData<List<NoteEntity>> {
      return noteDao.getNotes()
    }
}