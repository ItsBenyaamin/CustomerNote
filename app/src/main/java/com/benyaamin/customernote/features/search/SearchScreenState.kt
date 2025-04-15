package com.benyaamin.customernote.features.search

import com.benyaamin.customernote.models.Note

data class SearchScreenState(
    val list: List<Note> = emptyList<Note>()
)
