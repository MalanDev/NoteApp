package lk.malanadev.noteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteDao::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun noteDao(): NoteDao
}