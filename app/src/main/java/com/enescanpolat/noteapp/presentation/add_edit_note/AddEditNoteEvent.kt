package com.enescanpolat.noteapp.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent{
    data class EnteredTile(val value:String):AddEditNoteEvent()
    data class ChangeFocusTile(val focusState: FocusState):AddEditNoteEvent()
    data class EnteredContent(val value:String):AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState):AddEditNoteEvent()
    data class ChangeColor(val color:Int):AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}