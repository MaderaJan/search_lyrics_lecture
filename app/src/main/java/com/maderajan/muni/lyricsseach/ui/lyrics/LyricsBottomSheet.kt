package com.maderajan.muni.lyricsseach.ui.lyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.maderajan.muni.lyricsseach.data.LyricsData
import com.maderajan.muni.lyricsseach.repository.LyricsRepository
import kotlinx.coroutines.flow.MutableStateFlow

class LyricsBottomSheet : BottomSheetDialogFragment() {

    // TODO 4.2 (S) Odkomentovat po přidání direction se safeArgs - todo 4.2
//    private val args: LyricsBottomSheetArgs by navArgs()

    private val lyricsRepository: LyricsRepository by lazy {
        LyricsRepository(context = requireContext())
    }

    private lateinit var lyricsDataFlow: MutableStateFlow<LyricsData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO 4.2 (S) Odkomentovat po přidání direction se safeArgs - todo 4.2
//        lyricsDataFlow = MutableStateFlow(args.lyrics)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val lyricsData = lyricsDataFlow.collectAsState().value

                // TODO 4.1 (S) Vytvoření action z SearchLyricsFragment do LyricsBottomSheet s předáním LyricsData jako SafeArgumentu
                // TODO 4.1 (S) argument name: lyrics type: Parcelablee – LyricsData
                // TODO 4.2 (S) Odkomentovat řádek 160 z SearchLyricsFragment
                // TODO 4.3 (S) Vytvoření layoutu pomocí compose viz prezentace (layout nemusí být pixel perfect nebo udělejte vlastní layout, ale funkce musí být samozřejmně zachována)
                // TODO 4.3 (S) val lyricsData ... obsahuje aktuální data získáná z předaného argumentu
                // TODO 4.4 (S) Při kliknutí na srdíčko se položka odstraní a nebo přidá do databáze
                // TODO 4.4 (S) Přidání nebo mazazání z databáze pomocí LyricsRepository.insertOrDeleteLyrics(Boolean)
            }
        }
}