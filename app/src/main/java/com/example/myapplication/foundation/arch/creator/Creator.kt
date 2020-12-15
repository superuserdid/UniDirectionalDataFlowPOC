package com.example.myapplication.foundation.arch.creator

interface Creator<INPUT, OUTPUT> {

    fun create(input: INPUT): OUTPUT
}
