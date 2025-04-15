package com.benyaamin.customernote.ui.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutes() {
    @Serializable
    object ListScreen : NavRoutes()

    @Serializable
    data class NoteDetail(val id: Int) : NavRoutes()

    @Serializable
    data object InsertNote : NavRoutes()

    @Serializable
    data class EditNote(val id: Int) : NavRoutes()

    @Serializable
    data object SearchScreen : NavRoutes()
}