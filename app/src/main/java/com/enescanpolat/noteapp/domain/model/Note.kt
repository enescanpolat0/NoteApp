package com.enescanpolat.noteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.enescanpolat.noteapp.presentation.ui.theme.Black
import com.enescanpolat.noteapp.presentation.ui.theme.Blue
import com.enescanpolat.noteapp.presentation.ui.theme.Red
import com.enescanpolat.noteapp.presentation.ui.theme.Yellow

@Entity
data class Note(
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int,
    @PrimaryKey val id:Int?=null,
){
    companion object{
        val noteColors = listOf(Red, Yellow, Blue, Black)
    }
}

class InvalidNoteException(message:String) : Exception(message)
