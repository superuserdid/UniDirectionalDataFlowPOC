package com.example.myapplication.business.arch.properties.nativeimpl.read

import com.example.myapplication.business.arch.properties.nativeimpl.NativeObservablePropertySubject
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty

class NativeObservableReadProperty<T>(
    private val subject: NativeObservablePropertySubject<T>
): ObservableReadProperty<T> {

    override val value: T
        get() = subject.value!!

    override fun observe(callback: (T) -> Unit) {
        subject.callbacks.add(callback)
    }
}
