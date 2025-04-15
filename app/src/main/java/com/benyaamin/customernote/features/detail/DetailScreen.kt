package com.benyaamin.customernote.features.detail

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.benyaamin.customernote.R
import com.benyaamin.customernote.data.fakeNote
import com.benyaamin.customernote.models.Note
import com.benyaamin.customernote.ui.component.CenteredClickableIcon
import com.benyaamin.customernote.ui.component.TopBar
import com.benyaamin.customernote.ui.theme.CallColor
import com.benyaamin.customernote.ui.theme.CustomerNoteTheme
import com.benyaamin.customernote.ui.theme.DeleteColor
import com.benyaamin.customernote.ui.theme.EditColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(note: Note, action: (DetailScreenActions) -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            title = {
                Row {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = note.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }


                    CenteredClickableIcon(
                        modifier = Modifier
                            .size(38.dp)
                            .padding(8.dp),
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = "tel:${note.phoneNumber}".toUri()
                            context.startActivity(intent)
                        },
                        imageVector = Icons.Default.Call,
                        tint = CallColor,
                    )
                }
            },
            navigationIcon = {
                CenteredClickableIcon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(4.dp),
                    onClick = { action(DetailScreenActions.Back) },
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                )
            },
            actions = {
                Row {
                    CenteredClickableIcon(
                        modifier = Modifier
                            .size(38.dp)
                            .padding(8.dp),
                        onClick = { action(DetailScreenActions.EditNote(note)) },
                        imageVector = Icons.Default.Edit,
                        tint = EditColor,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    CenteredClickableIcon(
                        modifier = Modifier
                            .size(38.dp)
                            .padding(8.dp),
                        onClick = { action(DetailScreenActions.DeleteNote(note)) },
                        imageVector = Icons.Default.Delete,
                        tint = DeleteColor,
                    )
                }
            }
        )

        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
            ) {
                Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp)) {
                    Box(
                        modifier = Modifier.height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row {
                            Text(
                                stringResource(R.string.phone_number),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = note.phoneNumber,
                                fontSize = 16.sp,
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row {
                            Text(
                                stringResource(R.string.date),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = note.date,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(4.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Text(note.description, fontSize = 16.sp)
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    CustomerNoteTheme {
        DetailScreen(fakeNote) { }
    }
}