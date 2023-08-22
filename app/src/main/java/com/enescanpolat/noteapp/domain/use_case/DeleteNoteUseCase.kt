package com.enescanpolat.noteapp.domain.use_case

import com.enescanpolat.noteapp.domain.model.Note
import com.enescanpolat.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {


    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}