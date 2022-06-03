package com.example.financialapplication.ui.items

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.bumptech.glide.Glide
import com.example.financialapplication.R
import com.example.financialapplication.ui.extensions.showToast
import com.example.financialapplication.ui.items.models.UiItem
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.view_details_dialog.*

class ItemsFragment : RainbowCakeFragment<ItemsViewState, ItemsViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_items

    private lateinit var itemsRecyclerViewAdapter: ItemsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setupRecyclerView()

        viewModel.loadItems()
    }

    private fun setListeners() {
        itemsRefreshLayout.setOnRefreshListener {
            viewModel.updateItems()
        }
        itemsSearchBar.apply {
            onActionViewExpanded()
            clearFocus()
            queryHint = getString(R.string.hint_search_by_title)
            setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.searchItemsByTitle(query.toString())
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchItemsByTitle(newText.toString())
                    return true
                }
            })
        }
    }

    private fun setupRecyclerView() {
        itemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(requireContext(), ::showDetailsDialog)
        itemsList.adapter = itemsRecyclerViewAdapter
    }

    private fun showDetailsDialog(item: UiItem) {
        MaterialDialog(requireContext(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            customView(R.layout.view_details_dialog, scrollable = true, horizontalPadding = true)
            detailedItemSummaryText.text = item.summary
            Glide
                .with(this@ItemsFragment.requireContext())
                .load(item.iconDrawable)
                .onlyRetrieveFromCache(true)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(detailedItemImage)
        }
    }

    override fun render(viewState: ItemsViewState) {
        when (viewState) {
            is Loading -> showLoading()
            is Error -> showError(viewState.errorMessage)
            is ItemsLoaded -> showItems(viewState.items)
        }
    }

    override fun onEvent(event: OneShotEvent) {
        when (event) {
            is ItemsViewModel.FailedToUpdateEvent -> showToast(requireContext().getString(event.messageRes))
        }
    }

    private fun showLoading() {
        if (itemsRefreshLayout.isRefreshing.not()) itemsProgressBar.isVisible = true
    }

    private fun showError(message: String?) {
        itemsProgressBar.isVisible = false
        itemsRefreshLayout.isRefreshing = false
        showToast(message)
    }

    private fun showItems(items: List<UiItem>) {
        itemsProgressBar.isVisible = false
        itemsRefreshLayout.isRefreshing = false
        itemsRecyclerViewAdapter.submitList(items)
    }
}