package com.example.financialapplication.ui.items

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.example.financialapplication.R
import com.example.financialapplication.data.ResultFailure
import com.example.financialapplication.data.ResultSuccess
import com.example.financialapplication.ui.items.ItemsViewModel.FailedToUpdateEvent
import com.example.financialapplication.ui.items.models.UiItem
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemsViewModelTest : ViewModelTest() {

    private lateinit var viewModel: ItemsViewModel

    private var itemsPresenter: ItemsPresenter = mockk()

    @Before
    fun setup() {
        clearAllMocks()
        viewModel = ItemsViewModel(itemsPresenter)
    }

    @Test
    fun testLoadItemsSuccess() = runTest {
        // Given
        coEvery { itemsPresenter.getItemsFromCache() } returns ResultSuccess(ITEM_MOCK_LIST_CACHE)
        coEvery { itemsPresenter.getItemsFromNetwork() } returns ResultSuccess(ITEM_MOCK_LIST)
        coEvery { itemsPresenter.cacheItems(ITEM_MOCK_LIST) } just Runs

        //When, Then
        viewModel.observeStateAndEvents { stateObserver, _ ->
            viewModel.loadItems()
            stateObserver.assertObserved(
                Loading,
                ItemsLoaded(ITEM_MOCK_LIST_CACHE),
                Loading,
                ItemsLoaded(ITEM_MOCK_LIST)
            )
        }
        coVerify(exactly = 1) { itemsPresenter.getItemsFromCache() }
        coVerify(exactly = 1) { itemsPresenter.getItemsFromNetwork() }
        coVerify(exactly = 1) { itemsPresenter.cacheItems(ITEM_MOCK_LIST) }
    }

    @Test
    fun testLoadItemsNetworkCallFailed() = runTest {
        // Given
        coEvery { itemsPresenter.getItemsFromCache() } returns ResultSuccess(ITEM_MOCK_LIST_CACHE)
        coEvery { itemsPresenter.getItemsFromNetwork() } returns ResultFailure("")
        coEvery { itemsPresenter.cacheItems(ITEM_MOCK_LIST) } just Runs

        //When, Then
        viewModel.observeStateAndEvents { stateObserver, _, queuedOneShotEvent ->
            viewModel.loadItems()
            stateObserver.assertObserved(Loading, ItemsLoaded(ITEM_MOCK_LIST_CACHE), Loading)
            queuedOneShotEvent.assertObserved(FailedToUpdateEvent(R.string.failed_to_update_message))
        }
        coVerify(exactly = 1) { itemsPresenter.getItemsFromCache() }
        coVerify(exactly = 1) { itemsPresenter.getItemsFromNetwork() }
        coVerify(exactly = 0) { itemsPresenter.cacheItems(ITEM_MOCK_LIST) }
    }

    companion object {
        private val ITEMS_MOCK = UiItem("id", "summary", "category", 10, "currency")
        private val ITEM_MOCK_LIST_CACHE = listOf(ITEMS_MOCK)
        private val ITEM_MOCK_LIST = listOf(ITEMS_MOCK.copy())
    }
}