package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListContract
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.business.arch.properties.nativeimpl.FlowObservableProperty

// DataModel handles what it means to set and get/write and read.
class BurnerListDataModelImpl(
    private val response: ResponseModel
) : BurnerListContract.DataModel {

    override val color: FlowObservableProperty<Int> = FlowObservableProperty(response.color)

    fun setColor(value: Int) {
        if (response.color != 999 && value == -1) {
            color.set(response.color)
        } else {
            color.set(value)
        }
    }
}
