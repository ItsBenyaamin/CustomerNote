package com.benyaamin.customernote.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CenteredClickableIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector,
    tint: Color = LocalContentColor.current,
) {
    Box(
        modifier = Modifier.fillMaxHeight().width(40.dp),
        contentAlignment = Alignment.Center
    ) {
        ClickableIcon(
            modifier = modifier,
            onClick = onClick,
            imageVector = imageVector,
            tint = tint
        )
    }
}

@Composable
fun ClickableIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        modifier = modifier
            .clickable(
                interactionSource =
                    remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        imageVector = imageVector,
        tint = tint,
        contentDescription = null,
    )
}

@Composable
fun CenteredClickableIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter,
    tint: Color = LocalContentColor.current,
) {
    Box(
        modifier = Modifier.fillMaxHeight().width(40.dp),
        contentAlignment = Alignment.Center
    ) {
        ClickableIcon(
            modifier = modifier,
            onClick = onClick,
            painter = painter,
            tint = tint
        )
    }
}

@Composable
fun ClickableIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        modifier = modifier
            .clickable(
                interactionSource =
                    remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        painter = painter,
        tint = tint,
        contentDescription = null,
    )
}