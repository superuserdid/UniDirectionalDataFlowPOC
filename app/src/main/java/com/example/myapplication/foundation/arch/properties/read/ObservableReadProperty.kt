package com.example.myapplication.foundation.arch.properties.read

interface ObservableReadProperty<T>: ReadProperty<T> {

    fun observe(callback: (T) -> Unit)
}
