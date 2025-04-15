package com.benyaamin.customernote.features.insert

import com.benyaamin.customernote.models.Note

sealed class InsertScreenActions {
    object Back : InsertScreenActions()
    data class Save(val note: Note): InsertScreenActions()
}