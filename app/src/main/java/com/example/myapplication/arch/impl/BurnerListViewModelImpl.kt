package com.example.myapplication.arch.impl

import androidx.lifecycle.*
import com.example.myapplication.arch.BurnerListContract
import com.example.myapplication.business.arch.properties.nativeimpl.FlowObservableProperty
import com.example.myapplication.business.arch.properties.nativeimpl.read.FlowReadProperty
import com.example.myapplication.foundation.arch.properties.ObservableProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
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

    // Lazy data model so that we only fetch on first request.
    // We do fetching operations here like loading and such.
    private val dataModel by lazy {
        isLoading.postValue(true)
        val dataModel = interactor.dataModel
        isLoading.postValue(false)
        dataModel
    }

    // We can use mutable versions of the same readable LiveData object in the impl
    override val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    // Assumption here is that every observable live data field that comes from the data model
    // will be a flow.
    @FlowPreview
    override val burnerColor: LiveData<Int> = dataModel.flatMapConcat {
            it.color.flow
        }.asLiveData(
            viewModelContext
        )

    // Error handling example 1. This one emits false or true whenever an error is emitted depending
    // on the type of error.
    override val showColorChangeError: LiveData<Boolean> = interactor.onError.flow.map {
        it is BurnerListContract.Error.ColorChange
    }.asLiveData(viewModelContext)

    override fun onAttached() {
        // Error handling Example 2.
        interactor.onError.observe {  error ->
            when (error) {
                BurnerListContract.Error.InitialFetch -> {
                    // Do Something
                }
                is BurnerListContract.Error.ColorChange -> {
                    // Do Something Else
                }
            }
        }
    }

    override fun onColorChange(color: Int) {
        // Example of doing loading if an interaction requires some delay.
        isLoading.postValue(true)
        viewModelScope.launch(viewModelContext) {
            interactor.onColorChange(color)
            // Happens after things in interactor which can be async
            isLoading.postValue(false)
        }
    }
}
