package com.example.myapplication.business.arch.properties.nativeimpl

import com.example.myapplication.business.arch.properties.nativeimpl.read.FlowReadProperty
import com.example.myapplication.business.arch.properties.nativeimpl.write.FlowWriteProperty
import com.example.myapplication.foundation.arch.properties.ObservableProperty
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty
import com.example.myapplication.foundation.arch.properties.write.WriteProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow

class FlowObservableProperty<T> @ExperimentalCoroutinesApi constructor(
    private val subject: BroadcastChannel<T> = BroadcastChannel(1),
    private val initialValue: T?,
    private val scope: CoroutineScope
): ObservableProperty<T>,
    WriteProperty<T> by FlowWriteProperty(subject, initialValue, scope),
    FlowObservableReadProperty<T> by FlowReadProperty(subject, scope) {

    constructor(
        initialValue: T? = null,
        capacity: Int = 1,
        scope: CoroutineScope = GlobalScope
    ): this(
        BroadcastChannel<T>(capacity),
        initialValue,
        scope
    )
}

interface FlowObservableReadProperty<T>: ObservableReadProperty<T> {

    @FlowPreview
    val flow: Flow<T>
}
