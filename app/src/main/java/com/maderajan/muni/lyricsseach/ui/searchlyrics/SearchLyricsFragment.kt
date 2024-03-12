package com.maderajan.muni.lyricsseach.ui.searchlyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.maderajan.muni.lyricsseach.ui.theme.black300
import com.maderajan.muni.lyricsseach.ui.theme.yellow500

class SearchLyricsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
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
        Icon(
            painter = icon,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp)
                .background(color = if (isFilled) yellow500 else Color.Transparent, shape = CircleShape)
                .border(
                    width = 1.dp, color = black300, shape = CircleShape
                )
                .padding(4.dp)
        ) {
            if (isEnabled && isFilled) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = ""
                )
            }
        }
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