package com.benyaamin.customernote.features.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.benyaamin.customernote.R
import com.benyaamin.customernote.models.Note
import com.benyaamin.customernote.ui.component.CenteredClickableIcon
import com.benyaamin.customernote.ui.component.NoteItem
import com.benyaamin.customernote.ui.component.TopBar

@Composable
fun ListScreen(list: LazyPagingItems<Note>, actionCallback: (ListScreenActions) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Column {
            TopBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.app_name),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }
                },
                actions = {
                    CenteredClickableIcon(
                        modifier = Modifier
                            .size(38.dp)
                            .padding(6.dp),
                        onClick = { actionCallback(ListScreenActions.Search) },
                        imageVector = Icons.Default.Search,
                    )
                }
            )

            when (list.loadState.refresh) {
                LoadState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is LoadState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Error loading notes")
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(list.itemCount, { index -> list[index]?.id ?: 0 }) { index ->
                            list[index]?.let {
                                NoteItem(
                                    it,
                                    onOpen = {
                                        actionCallback(ListScreenActions.OpenNote(it))
                                    },
                                    onEdit = {
                                        actionCallback(ListScreenActions.EditNote(it))
                                    },
                                    onDelete = {
                                        actionCallback(ListScreenActions.DeleteNote(it))
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            content = {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(6.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )
            },
            onClick = {
                actionCallback(ListScreenActions.NewNote)
            }
        )
    }
}