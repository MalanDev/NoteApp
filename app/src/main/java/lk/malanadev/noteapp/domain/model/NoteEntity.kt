package lk.malanadev.noteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var firebaseId:String = "",
    var title: String,
    var content: String,
    val timestamp: Long
){
    constructor():this( 0,"","","", Date().time)
    constructor(title:String,content: String):this(0,"",title,content,Date().time)
}
