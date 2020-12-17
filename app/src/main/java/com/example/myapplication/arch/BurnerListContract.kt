package com.example.myapplication.arch

import androidx.lifecycle.LiveData
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty

interface BurnerListContract {

    interface ViewModel {

        val isLoading: LiveData<Boolean>

        val burnerColor: LiveData<Int>

        fun onAttached()

        fun onColorChange(color: Int)
    }

    interface DataModel {

        val color: ObservableReadProperty<Int>
    }

    interface Repository {

        fun fetch(callback: (ResponseModel) -> Unit)
    }

    interface Interactor {

        fun onColorChange(value: Int)

        fun fetchDataModel(callback: (DataModel) -> Unit)
    }
}