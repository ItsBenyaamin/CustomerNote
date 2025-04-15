package com.benyaamin.customernote.features.edit

import android.widget.Toast
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benyaamin.customernote.R
import com.benyaamin.customernote.data.fakeNote
import com.benyaamin.customernote.models.Note
import com.benyaamin.customernote.ui.component.CenteredClickableIcon
import com.benyaamin.customernote.ui.component.ClickableIcon
import com.benyaamin.customernote.ui.component.TopBar
import com.benyaamin.customernote.ui.theme.CustomerNoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(note: Note, action: (EditScreenActions) -> Unit) {
    val name = remember { mutableStateOf(note.name) }
    val phoneNumber = remember { mutableStateOf(note.phoneNumber) }
    val date = remember { mutableStateOf(note.date) }
    val description = remember { mutableStateOf(note.description) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TopBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.edit_note),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
            },
            navigationIcon = {
                CenteredClickableIcon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(4.dp),
                    onClick = { action(EditScreenActions.Back) },
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                )
            },
            actions = {
                Box(
                    modifier = Modifier.fillMaxHeight().width(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ClickableIcon(
                        modifier = Modifier
                            .size(38.dp)
                            .padding(6.dp),
                        onClick = {
                            focusManager.clearFocus(true)
                            if (
                                name.value.isNotEmpty() &&
                                phoneNumber.value.isNotEmpty()
                            ) {
                                action(
                                    EditScreenActions.Save(
                                        note.copy(
                                            name = name.value,
                                            phoneNumber = phoneNumber.value,
                                            date = date.value,
                                            description = description.value
                                        )
                                    )
                                )
                            }else {
                                Toast.makeText(context, R.string.name_and_phone_number_empty,
                                    Toast.LENGTH_SHORT).show()
                            }
                        },
                        painter = painterResource(R.drawable.baseline_save_24),
                    )
                }
            }
        )

        Spacer(modifier = Modifier.size(6.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(.9f),
            value = name.value,
            onValueChange = { name.value = it },
            singleLine = true,
            placeholder = { Text(stringResource(R.string.enter_name), color = Color.Gray) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
        )

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(.9f),
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            singleLine = true,
            placeholder = { Text(stringResource(R.string.enter_phone_number), color = Color.Gray) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
        )

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(.9f),
            value = date.value,
            onValueChange = { date.value = it },
            singleLine = true,
            placeholder = { Text(stringResource(R.string.enter_date), color = Color.Gray) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
        )

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .heightIn(min = 300.dp),
            value = description.value,
            onValueChange = { description.value = it },
            singleLine = false,
            placeholder = { Text(stringResource(R.string.enter_description), color = Color.Gray) },
        )

    }
}

@Preview
@Composable
fun PreviewInsertScreen() {
    CustomerNoteTheme {
        EditScreen(fakeNote) {

        }
    }
}