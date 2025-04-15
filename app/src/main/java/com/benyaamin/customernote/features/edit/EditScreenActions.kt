package com.benyaamin.customernote.features.edit

import com.benyaamin.customernote.models.Note

sealed class EditScreenActions {
    object Back : EditScreenActions()
    data class Save(val note: Note): EditScreenActions()
}