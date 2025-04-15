package com.benyaamin.customernote.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.benyaamin.customernote.data.utils.toNote
import com.benyaamin.customernote.data.repository.NoteRepository
import com.benyaamin.customernote.models.Note
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ListViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _state = MutableSharedFlow<ListScreenActions>()
    val state = _state.asSharedFlow()
    val pager = noteRepository.notesPager()
        .flow
        .map { it.map { entity -> entity.toNote() } }
        .cachedIn(viewModelScope)

    fun processAction(action: ListScreenActions) {
        viewModelScope.launch {
            _state.emit(action)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

}