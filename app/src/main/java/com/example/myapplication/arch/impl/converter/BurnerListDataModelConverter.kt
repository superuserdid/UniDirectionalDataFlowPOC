package com.example.myapplication.arch.impl.converter

import com.example.myapplication.arch.impl.BurnerListDataModelImpl
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.converter.Converter
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Converts one thing to another, in this case, the Response to a BurnerListDataModelImpl.
 */
class BurnerListDataModelConverter(
    private val coroutineContext: CoroutineContext
): Converter<ResponseModel, BurnerListDataModelImpl> {

    override suspend fun convert(
        input: ResponseModel
    ): BurnerListDataModelImpl {
        return withContext(coroutineContext) {
            BurnerListDataModelImpl(input)
        }
    }
}
