package com.enescanpolat.noteapp.data.repository

import com.enescanpolat.noteapp.data.room.NoteDao
import com.enescanpolat.noteapp.domain.model.Note
import com.enescanpolat.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) :NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}