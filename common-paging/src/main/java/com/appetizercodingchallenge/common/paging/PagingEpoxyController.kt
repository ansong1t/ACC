package com.appetizercodingchallenge.common.paging

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.appetizercodingchallenge.api.UiLoading
import com.appetizercodingchallenge.common.epoxy.TotalSpanOverride
import com.appetizercodingchallenge.common.layout.vertSpacerSmall
import com.appetizercodingchallenge.common.layout.infiniteLoading
import com.appetizercodingchallenge.extensions.observable

abstract class PagingEpoxyController<STATE : PagingViewState, LI, Placeholder : EpoxyModel<*>>(
    initialState: STATE,
    clearCacheOnStateChange: Boolean = false
) : PagedListEpoxyController<LI>() {

    var state: STATE by observable(initialState) {
        if (clearCacheOnStateChange) requestForcedModelBuild()
        else requestForcedModelBuild()
    }

    @Suppress("UselessCallOnCollection")
    override fun addModels(models: List<EpoxyModel<*>>) {

        insertHeaderModels(this)

        if (models.isNotEmpty()) {
            vertSpacerSmall {
                id("top_spacer")
                spanSizeOverride(TotalSpanOverride)
                onBind { _, view, _ ->
                    val layoutParams =
                        view.dataBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams
                    layoutParams?.isFullSpan = true
                }
            }

            super.addModels(models)

            vertSpacerSmall {
                id("bottom_spacer")
                spanSizeOverride(TotalSpanOverride)
                onBind { _, view, _ ->
                    val layoutParams =
                        view.dataBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams
                    layoutParams?.isFullSpan = true
                }
            }
        } else {
            onEmptyState(this)
        }

        val status = state.status
        if (status is UiLoading && !status.fullRefresh) {
            infiniteLoading {
                id("loading_view")
                spanSizeOverride(TotalSpanOverride)
                onBind { _, view, _ ->
                    val layoutParams =
                        view.dataBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams
                    layoutParams?.isFullSpan = true
                }
            }
        }
    }

    override fun buildItemModel(currentPosition: Int, item: LI?): EpoxyModel<*> {
        return if (item != null) buildItemModel(item) else buildItemPlaceholder(currentPosition)
    }

    protected abstract fun buildItemModel(item: LI): EpoxyModel<*>

    protected open fun onEmptyState(modelCollector: ModelCollector) {}

    protected open fun insertHeaderModels(modelCollector: ModelCollector) {}

    protected abstract fun buildItemPlaceholder(index: Int): Placeholder
}
