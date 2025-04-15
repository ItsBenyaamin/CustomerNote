package com.benyaamin.customernote.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.benyaamin.customernote.data.local.CustomerNoteDatabase
import com.benyaamin.customernote.data.local.entity.NoteEntity
import com.benyaamin.customernote.data.utils.toEntity
import com.benyaamin.customernote.data.utils.toNote
import com.benyaamin.customernote.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    database: CustomerNoteDatabase,
) : NoteRepository {
    private val noteDao = database.noteDao()

    override fun notesPager(): Pager<Int, NoteEntity> {
        return Pager(
            config = PagingConfig(pageSize = 12)
        ) {
            noteDao.getNotesPagingSource()
        }
    }

    override suspend fun searchNote(query: String): List<Note> {
        return noteDao.queryNotes(query)
            .map { it.toNote() }
    }

    override suspend fun getNote(id: Int): Note {
        return noteDao.getNote(id).toNote()
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toEntity())
        notesPager()
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.id)
    }

    override suspend fun editNote(note: Note) {
        noteDao.updateNote(note.toEntity())
    }

    override fun noteFlow(id: Int): Flow<Note> {
        return noteDao
            .getNoteFlow(id)
            .filterNotNull()
            .map { it.toNote() }
            .flowOn(Dispatchers.IO)
    }
}