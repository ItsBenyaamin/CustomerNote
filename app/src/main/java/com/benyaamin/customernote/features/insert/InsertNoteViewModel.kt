package com.benyaamin.customernote.features.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benyaamin.customernote.data.repository.NoteRepository
import com.benyaamin.customernote.models.Note
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class InsertNoteViewModel(
    private val noteRepository: NoteRepository,
) : ViewModel() {
    private val _state = MutableSharedFlow<InsertScreenActions>()
    val state = _state.asSharedFlow()

    fun processAction(action: InsertScreenActions) {
        viewModelScope.launch {
            _state.emit(action)
        }
    }


    fun save(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
            _state.emit(InsertScreenActions.Back)
        }
    }

}