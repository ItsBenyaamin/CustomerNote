package com.benyaamin.customernote.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benyaamin.customernote.R
import com.benyaamin.customernote.data.fakeNote
import com.benyaamin.customernote.models.Note
import com.benyaamin.customernote.ui.theme.DeleteColor
import com.benyaamin.customernote.ui.theme.EditColor

@Composable
fun NoteItem(
    note: Note,
    onOpen: (Note) -> Unit,
    onEdit: (Note) -> Unit,
    onDelete: (Note) -> Unit,
) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(size = 4.dp),
        onClick = {
            onOpen(note)
        }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Box(
                modifier = Modifier.height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Text(
                        stringResource(R.string.name),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = note.name,
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
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray))
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                ClickableIcon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(6.dp),
                    onClick = { onEdit(note) },
                    imageVector = Icons.Default.Edit,
                    tint = EditColor,
                )

                Spacer(modifier = Modifier.width(8.dp))

                ClickableIcon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(6.dp),
                    onClick = { onDelete(note) },
                    imageVector = Icons.Default.Delete,
                    tint = DeleteColor,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewNoteItem() {
    NoteItem(fakeNote, {}, {}, {})
}