package lk.malanadev.noteapp.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import lk.malanadev.noteapp.R
import lk.malanadev.noteapp.domain.model.NoteEntity

class NoteAdapter(
    private val onEdit:(NoteEntity) -> Unit,
    private val onDelete:(NoteEntity) -> Unit
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    private var notes: List<NoteEntity> = emptyList()

    fun setNotes(noteEntityList: List<NoteEntity>){
        notes = noteEntityList
        notifyDataSetChanged()
    }

    class NoteViewHolder(itemView: View): ViewHolder(itemView){
        fun bind(noteEntity: NoteEntity, onEdit: (NoteEntity) -> Unit, onDelete: (NoteEntity) -> Unit){
            itemView.findViewById<TextView>(R.id.txtTitle).text = noteEntity.title
            itemView.findViewById<TextView>(R.id.txtContent).text = noteEntity.content
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note = notes[position]
        holder.bind(note,onEdit,onDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val  view = LayoutInflater.from(parent.context).inflate(R.layout.note_adapter,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
       return notes.size
    }

}