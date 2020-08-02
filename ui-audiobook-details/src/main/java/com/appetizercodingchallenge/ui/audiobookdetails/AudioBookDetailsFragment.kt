package com.appetizercodingchallenge.ui.audiobookdetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.appetizercodingchallenge.common.FragmentWithBinding
import com.appetizercodingchallenge.ui.SpacingItemDecorator
import com.appetizercodingchallenge.ui.audiobookdetails.databinding.FragmentAudioBookDetailsBinding
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@FlowPreview
@ExperimentalCoroutinesApi
class AudioBookDetailsFragment :
    FragmentWithBinding<FragmentAudioBookDetailsBinding>() {

    private val args: AudioBookDetailsFragmentArgs by navArgs()

    private val viewModel by viewModel<AudioBookDetailsViewModel> {
        parametersOf(args.collectionId)
    }

    private var controller: AudioBookDetailsEpoxyController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = AudioBookDetailsEpoxyController(requireContext())
    }

    override fun onViewCreated(
        binding: FragmentAudioBookDetailsBinding,
        savedInstanceState: Bundle?
    ) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.ivHeader.applySystemWindowInsetsToPadding(top = true, consume = true)
        binding.parent.setEdgeToEdgeSystemUiFlags(true)
        binding.rvScrollable.apply {
            setController(controller!!.apply {
                callbacks = object : AudioBookDetailsEpoxyController.Callbacks {
                    override fun onPreviewUrl(url: String) {
                        startActivity(Intent(Intent.ACTION_VIEW).apply {
                            data = url.toUri()
                        })
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
    ): FragmentAudioBookDetailsBinding =
        FragmentAudioBookDetailsBinding.inflate(inflater, container, false)

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

    private fun render(state: AudioBookDetailsViewState) {
        val binding = requireBinding()
        binding.state = state
        controller?.state = state
    }
}
