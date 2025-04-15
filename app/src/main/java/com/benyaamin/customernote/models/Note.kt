package com.benyaamin.customernote.models

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val date: String,
    val description: String,
)