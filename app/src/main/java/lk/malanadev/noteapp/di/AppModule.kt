package lk.malanadev.noteapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lk.malanadev.noteapp.data.local.NoteDao
import lk.malanadev.noteapp.data.local.NoteDatabase
import lk.malanadev.noteapp.data.repository.NoteRepositoryFirebaseImpl
import lk.malanadev.noteapp.data.repository.NoteRepositoryImpl
import lk.malanadev.noteapp.data.repository.NotificationRepositoryImpl
import lk.malanadev.noteapp.domain.repository.NoteRepository
import lk.malanadev.noteapp.domain.repository.NoteRepositoryFirebase
import lk.malanadev.noteapp.domain.repository.NotificationRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

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

    @Provides
    @Singleton
    fun provideFirebaseNoteRepository(db:FirebaseFirestore):NoteRepositoryFirebase{
        return NoteRepositoryFirebaseImpl(db)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository():NotificationRepository{
        return NotificationRepositoryImpl()
    }
}