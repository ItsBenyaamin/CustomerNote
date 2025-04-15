package com.benyaamin.customernote.features.detail

import com.benyaamin.customernote.models.Note

sealed class DetailScreenActions {
    data object Back : DetailScreenActions()
    data class EditNote(val note: Note) : DetailScreenActions()
    data class DeleteNote(val note: Note) : DetailScreenActions()
}