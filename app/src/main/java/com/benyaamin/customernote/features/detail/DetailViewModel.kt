package com.benyaamin.customernote.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benyaamin.customernote.data.repository.NoteRepository
import com.benyaamin.customernote.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val noteRepository: NoteRepository,
) : ViewModel() {
    private val _state = MutableSharedFlow<DetailScreenActions>()
    val state = _state.asSharedFlow()
    lateinit var noteFlow: Flow<Note>

    fun initFlow(id: Int) {
        noteFlow = noteRepository.noteFlow(id)
    }

    fun processAction(action: DetailScreenActions) {
        viewModelScope.launch {
            _state.emit(action)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            _state.emit(DetailScreenActions.Back)
        }
    }

}