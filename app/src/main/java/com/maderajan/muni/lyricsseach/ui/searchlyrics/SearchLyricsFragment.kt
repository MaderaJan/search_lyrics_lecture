package com.maderajan.muni.lyricsseach.ui.searchlyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
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
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.maderajan.muni.lyricsseach.R
import com.maderajan.muni.lyricsseach.data.LyricsData
import com.maderajan.muni.lyricsseach.data.SearchResult
import com.maderajan.muni.lyricsseach.data.SearchType
import com.maderajan.muni.lyricsseach.repository.SearchRepository
import com.maderajan.muni.lyricsseach.ui.search.SearchBottomSheet
import com.maderajan.muni.lyricsseach.ui.theme.black300
import com.maderajan.muni.lyricsseach.ui.theme.yellow500
import kotlinx.coroutines.flow.MutableStateFlow

class SearchLyricsFragment : Fragment() {

    private val searchRepository: SearchRepository by lazy {
        SearchRepository()
    }

    private val artistFlow = MutableStateFlow<SearchResult?>(null)
    private val albumFlow = MutableStateFlow<SearchResult?>(null)
    private val songFlow = MutableStateFlow<SearchResult?>(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    val artist = artistFlow.collectAsState().value
                    val album = albumFlow.collectAsState().value
                    val song = songFlow.collectAsState().value

                    FormField(
                        icon = painterResource(id = R.drawable.ic_group),
                        text = artist?.name ?: stringResource(id = R.string.search_form_select_an_artist),
                        isEnabled = true,
                        isFilled = artist != null,
                        onClick = {
                            navigateToSearch(SearchType.ARTIST)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FormField(
                        icon = painterResource(id = R.drawable.ic_record),
                        text = album?.name ?: stringResource(id = R.string.search_form_select_an_album),
                        isEnabled = artist != null,
                        isFilled = album != null,
                        onClick = {
                            navigateToSearch(SearchType.ALBUM, query = artist?.id)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FormField(
                        icon = painterResource(id = R.drawable.ic_song),
                        text = song?.name ?: stringResource(id = R.string.search_form_select_an_song),
                        isEnabled = artist != null && album != null,
                        isFilled = song != null,
                        onClick = {
                            navigateToSearch(SearchType.SONGS, query = album?.id)
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
                            searchSongLyrics(artist, song, album)
                        },
                        enabled = artist != null && album != null && song != null
                    ) {
                        Text(
                            text = stringResource(id = R.string.search_form_get_lyrics),
                            color = Color.Black
                        )
                    }
                }
            }
        }


    private fun navigateToSearch(searchType: SearchType, query: String? = null) {
        findNavController()
            .navigate(
                SearchLyricsFragmentDirections
                    .actionSearchLyricsFragmentToSearchBottomSheet(searchType = searchType, query = query)
            )
    }

    private fun searchSongLyrics(
        artist: SearchResult?,
        song: SearchResult?,
        album: SearchResult?
    ) {
        searchRepository.searchLyrics(
            artistName = artist?.name.orEmpty(),
            songName = song?.name.orEmpty(),
            success = { lyrics ->
                if (song != null && artist != null && lyrics != null) {
                    val lyricsData = LyricsData(
                        id = song.id,
                        songName = song.name,
                        artistName = artist.name,
                        coverUrl = album?.imageUrl,
                        lyrics = lyrics,
                        isFavorite = false
                    )

                    findNavController()
                        .navigate(SearchLyricsFragmentDirections.actionSearchBottomSheetToLyricsBottomSheet(lyrics = lyricsData))
                }
            },
            fail = {
                Toast.makeText(requireContext(), getString(R.string.nothing_was_found), Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(SearchType.ARTIST.name) { _, bundle ->
            artistFlow.value = bundle.getParcelable(SearchBottomSheet.SEARCH_KEY)
        }

        setFragmentResultListener(SearchType.ALBUM.name) { _, bundle ->
            albumFlow.value = bundle.getParcelable(SearchBottomSheet.SEARCH_KEY)
        }

        setFragmentResultListener(SearchType.SONGS.name) { _, bundle ->
            songFlow.value = bundle.getParcelable(SearchBottomSheet.SEARCH_KEY)
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
}