package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListContract
import com.example.myapplication.arch.models.Response
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.converter.Converter
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class BurnerListRepositoryImpl(
    private val converter: Converter<Response, ResponseModel>,
    private val coroutineContext: CoroutineContext
): BurnerListContract.Repository {

    override suspend fun fetch(): ResponseModel {
        return withContext(coroutineContext) {
            converter.convert(Response(5))
        }
    }
}
