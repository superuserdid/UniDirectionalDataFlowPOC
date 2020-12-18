package com.example.myapplication.arch

import androidx.lifecycle.LiveData
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.business.arch.properties.nativeimpl.FlowObservableReadProperty
import kotlinx.coroutines.flow.Flow

interface BurnerListContract {

    interface ViewModel {

        fun onAttached()

        val showColorChangeError: LiveData<Boolean>

        val isLoading: LiveData<Boolean>

        val burnerColor: LiveData<Int>

        fun onColorChange(color: Int)
    }

    interface DataModel {

        val color: FlowObservableReadProperty<Int>
    }

    interface Repository {

        // Anything that is a coroutine can be a flow. Flows are complex
        // I think we should use discretion when defining types as flow or just suspended functions
        suspend fun fetch(): ResponseModel
    }

    interface Interactor {

        suspend fun onColorChange(value: Int)

        val dataModel: Flow<DataModel>

        val onError: FlowObservableReadProperty<Error>
    }

    sealed class Error {
        object InitialFetch: Error()
        data class ColorChange(val exception: Exception): Error()
    }
}
