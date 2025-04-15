package com.benyaamin.customernote.data.repository

import androidx.paging.Pager
import com.benyaamin.customernote.data.local.entity.NoteEntity
import com.benyaamin.customernote.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun notesPager(): Pager<Int, NoteEntity>
    suspend fun getNote(id: Int): Note
    suspend fun searchNote(query: String): List<Note>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun editNote(note: Note)

    fun noteFlow(id: Int): Flow<Note>
}