package com.example.myapplication.business.arch.properties.livedata

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.business.arch.properties.livedata.read.LiveDataObservableReadProperty
import com.example.myapplication.business.arch.properties.livedata.write.LiveDataWriteProperty
import com.example.myapplication.foundation.arch.properties.ObservableProperty
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty
import com.example.myapplication.foundation.arch.properties.write.WriteProperty
import kotlin.reflect.KProperty

class LiveDataObservableProperty<T>(
    private val liveData: MutableLiveData<T> = MutableLiveData()
): ObservableProperty<T>,
    WriteProperty<T> by LiveDataWriteProperty(liveData),
    ObservableReadProperty<T> by LiveDataObservableReadProperty(liveData) {

    constructor(initialValue: T): this(MutableLiveData(initialValue))
}
