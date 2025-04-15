package com.benyaamin.customernote.features.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benyaamin.customernote.models.Note
import com.benyaamin.customernote.ui.component.NoteItem
import com.benyaamin.customernote.ui.component.TopSearchBar

sealed class SearchScreenActions {
    data class Search(val query: String) : SearchScreenActions()
    data class OpenNote(val note: Note) : SearchScreenActions()
    data class EditNote(val note: Note) : SearchScreenActions()
    data class DeleteNote(val note: Note) : SearchScreenActions()
}

@Composable
fun SearchScreen(list: List<Note>, actionCallback: (SearchScreenActions) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Column {
            TopSearchBar {
                actionCallback(SearchScreenActions.Search(it))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(list.size, { index -> list[index].id }) { index ->
                    val item = list[index]
                    NoteItem(
                        item,
                        onOpen = {
                            actionCallback(SearchScreenActions.OpenNote(it))
                        },
                        onEdit = {
                            actionCallback(SearchScreenActions.EditNote(it))
                        },
                        onDelete = {
                            actionCallback(SearchScreenActions.DeleteNote(it))
                        }
                    )
                }
            }
        }
    }
}