package com.example.myapplication.arch

import com.example.myapplication.arch.models.Response

interface BurnerListRepository {

    fun fetch(callback: (Response) -> Unit)
}