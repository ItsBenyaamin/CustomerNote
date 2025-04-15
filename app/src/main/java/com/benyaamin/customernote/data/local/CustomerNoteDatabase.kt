package com.benyaamin.customernote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.benyaamin.customernote.data.local.dao.NoteDao
import com.benyaamin.customernote.data.local.entity.NoteEntity

@Database(
    version = 1,
    entities = [
        NoteEntity::class,
    ],
)
abstract class CustomerNoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}