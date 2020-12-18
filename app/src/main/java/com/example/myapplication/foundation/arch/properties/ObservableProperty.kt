package com.example.myapplication.foundation.arch.properties

import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty
import com.example.myapplication.foundation.arch.properties.write.WriteProperty
import kotlinx.coroutines.flow.flow
import kotlin.reflect.KProperty

interface ObservableProperty<T>: ObservableReadProperty<T>, WriteProperty<T> {

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: ObservableProperty<T>) {
        set(value.value)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): ObservableProperty<T> {
        return this
    }
}
