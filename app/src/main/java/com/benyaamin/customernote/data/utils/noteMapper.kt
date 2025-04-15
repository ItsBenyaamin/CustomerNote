package com.benyaamin.customernote.data.utils

import com.benyaamin.customernote.data.local.entity.NoteEntity
import com.benyaamin.customernote.models.Note

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        date = date,
        description = description
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        date = date,
        description = description
    )
}