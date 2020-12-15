package com.example.myapplication.business.arch.properties.nativeimpl

class NativeObservablePropertySubject<T>(
    var value: T? = null,
    val callbacks: ArrayList<(T) -> Unit> = arrayListOf()
)
