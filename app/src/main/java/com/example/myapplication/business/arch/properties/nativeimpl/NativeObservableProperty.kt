package com.example.myapplication.business.arch.properties.nativeimpl

import com.example.myapplication.business.arch.properties.nativeimpl.read.NativeObservableReadProperty
import com.example.myapplication.business.arch.properties.nativeimpl.write.NativeWriteProperty
import com.example.myapplication.foundation.arch.properties.ObservableProperty
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty
import com.example.myapplication.foundation.arch.properties.write.WriteProperty

class NativeObservableProperty<T>(
    private val subject: NativeObservablePropertySubject<T> = NativeObservablePropertySubject()
): ObservableProperty<T>,
    WriteProperty<T> by NativeWriteProperty(subject),
    ObservableReadProperty<T> by NativeObservableReadProperty(subject) {

    constructor(initialValue: T): this(NativeObservablePropertySubject(initialValue))
}
