package com.appetizercodingchallenge.ui.songdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.appetizercodingchallenge.common.FragmentWithBinding
import com.appetizercodingchallenge.common.navigation.songDetailsDeeplink
import com.appetizercodingchallenge.ui.SpacingItemDecorator
import com.appetizercodingchallenge.ui.songdetails.databinding.FragmentSongDetailsBinding
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@FlowPreview
@ExperimentalCoroutinesApi
class SongDetailsFragment :
    FragmentWithBinding<FragmentSongDetailsBinding>() {

    private val args: SongDetailsFragmentArgs by navArgs()

    private val viewModel by viewModel<SongDetailsViewModel> {
        parametersOf(args.trackId)
    }

    private var controller: SongDetailsEpoxyController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = SongDetailsEpoxyController(requireContext())
    }

    override fun onViewCreated(
        binding: FragmentSongDetailsBinding,
        savedInstanceState: Bundle?
    ) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.ivHeader.applySystemWindowInsetsToPadding(top = true, consume = true)
        binding.parent.setEdgeToEdgeSystemUiFlags(true)
        binding.rvScrollable.apply {
            setController(controller!!.apply {
                callbacks = object : SongDetailsEpoxyController.Callbacks {
                    override fun onTrackClicked(trackId: Long) {
                        findNavController().navigate(songDetailsDeeplink(trackId))
                    }
                }
            })
            addItemDecoration(
                SpacingItemDecorator(
                    resources.getDimension(R.dimen.space_small).toInt()
                )
            )
        }

        viewModel.liveData.observe(viewLifecycleOwner, ::render)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSongDetailsBinding =
        FragmentSongDetailsBinding.inflate(inflater, container, false)

    override fun onDestroy() {
        binding?.rvScrollable?.clear()
        super.onDestroy()
        controller?.clear()
        controller = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        controller?.removeCallback()
    }

    private fun render(state: SongDetailsViewState) {
        val binding = requireBinding()
        binding.state = state
        controller?.state = state
    }
}
