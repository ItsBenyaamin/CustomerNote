package com.benyaamin.customernote.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.benyaamin.customernote.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("select * from noteentity order by id desc")
    fun getNotesPagingSource(): PagingSource<Int, NoteEntity>

    @Query("select * from noteentity where name like '%' || :query || '%' or phoneNumber like '%' || :query || '%' order by id desc")
    suspend fun queryNotes(query: String): List<NoteEntity>

    @Query("select * from noteentity where id = :id")
    fun getNoteFlow(id: Int): Flow<NoteEntity?>

    @Query("select * from noteentity where id = :id")
    suspend fun getNote(id: Int): NoteEntity

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("delete from noteentity where id = :id")
    suspend fun deleteNote(id: Int)
}