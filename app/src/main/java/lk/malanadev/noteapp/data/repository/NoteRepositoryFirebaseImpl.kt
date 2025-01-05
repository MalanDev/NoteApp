package lk.malanadev.noteapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import lk.malanadev.noteapp.data.remote.NetworkResult
import lk.malanadev.noteapp.domain.model.NoteEntity
import lk.malanadev.noteapp.domain.repository.NoteRepositoryFirebase
import lk.malanadev.noteapp.utils.CommonUtils

class NoteRepositoryFirebaseImpl(
    private val db: FirebaseFirestore
):NoteRepositoryFirebase {
    override suspend fun addNote(note: NoteEntity, result: (NetworkResult<NoteEntity>) -> Unit) {
        note.firebaseId = db.collection(CommonUtils.FIREBASE_COLLECTION_NAME).document().id
        db.collection(CommonUtils.FIREBASE_COLLECTION_NAME).document(note.firebaseId).set(
            note
        )

            .addOnSuccessListener {

            result.invoke(
                NetworkResult.Success(note)
            )
        }.addOnFailureListener {
            result.invoke(
                NetworkResult.Error(it.localizedMessage.toString())
            )
        }
    }

    override suspend fun updateNote(note: NoteEntity, result: (NetworkResult<NoteEntity>) -> Unit) {
        db.collection(CommonUtils.FIREBASE_COLLECTION_NAME)
            .document(note.firebaseId)
            .set(note)
            .addOnSuccessListener {
                result.invoke(
                    NetworkResult.Success(note)
                )
            }.addOnFailureListener {
                result.invoke(
                    NetworkResult.Error(it.localizedMessage.toString())
                )
            }
    }

    override suspend fun deleteNote(note: NoteEntity, result: (NetworkResult<String>) -> Unit) {
        db.collection(CommonUtils.FIREBASE_COLLECTION_NAME)
            .document(note.firebaseId)
            .delete()
            .addOnSuccessListener {
                result.invoke(
                    NetworkResult.Success("Successfully Updated!")
                )
            }.addOnFailureListener {
                result.invoke(
                    NetworkResult.Error(it.localizedMessage.toString())
                )
            }
    }

    override suspend fun getNotes(result: (NetworkResult<List<NoteEntity>>) -> Unit) {
        db.collection(CommonUtils.FIREBASE_COLLECTION_NAME)
            .get()
            .addOnSuccessListener {
                val noteList = arrayListOf<NoteEntity>()
                for (document in it){
                    val note = document.toObject(NoteEntity::class.java)
                    noteList.add(note)
                }
                result.invoke(
                    NetworkResult.Success(noteList)
                )
            }.addOnFailureListener {
                result.invoke(
                    NetworkResult.Error(it.localizedMessage.toString())
                )
            }
    }

    override suspend fun getNoteById(firebaseId: String, result: (NetworkResult<NoteEntity?>) -> Unit) {
        db.collection(CommonUtils.FIREBASE_COLLECTION_NAME)
            .document(firebaseId)
            .get()
            .addOnSuccessListener {
                val note = it.toObject(NoteEntity::class.java)
                result.invoke(
                    NetworkResult.Success(note)
                )
            }.addOnFailureListener {
                result.invoke(
                    NetworkResult.Error(it.localizedMessage.toString())
                )
            }
    }


}