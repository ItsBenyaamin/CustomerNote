package com.benyaamin.customernote.features.search

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

class SearchViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    private val _actions = MutableSharedFlow<SearchScreenActions>()
    val actions = _actions.asSharedFlow()

    init {
        searchWith("")
    }

    fun searchWith(query: String) {
        viewModelScope.launch {
            val result = noteRepository.searchNote(query)
            _state.update { it.copy(list = result) }
        }
    }


    fun processAction(action: SearchScreenActions) {
        viewModelScope.launch {
            _actions.emit(action)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

}