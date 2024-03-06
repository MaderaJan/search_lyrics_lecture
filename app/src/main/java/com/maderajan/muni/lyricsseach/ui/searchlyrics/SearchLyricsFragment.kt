package com.maderajan.muni.lyricsseach.ui.searchlyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.maderajan.muni.lyricsseach.R
import com.maderajan.muni.lyricsseach.ui.theme.yellow500

class SearchLyricsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // TODO 3 Compose layout explain

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    FormField(
                        icon = painterResource(id = R.drawable.ic_group),
                        text = stringResource(id = R.string.search_form_select_an_artist),
                        isEnabled = true,
                        isFilled = false,
                        onClick = {
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FormField(
                        icon = painterResource(id = R.drawable.ic_record),
                        text = stringResource(id = R.string.search_form_select_an_album),
                        isEnabled = true,
                        isFilled = false,
                        onClick = {
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FormField(
                        icon = painterResource(id = R.drawable.ic_song),
                        text = stringResource(id = R.string.search_form_select_an_song),
                        isEnabled = true,
                        isFilled = false,
                        onClick = {
                        }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = yellow500,
                            disabledContainerColor = Color.DarkGray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                        },
                        enabled = true
                    ) {
                        Text(
                            text = stringResource(id = R.string.search_form_get_lyrics),
                            color = Color.Black
                        )
                    }
                }
            }
        }
}

@Composable
fun FormField(
    icon: Painter,
    text: String,
    isEnabled: Boolean,
    isFilled: Boolean,
    onClick: () -> Unit
) {
    // TODO 4. Row Modifier Explain
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (isEnabled) {
                    onClick()
                }
            }
            .background(Color.Black, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .alpha(if (isEnabled) 1f else 0.5f)
    ) {
//        TODO 5. (S) FormField Layout -> Viz prezentace

//        TODO 5.1 (S) Icon
//        Icon()

//        TODO 5.2 (S) Text
//        Text()

        // TODO 4. Spacer Modifier.weight explain
        Spacer(modifier = Modifier.weight(1f))

        // TODO 5.3 (S) Check ikona
        // 1. Stav Checked -> Viz prezentace
        // 2. Stav Unchecked -> Viz prezentace
    }
}

@Preview
@Composable
fun FormFieldPreview() {
    Column {
        FormField(
            icon = painterResource(id = R.drawable.ic_group),
            text = "Nirvana",
            isEnabled = true,
            isFilled = true,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))


        FormField(
            icon = painterResource(id = R.drawable.ic_record),
            text = "Select an album",
            isEnabled = true,
            isFilled = false,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        FormField(
            icon = painterResource(id = R.drawable.ic_song),
            text = "Select a song",
            isEnabled = false,
            isFilled = false,
            onClick = {}
        )
    }
}