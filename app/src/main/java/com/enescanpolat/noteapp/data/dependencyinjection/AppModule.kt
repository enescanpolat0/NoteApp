package com.enescanpolat.noteapp.data.dependencyinjection

import android.app.Application
import androidx.room.Room
import com.enescanpolat.noteapp.data.repository.NoteRepositoryImpl
import com.enescanpolat.noteapp.data.room.NoteDatabase
import com.enescanpolat.noteapp.domain.repository.NoteRepository
import com.enescanpolat.noteapp.domain.use_case.AddNoteUseCase
import com.enescanpolat.noteapp.domain.use_case.DeleteNoteUseCase
import com.enescanpolat.noteapp.domain.use_case.GetNoteUseCase
import com.enescanpolat.noteapp.domain.use_case.GetNotesUseCase
import com.enescanpolat.noteapp.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectNoteDatabase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun injectNoteRepository(db:NoteDatabase):NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }


    @Singleton
    @Provides
    fun injectUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }

}