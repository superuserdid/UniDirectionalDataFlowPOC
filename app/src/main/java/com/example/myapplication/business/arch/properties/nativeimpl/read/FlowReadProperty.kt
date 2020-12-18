package com.example.myapplication.business.arch.properties.nativeimpl.read

import com.example.myapplication.business.arch.properties.nativeimpl.FlowObservableProperty
import com.example.myapplication.business.arch.properties.nativeimpl.FlowObservableReadProperty
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FlowReadProperty<T>(
    private val channel: BroadcastChannel<T>,
    scope: CoroutineScope
): FlowObservableReadProperty<T> {

    private val callbacks =  arrayListOf<(T) -> Unit>()

    init {
        scope.launch {
            channel.consumeEach { value ->
                callbacks.forEach { it(value) }
            }
        }
    }

    override val flow: Flow<T>
        get() = channel.asFlow()

    override val value: T
        get() = runBlocking { channel.openSubscription().receive() }

    override fun observe(callback: (T) -> Unit) {
        callbacks.add(callback)
    }
}
