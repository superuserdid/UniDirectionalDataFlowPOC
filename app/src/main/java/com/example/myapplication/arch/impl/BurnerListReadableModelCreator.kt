package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListDataModel
import com.example.myapplication.business.arch.creator.ReadableModelCreator

class BurnerListReadableModelCreator: ReadableModelCreator<BurnerListDataModel> {

    override fun create(input: BurnerListDataModel): BurnerListDataModel {
        // Copy model to only expose readable attributes.
        // This prevents ability to cast upstream.
        return object: BurnerListDataModel by input { }
    }

}
