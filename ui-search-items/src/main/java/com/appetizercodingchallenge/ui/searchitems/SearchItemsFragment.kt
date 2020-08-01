package com.appetizercodingchallenge.ui.searchitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.observe
import androidx.lifecycle.lifecycleScope
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector
import com.appetizercodingchallenge.api.UiError
import com.appetizercodingchallenge.common.FragmentWithBinding
import com.google.android.material.snackbar.Snackbar
import com.appetizercodingchallenge.common.paging.PagingEpoxyController
import com.appetizercodingchallenge.data.resultentities.SearchedItemEntryWithDetails
import com.appetizercodingchallenge.ui.SpacingItemDecorator
import com.appetizercodingchallenge.ui.searchitems.databinding.FragmentSearchItemsBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class SearchItemsFragment : FragmentWithBinding<FragmentSearchItemsBinding>() {
    private val viewModel: SearchItemsViewModel by viewModel()

    private var controller: PagingEpoxyController<
            SearchItemsViewState,
            SearchedItemEntryWithDetails,
            SearchItemBindingModel_>? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSearchItemsBinding {
        return FragmentSearchItemsBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = createController()
    }

    override fun onDestroy() {
        super.onDestroy()
        controller = null
    }

    override fun onDestroyView() {
        binding?.rvSearchItems?.clear()
        super.onDestroyView()
    }

    override fun onViewCreated(
        binding: FragmentSearchItemsBinding,
        savedInstanceState: Bundle?
    ) {
//        postponeEnterTransitionWithTimeout()

        binding.rvSearchItems.apply {
            // We set the item animator to null since it can interfere with the enter/shared element
            // transitions
            itemAnimator = null

            setController(controller!!)
            addItemDecoration(
                SpacingItemDecorator(
                    resources.getDimension(R.dimen.padding_4).toInt()
                )
            )
        }

        binding.etSearch.doAfterTextChanged {
            it?.let { viewModel.search(it.toString()) }
        }
        binding.etSearch.requestFocus()

        lifecycleScope.launchWhenStarted {
            viewModel.pagedList.collect {
                controller?.submitList(it)
            }
        }

        viewModel.liveData.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: SearchItemsViewState) {
        binding?.state = state
        controller!!.state = state

        when (val status = state.status) {
            is UiError -> {
                Snackbar.make(requireView(), status.message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun createController():
            PagingEpoxyController<SearchItemsViewState,
                    SearchedItemEntryWithDetails,
                    SearchItemBindingModel_> =
        object :
            PagingEpoxyController<SearchItemsViewState,
                    SearchedItemEntryWithDetails,
                    SearchItemBindingModel_>(SearchItemsViewState()) {
            override fun buildItemModel(item: SearchedItemEntryWithDetails): EpoxyModel<*> {
                return SearchItemBindingModel_()
                    .id(item.generateStableId())
                    .name(item.song.trackName)
                    .imageUrl(item.song.artworkUrl100)
                    .description(String.format("Artist: %s", item.song.artistName))
                    .onClickListener(View.OnClickListener {
//                        findNavController().navigate("com.acc://item/${item.item.id}".toUri())
                        requireContext().toast("Item clicked!")
                    })
            }

            override fun onEmptyState(modelCollector: ModelCollector) {
                // leave empty no empty state
            }

            override fun buildItemPlaceholder(index: Int): SearchItemBindingModel_ =
                SearchItemBindingModel_()
                    .id("item_placeholder_$index")
        }
}
