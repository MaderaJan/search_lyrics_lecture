package com.maderajan.muni.lyricsseach.ui.lyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.maderajan.muni.lyricsseach.R
import com.maderajan.muni.lyricsseach.data.LyricsData
import com.maderajan.muni.lyricsseach.repository.LyricsRepository
import com.maderajan.muni.lyricsseach.ui.theme.black300
import com.maderajan.muni.lyricsseach.ui.theme.yellow500
import kotlinx.coroutines.flow.MutableStateFlow

class LyricsBottomSheet : BottomSheetDialogFragment() {

    private val args: LyricsBottomSheetArgs by navArgs()

    private val lyricsRepository: LyricsRepository by lazy {
        LyricsRepository(context = requireContext())
    }

    private lateinit var lyricsDataFlow: MutableStateFlow<LyricsData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lyricsDataFlow = MutableStateFlow(args.lyrics)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val lyricsData = lyricsDataFlow.collectAsState().value
                val scrollState = rememberScrollState()

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(color = black300, shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                        .padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .height(8.dp)
                            .width(64.dp)
                            .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
                    )

                    TopBar(
                        lyricsData = lyricsData,
                        onArrowBackClick = {
                            this@LyricsBottomSheet.findNavController().navigateUp()
                        },
                        onFavoriteClick = {
                            val updatedIsFavorite = lyricsRepository.insertOrDeleteLyrics(lyricsData)
                            lyricsDataFlow.value = lyricsData.copy(isFavorite = updatedIsFavorite)
                        }
                    )

                    Text(
                        text = args.lyrics.lyrics,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(scrollState)
                            .padding(horizontal = 16.dp,)
                    )
                }
            }
        }

    @Composable
    private fun TopBar(
        lyricsData: LyricsData,
        onArrowBackClick: () -> Unit,
        onFavoriteClick: () -> Unit,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        ) {
            IconButton(onClick = onArrowBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Text(
                text = "${lyricsData.artistName} - ${lyricsData.songName}",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onFavoriteClick) {
                Icon(
                    painter = painterResource(
                        id = if (lyricsData.isFavorite) {
                            R.drawable.ic_favorite
                        } else {
                            R.drawable.ic_favorite_border
                        }
                    ),
                    tint = yellow500,
                    contentDescription = null
                )
            }
        }
    }
}