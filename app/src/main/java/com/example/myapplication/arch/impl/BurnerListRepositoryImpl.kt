package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListContract
import com.example.myapplication.arch.models.Response
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.converter.Converter

class BurnerListRepositoryImpl(
    private val converter: Converter<Response, ResponseModel>
): BurnerListContract.Repository {

    override fun fetch(callback: (ResponseModel) -> Unit) {
        converter.convert(Response(5)) { model ->
            callback(model)
        }
    }
}
