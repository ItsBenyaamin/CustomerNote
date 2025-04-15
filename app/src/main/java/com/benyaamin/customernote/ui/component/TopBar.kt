package com.benyaamin.customernote.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.benyaamin.customernote.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: @Composable () -> Unit,
    actions: (@Composable RowScope.() -> Unit)? = null,
    navigationIcon: (@Composable () -> Unit)? = null,
) {
    TopAppBar(
        modifier = Modifier
            .statusBarsPadding()
            .height(60.dp)
            .shadow(elevation = 2.dp),
//        colors = TopAppBarDefaults.topAppBarColors(
//            navigationIconContentColor = Color.Black,
//            titleContentColor = Color.Black,
//            containerColor = Color.White
//        ),
        title = title,
        actions = actions ?: {},
        navigationIcon = navigationIcon ?: {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(
    onValueChange: (String) -> Unit,
) {
    var value by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    SearchBar(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(60.dp)
            .shadow(elevation = 2.dp),
        inputField = {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .focusRequester(focusRequester),
                    value = value,
                    onValueChange = { newText ->
                        value = newText
                        onValueChange(newText)
                    },
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = Color.White,
//                        unfocusedContainerColor = Color.White,
//                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_placeholder),
                            color = Color.Gray,
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusManager.clearFocus(true)
                            onValueChange(value)
                        }
                    ),
                )
        },
        expanded = false,
        onExpandedChange = {}
    ) { }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}