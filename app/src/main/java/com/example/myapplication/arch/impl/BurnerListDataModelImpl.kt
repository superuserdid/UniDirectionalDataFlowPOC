package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListContract
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.business.arch.properties.nativeimpl.NativeObservableProperty
import com.example.myapplication.foundation.arch.properties.ObservableProperty

// DataModel handles what it means to set and get/write and read.
class BurnerListDataModelImpl(
    private val response: ResponseModel
) : BurnerListContract.DataModel {

    // Observable Property allows us to implement our own Observer pattern or
    // Use another implementation like LiveData or Rx.
    // Feel Free to Change this to RxPublishSubjectObservableProperty, NativeObservableProperty or LiveDataObservableProperty
    override val color: ObservableProperty<Int> by NativeObservableProperty(response.color)

    fun setColor(value: Int) {
        if (response.color != 999 && value == -1) {
            color.set(response.color)
        } else {
            color.set(value)
        }
    }
}
