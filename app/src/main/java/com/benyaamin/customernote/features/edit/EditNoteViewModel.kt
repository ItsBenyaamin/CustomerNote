package com.benyaamin.customernote.features.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benyaamin.customernote.data.repository.NoteRepository
import com.benyaamin.customernote.models.Note
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val noteRepository: NoteRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(EditNoteState())
    val state = _state.asStateFlow()

    private val _actions = MutableSharedFlow<EditScreenActions>()
    val actions = _actions.asSharedFlow()

    fun loatNote(id: Int) {
        viewModelScope.launch {
            val note = noteRepository.getNote(id)
            _state.update { it.copy(note = note) }
        }
    }

    fun processAction(action: EditScreenActions) {
        viewModelScope.launch {
            _actions.emit(action)
        }
    }

    fun save(note: Note) {
        viewModelScope.launch {
            noteRepository.editNote(note)
            _actions.emit(EditScreenActions.Back)
        }
    }

}