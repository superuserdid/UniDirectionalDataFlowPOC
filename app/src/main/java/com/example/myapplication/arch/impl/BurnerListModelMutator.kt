package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListWritableModel
import com.example.myapplication.arch.models.Response

/**
 * We need to be able to mutate the data model with a reference to the source of truth (the Response)
 *
 * Component that can use the response to appropriately update the data model the
 * View is observing.
 */
class BurnerListModelMutator(
    private val response: Response,
    private val writable: BurnerListWritableModel
): BurnerListWritableModel {

    override fun setColor(value: Int) {
        // TODO: Likely want to convert the Response to a not nullable representation to
        // get rid of null checks.
        // arbitrary logic below.
        if (response.color != null && value == -1) {
            writable.setColor(response.color)
        } else {
            writable.setColor(value)
        }
    }
}
