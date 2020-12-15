package com.example.myapplication.foundation.arch.converter

interface Converter<I, O> {

    fun convert(input: I, callback: (O) -> Unit)
}
