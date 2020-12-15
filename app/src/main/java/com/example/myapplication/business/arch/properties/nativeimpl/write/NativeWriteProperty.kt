package com.example.myapplication.business.arch.properties.nativeimpl.write

import com.example.myapplication.business.arch.properties.nativeimpl.NativeObservablePropertySubject
import com.example.myapplication.foundation.arch.properties.write.WriteProperty

class NativeWriteProperty<T>(
    private val subject: NativeObservablePropertySubject<T>
): WriteProperty<T> {

    override fun set(value: T) {
        subject.value = value
        subject.callbacks.forEach { it.invoke(value) }
    }
}
