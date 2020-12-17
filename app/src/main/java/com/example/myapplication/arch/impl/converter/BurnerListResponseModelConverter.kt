package com.example.myapplication.arch.impl.converter

import com.example.myapplication.arch.models.Response
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.converter.Converter
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Converts one thing to another, in this case, the Response to a BurnerListDataModelImpl.
 */
class BurnerListResponseModelConverter(
    private val converterContext: CoroutineContext
): Converter<Response, ResponseModel> {

    override suspend fun convert(
        input: Response
    ): ResponseModel {
        return withContext(converterContext) {
            ResponseModel(input.color ?: DEFAULT_COLOR)
        }
    }

    companion object {

        const val DEFAULT_COLOR = 123
    }
}
