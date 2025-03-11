package pl.kwasow.sunshine.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import pl.kwasow.sunshine.R

private const val COLUMNS = 9

@Composable
fun FlamingoBackgroundLight(modifier: Modifier = Modifier) {
    FlamingoBackground(
        modifier = modifier,
        color = Color(0x60FD7295),
    )
}

@Composable
fun FlamingoBackground(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFFD7295),
) {
    val painter = painterResource(id = R.drawable.ic_flamingo)
    val description = stringResource(id = R.string.contentDescription_background_flamingo)

    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMNS),
        modifier = modifier.fillMaxSize(),
        userScrollEnabled = false,
    ) {
        // We want to to generate items as long as they don't fill the entire screen
        items(Int.MAX_VALUE) { i ->
            val row = i / COLUMNS
            val column = i % COLUMNS
            val direction = if (column % 2 == 0) -1 else 1

            if ((row + column) % 2 == 0) {
                Icon(
                    painter = painter,
                    tint = color,
                    contentDescription = description,
                    modifier = Modifier.rotate(25f * direction),
                )
            }

            Box(Modifier)
        }
    }
}
