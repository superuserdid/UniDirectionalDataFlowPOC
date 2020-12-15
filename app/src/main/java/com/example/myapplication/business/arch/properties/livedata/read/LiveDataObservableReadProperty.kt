package com.example.myapplication.business.arch.properties.livedata.read

import androidx.lifecycle.LiveData
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty

class LiveDataObservableReadProperty<T>(
    private val liveData: LiveData<T>
): ObservableReadProperty<T> {

    override val value: T
        get() = liveData.value!!

    override fun observe(callback: (T) -> Unit) {
        liveData.observeForever(callback)
    }
}
