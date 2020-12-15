package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListDataModel
import com.example.myapplication.arch.BurnerListWritableModel
import com.example.myapplication.business.arch.properties.nativeimpl.NativeObservableProperty
import com.example.myapplication.business.arch.properties.rx.RxPublishSubjectObservableProperty
import com.example.myapplication.foundation.arch.properties.ObservableProperty

// DataModel handles what it means to set and get/write and read.
class BurnerListDataModelImpl(
    _color: Int
) : BurnerListDataModel, BurnerListWritableModel {

    // Observable Property allows us to implement our own Observer pattern or
    // Use another implementation like LiveData or Rx.
    // Feel Free to Change this to RxPublishSubjectObservableProperty, NativeObservableProperty or LiveDataObservableProperty
    override val color: ObservableProperty<Int> by NativeObservableProperty(_color)


    override fun setColor(value: Int) {
        color.set(value)
    }
}
