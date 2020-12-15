package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListRepository
import com.example.myapplication.arch.models.Response

class BurnerListRepositoryImpl: BurnerListRepository {

    override fun fetch(callback: (Response) -> Unit) {
        callback(Response(5))
    }

}
