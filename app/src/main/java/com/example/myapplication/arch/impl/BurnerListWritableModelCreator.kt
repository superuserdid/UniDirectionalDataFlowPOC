package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListWritableModel
import com.example.myapplication.arch.models.Response
import com.example.myapplication.business.arch.creator.WritableModelCreator

/**
 * Class meant to delegate out the creation of the Mutator object.
 * This is meant to make it easy to test.
 */
class BurnerListWritableModelCreator: WritableModelCreator<Response, BurnerListWritableModel> {

    override fun create(
        input: WritableModelCreator.Input<Response, BurnerListWritableModel>
    ): BurnerListWritableModel {
        return BurnerListModelMutator(input.source, input.model)
    }

}
