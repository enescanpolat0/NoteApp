package com.enescanpolat.noteapp.presentation.notes

import com.enescanpolat.noteapp.domain.model.Note
import com.enescanpolat.noteapp.util.NoteOrder

sealed class NotesEvent{


    data class Order(val noteOrder: NoteOrder):NotesEvent()

    data class DeleteNote(val note:Note):NotesEvent()

    object RestoreNote:NotesEvent()

    object ToggleOrderSection:NotesEvent()
}
