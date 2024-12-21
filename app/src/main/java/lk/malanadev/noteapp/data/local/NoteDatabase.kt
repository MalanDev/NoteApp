package lk.malanadev.noteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import lk.malanadev.noteapp.domain.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun noteDao(): NoteDao
}