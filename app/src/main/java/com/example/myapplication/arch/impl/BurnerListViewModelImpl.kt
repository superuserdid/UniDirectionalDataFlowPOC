package com.example.myapplication.arch.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.arch.BurnerListContract

// ViewModel manages view things as well as
// rendering view from data provided by data model
// Notice, the ViewModel cannot cast the data model to anything else to write.
// View Model MUST delegate to the interactor according to the Unidirectional Data Flow
class BurnerListViewModelImpl(
    private val interactor: BurnerListContract.Interactor
): ViewModel(), BurnerListContract.ViewModel {

    // We can use mutable versions of the same readable LiveData object in the impl
    override val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    override val burnerColor: MutableLiveData<Int> = MutableLiveData()

    override fun onAttached() {
        interactor.fetchDataModel {
            val color = it.color
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
