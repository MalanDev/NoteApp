package lk.malanadev.noteapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lk.malanadev.noteapp.data.local.NoteDao
import lk.malanadev.noteapp.data.local.NoteDatabase
import lk.malanadev.noteapp.data.repository.NoteRepositoryImpl
import lk.malanadev.noteapp.domain.repository.NoteRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context):NoteDatabase{
        return Room.databaseBuilder(context,NoteDatabase::class.java,"note_database").build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(database: NoteDatabase):NoteDao{
        return database.noteDao()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao):NoteRepository{
        return NoteRepositoryImpl(noteDao)
    }
}