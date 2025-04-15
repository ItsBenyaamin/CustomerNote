package com.benyaamin.customernote.di

import androidx.room.Room
import com.benyaamin.customernote.data.local.CustomerNoteDatabase
import com.benyaamin.customernote.data.repository.NoteRepository
import com.benyaamin.customernote.data.repository.NoteRepositoryImpl
import com.benyaamin.customernote.features.detail.DetailViewModel
import com.benyaamin.customernote.features.edit.EditNoteViewModel
import com.benyaamin.customernote.features.insert.InsertNoteViewModel
import com.benyaamin.customernote.features.list.ListViewModel
import com.benyaamin.customernote.features.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CustomerNoteDatabase> {
        Room.databaseBuilder(
            androidContext(),
            CustomerNoteDatabase::class.java,
            "customerNoteDB"
        ).build()
    }

    single<NoteRepository> {
        NoteRepositoryImpl(get())
    }

    viewModel {
        ListViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }
    viewModel {
        InsertNoteViewModel(get())
    }
    viewModel {
        EditNoteViewModel(get())
    }
    viewModel {
        SearchViewModel(get())
    }

}