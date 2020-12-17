package com.example.myapplication.arch.impl.converter

import com.example.myapplication.arch.models.Response
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.converter.Converter

/**
 * Converts one thing to another, in this case, the Response to a BurnerListDataModelImpl.
 */
class BurnerListResponseModelConverter: Converter<Response, ResponseModel> {

    override fun convert(
        input: Response,
        callback: (ResponseModel) -> Unit
    ) {
        callback(ResponseModel(input.color ?: DEFAULT_COLOR))
    }

    companion object {

        const val DEFAULT_COLOR = 123
    }
}
