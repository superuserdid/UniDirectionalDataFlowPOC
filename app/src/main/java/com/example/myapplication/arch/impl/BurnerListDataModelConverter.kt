package com.example.myapplication.arch.impl

import com.example.myapplication.arch.models.Response
import com.example.myapplication.foundation.arch.converter.Converter

/**
 * Converts one thing to another, in this case, the Response to a BurnerListDataModelImpl.
 *
 * TODO: We likely want to consider converting the response to a not nullable representation FIRST.
 */
class BurnerListDataModelConverter: Converter<Response, BurnerListDataModelImpl> {

    override fun convert(
        input: Response,
        callback: (BurnerListDataModelImpl) -> Unit
    ) {
        callback(BurnerListDataModelImpl(input.color ?: DEFAULT_COLOR))
    }

    companion object {

        private const val DEFAULT_COLOR = 0
    }
}
