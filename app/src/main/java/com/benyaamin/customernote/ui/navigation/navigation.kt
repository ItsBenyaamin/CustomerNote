package com.benyaamin.customernote.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.benyaamin.customernote.features.detail.DetailScreen
import com.benyaamin.customernote.features.detail.DetailScreenActions
import com.benyaamin.customernote.features.detail.DetailViewModel
import com.benyaamin.customernote.features.edit.EditNoteViewModel
import com.benyaamin.customernote.features.edit.EditScreen
import com.benyaamin.customernote.features.edit.EditScreenActions
import com.benyaamin.customernote.features.insert.InsertNoteViewModel
import com.benyaamin.customernote.features.insert.InsertScreen
import com.benyaamin.customernote.features.insert.InsertScreenActions
import com.benyaamin.customernote.features.list.ListScreen
import com.benyaamin.customernote.features.list.ListScreenActions
import com.benyaamin.customernote.features.list.ListViewModel
import com.benyaamin.customernote.features.search.SearchScreen
import com.benyaamin.customernote.features.search.SearchScreenActions
import com.benyaamin.customernote.features.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Composable
fun AppContent(navController: NavHostController, activity: ComponentActivity) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.ListScreen
    ) {
        composable<NavRoutes.ListScreen> {
            val viewModel = activity.getViewModel<ListViewModel>()
            val list = viewModel.pager.collectAsLazyPagingItems()

            LaunchedEffect(Unit) {
                viewModel.state.collect { action ->
                    when (action) {
                        ListScreenActions.NewNote -> navController.navigate(
                            NavRoutes.InsertNote
                        )

                        is ListScreenActions.OpenNote -> navController.navigate(
                            NavRoutes.NoteDetail(action.note.id)
                        )

                        is ListScreenActions.EditNote -> navController.navigate(
                            NavRoutes.EditNote(action.note.id)
                        )

                        is ListScreenActions.DeleteNote -> viewModel.deleteNote(
                            action.note
                        )

                        ListScreenActions.Search -> navController.navigate(
                            NavRoutes.SearchScreen
                        )
                    }
                }
            }

            ListScreen(list) { action ->
                viewModel.processAction(action)
            }
        }

        composable<NavRoutes.NoteDetail> {
            val args = it.toRoute<NavRoutes.NoteDetail>()
            val viewModel = activity.getViewModel<DetailViewModel>()

            viewModel.initFlow(args.id)

            LaunchedEffect(Unit) {
                viewModel.state.collect { action ->
                    when (action) {
                        is DetailScreenActions.DeleteNote -> viewModel.deleteNote(
                            action.note
                        )

                        is DetailScreenActions.EditNote -> navController.navigate(
                            NavRoutes.EditNote(action.note.id)
                        )

                        DetailScreenActions.Back -> navController.popBackStack()
                    }
                }
            }

            val flowState = viewModel.noteFlow.collectAsState(null)
            flowState.value?.let {
                DetailScreen(it) { action ->
                    viewModel.processAction(action)
                }
            }
        }

        composable<NavRoutes.InsertNote> {
            val viewModel = activity.getViewModel<InsertNoteViewModel>()

            LaunchedEffect(Unit) {
                viewModel.state.collect { action ->
                    when (action) {
                        InsertScreenActions.Back -> navController.popBackStack()
                        is InsertScreenActions.Save -> viewModel.save(action.note)
                    }
                }
            }

            InsertScreen { action ->
                viewModel.processAction(action)
            }
        }

        composable<NavRoutes.EditNote> {
            val args = it.toRoute<NavRoutes.EditNote>()
            val viewModel = activity.getViewModel<EditNoteViewModel>()
            viewModel.loatNote(args.id)

            LaunchedEffect(Unit) {
                viewModel.actions.collect { action ->
                    when (action) {
                        is EditScreenActions.Back -> navController.popBackStack()
                        is EditScreenActions.Save -> viewModel.save(action.note)
                    }
                }
            }

            val state = viewModel.state.collectAsStateWithLifecycle()
            state.value.note?.let {
                EditScreen(it) { action ->
                    viewModel.processAction(action)
                }
            }
        }

        composable<NavRoutes.SearchScreen> {
            val viewModel = activity.getViewModel<SearchViewModel>()

            LaunchedEffect(Unit) {
                viewModel.actions.collect { action ->
                    when (action) {
                        is SearchScreenActions.DeleteNote -> viewModel.deleteNote(
                            action.note
                        )

                        is SearchScreenActions.EditNote -> navController.navigate(
                            NavRoutes.EditNote(action.note.id)
                        )

                        is SearchScreenActions.OpenNote -> navController.navigate(
                            NavRoutes.NoteDetail(action.note.id)
                        )

                        is SearchScreenActions.Search -> viewModel.searchWith(action.query)
                    }
                }
            }

            val state = viewModel.state.collectAsStateWithLifecycle()

            SearchScreen(state.value.list) { action ->
                viewModel.processAction(action)
            }
        }
    }
}