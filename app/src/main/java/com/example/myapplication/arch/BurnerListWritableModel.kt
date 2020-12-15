package com.example.myapplication.arch

import com.example.myapplication.business.arch.models.write.WritableModel

/**
 * Exposes only writable aspects of data model
 */
interface BurnerListWritableModel: WritableModel {

    fun setColor(value: Int)
}
