package com.example.myapplication.foundation.arch.properties.read

import com.example.myapplication.foundation.arch.properties.ObservableProperty
import kotlin.reflect.KProperty

interface ObservableReadProperty<T>: ReadProperty<T> {

    fun observe(callback: (T) -> Unit)
}
