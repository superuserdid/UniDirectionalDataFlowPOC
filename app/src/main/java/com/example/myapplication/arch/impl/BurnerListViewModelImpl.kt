package com.example.myapplication.arch.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.arch.BurnerListContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

// ViewModel manages view things as well as
// rendering view from data provided by data model
// Notice, the ViewModel cannot cast the data model to anything else to write.
// View Model MUST delegate to the interactor according to the Unidirectional Data Flow
class BurnerListViewModelImpl(
    private val interactor: BurnerListContract.Interactor,
    private val viewModelContext: CoroutineContext
): ViewModel(), BurnerListContract.ViewModel {

    // We can use mutable versions of the same readable LiveData object in the impl
    override val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    override val burnerColor: MutableLiveData<Int> = MutableLiveData()

    override fun onAttached() {
        viewModelScope.launch(viewModelContext) {
            val dataModel = interactor.fetchDataModel()
            val color = dataModel.color
            color.observe { colorValue ->
                burnerColor.postValue(colorValue)
            }
            isLoading.postValue(false)
        }
    }

    override fun onColorChange(color: Int) {
        isLoading.postValue(true)
        interactor.onColorChange(color)
    }
}
