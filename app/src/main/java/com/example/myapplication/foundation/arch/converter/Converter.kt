package com.example.myapplication.foundation.arch.converter

interface Converter<I, O> {

    suspend fun convert(input: I): O
}
