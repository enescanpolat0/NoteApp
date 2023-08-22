package com.enescanpolat.noteapp.domain.use_case

import com.enescanpolat.noteapp.domain.model.InvalidNoteException
import com.enescanpolat.noteapp.domain.model.Note
import com.enescanpolat.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NoteRepository) {


    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The content of the note can't be empty")
        }

        repository.insertNote(note)
    }

}