package com.enescanpolat.noteapp.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enescanpolat.noteapp.domain.model.InvalidNoteException
import com.enescanpolat.noteapp.domain.model.Note
import com.enescanpolat.noteapp.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditNoteViewModel
@Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
    ) : ViewModel(){

    private val _noteTitle = mutableStateOf(NotesTextFieldState(
        hint = "Enter Title"
    ))
    val noteTitle : State<NotesTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NotesTextFieldState(
        hint = "Enter Content"
    ))
    val noteContent : State<NotesTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf<Int>(Note.noteColors.random().toArgb())
    val noteColor : State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId:Int?=null


    init {
        savedStateHandle.get<Int>("noteId")?.let {noteId->
            if (noteId != -1){
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also {note->
                        currentNoteId=note.id
                        _noteTitle.value=noteTitle.value.copy(
                            text = note.title,
                            ishintvisible = true
                        )
                        _noteContent.value=noteContent.value.copy(
                            text = note.content,
                            ishintvisible = true
                        )
                        _noteColor.value=note.color
                    }
                }
            }
        }
    }




    fun onEvent(event:AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnteredTile->{
                _noteTitle.value=noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeFocusTile->{
                _noteTitle.value=noteTitle.value.copy(
                    ishintvisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent->{
                _noteContent.value=noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus->{
                _noteContent.value=noteContent.value.copy(
                    ishintvisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor->{
                _noteColor.value=event.color
            }

            is AddEditNoteEvent.SaveNote->{
                viewModelScope.launch {
                    try {

                        noteUseCases.addNote(Note(
                            title = noteTitle.value.text,
                            content = noteContent.value.text,
                            timestamp = System.currentTimeMillis(),
                            color = noteColor.value,
                            id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)


                    }catch (e:InvalidNoteException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(message = e.message?:"Error")
                        )
                    }
                }
            }




        }
    }


    sealed class UiEvent{
        data class ShowSnackBar(val message:String):UiEvent()
        object SaveNote:UiEvent()
    }

}