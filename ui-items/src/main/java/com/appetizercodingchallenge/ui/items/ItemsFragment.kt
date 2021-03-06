package com.appetizercodingchallenge.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import com.appetizercodingchallenge.api.UiError
import com.appetizercodingchallenge.api.UiLoading
import com.appetizercodingchallenge.common.FragmentWithBinding
import com.appetizercodingchallenge.common.layout.headline3
import com.appetizercodingchallenge.common.navigation.audioBookDetailsDeeplink
import com.appetizercodingchallenge.common.navigation.defaultNavAnimation
import com.appetizercodingchallenge.common.navigation.movieDetailsDeeplink
import com.appetizercodingchallenge.common.navigation.songDetailsDeeplink
import com.appetizercodingchallenge.common.navigation.tvShowDetailsDeeplink
import com.appetizercodingchallenge.common.paging.PagingEpoxyController
import com.appetizercodingchallenge.data.resultentities.ItemEntryWithDetails
import com.appetizercodingchallenge.data.types.ListItemType
import com.appetizercodingchallenge.ui.ProgressTimeLatch
import com.appetizercodingchallenge.ui.SpacingItemDecorator
import com.appetizercodingchallenge.ui.items.databinding.FragmentItemsBinding
import com.appetizercodingchallenge.util.autoCleared
import com.appetizercodingchallenge.util.getLastUserVisitedTime
import com.appetizercodingchallenge.util.getPref
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.qualifier
import java.text.SimpleDateFormat

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class ItemsFragment : FragmentWithBinding<FragmentItemsBinding>() {

    private val viewModel: ItemsViewModel by viewModel()
    private val dateFormatter: SimpleDateFormat by inject(
        qualifier = qualifier("lastUserVisitFormatter")
    )

    private var swipeRefreshLatch: ProgressTimeLatch by autoCleared()
    private var controller: PagingEpoxyController<
            ItemsViewState,
            ItemEntryWithDetails,
            ItemBindingModel_>? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentItemsBinding {
        return FragmentItemsBinding.inflate(inflater, container, false)
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
        binding?.rvItemLists?.clear()
        super.onDestroyView()
    }

    override fun onViewCreated(
        binding: FragmentItemsBinding,
        savedInstanceState: Bundle?
    ) {
//        binding!!.rvItemLists.applySystemWindowInsetsToMargin(top = true)
        swipeRefreshLatch = ProgressTimeLatch {
            binding.refresher.isRefreshing = it
        }

        binding.rvItemLists.apply {
            setController(controller!!)
            addItemDecoration(
                SpacingItemDecorator(
                    resources.getDimension(R.dimen.padding_8).toInt()
                )
            )
        }

        binding.refresher.setOnRefreshListener(viewModel::refresh)

        lifecycleScope.launchWhenStarted {
            viewModel.pagedList.collect {
                controller?.submitList(it)
            }
        }

        viewModel.liveData.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: ItemsViewState) {
        controller!!.state = state

        when (val status = state.status) {
            is UiError -> {
                swipeRefreshLatch.refreshing = false
                Snackbar.make(requireView(), status.message, Snackbar.LENGTH_SHORT).show()
            }
            is UiLoading -> swipeRefreshLatch.refreshing = status.fullRefresh
            else -> swipeRefreshLatch.refreshing = false
        }
    }

    private fun createController(): PagingEpoxyController<
            ItemsViewState, ItemEntryWithDetails, ItemBindingModel_> =
        object :
            PagingEpoxyController<ItemsViewState, ItemEntryWithDetails, ItemBindingModel_>(
                ItemsViewState(),
                true
            ) {

            override fun insertHeaderModels(modelCollector: ModelCollector) {
                with(modelCollector) {
                    headline3 {
                        id("headline")
                        val lastTimeVisited = requireContext().getPref().getLastUserVisitedTime()
                        val text = String.format(
                            getString(R.string.last_date_previously_visited),
                            if (lastTimeVisited != null) dateFormatter.format(
                                lastTimeVisited
                            ) else ""
                        )
                        text(text)
                        onBind { _, view, _ ->
                            val layoutParams =
                                view.dataBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams
                            layoutParams?.isFullSpan = true
                        }
                    }
                }
            }

            override fun buildItemModel(item: ItemEntryWithDetails): EpoxyModel<*> {
                return ItemBindingModel_()
                    .id(item.generateStableId())
                    .name(item.trackName())
                    .imageUrl(item.imageUrl())
                    .genre(item.genre())
                    .price(item.trackPrice())
                    .currency(item.currency())
                    .kind(item.kind())
                    .onClickListener(View.OnClickListener {
                        findNavController().navigate(
                            when (item.itemEntry.kind) {
                                ListItemType.TV_SHOW -> tvShowDetailsDeeplink(item.itemEntry.itemId)
                                ListItemType.SONG -> songDetailsDeeplink(item.itemEntry.itemId)
                                ListItemType.FEATURE_MOVIE -> movieDetailsDeeplink(item.itemEntry.itemId)
                                else -> audioBookDetailsDeeplink(item.itemEntry.itemId)
                            }, defaultNavAnimation()
                        )
                    })
            }

            override fun buildItemPlaceholder(index: Int): ItemBindingModel_ =
                ItemBindingModel_()
                    .id("item_placeholder_$index")
        }
}
