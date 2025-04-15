package com.benyaamin.customernote.features.list

import com.benyaamin.customernote.models.Note

sealed class ListScreenActions {
    object NewNote : ListScreenActions()
    data class OpenNote(val note: Note) : ListScreenActions()
    data class EditNote(val note: Note) : ListScreenActions()
    data class DeleteNote(val note: Note) : ListScreenActions()
    data object Search : ListScreenActions()
}