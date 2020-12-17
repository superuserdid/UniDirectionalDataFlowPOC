package com.example.myapplication.arch.impl.converter

import com.example.myapplication.arch.impl.BurnerListDataModelImpl
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.converter.Converter

/**
 * Converts one thing to another, in this case, the Response to a BurnerListDataModelImpl.
 */
class BurnerListDataModelConverter: Converter<ResponseModel, BurnerListDataModelImpl> {

    override fun convert(
        input: ResponseModel,
        callback: (BurnerListDataModelImpl) -> Unit
    ) {
        callback(BurnerListDataModelImpl(input))
    }
}
