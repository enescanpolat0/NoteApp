package com.enescanpolat.noteapp.presentation.notes

import com.enescanpolat.noteapp.domain.model.Note
import com.enescanpolat.noteapp.util.NoteOrder
import com.enescanpolat.noteapp.util.OrderType

data class NotesState(
    val notes:List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean=false
)
