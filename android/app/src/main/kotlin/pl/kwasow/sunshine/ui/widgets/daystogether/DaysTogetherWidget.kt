package pl.kwasow.sunshine.ui.widgets.daystogether

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import pl.kwasow.sunshine.R

// ====== Public composables
@Composable
fun DaysTogetherWidget(modifier: Modifier = Modifier) {
    val daysTogetherViewModel = koinViewModel<DaysTogetherViewModel>()
    val daysTogether = stringResource(id = R.string.widget_daystogether_day_together).uppercase()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style =
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        ),
                ) {
                    append("${daysTogetherViewModel.getDaysTogether()}. ")
                }
                withStyle(
                    style = SpanStyle(),
                ) {
                    append(daysTogether)
                }
            },
        )
    }
}

// ====== Previews
@Composable
@Preview
private fun DaysTogetherWidgetPreview() {
    DaysTogetherWidget()
}
