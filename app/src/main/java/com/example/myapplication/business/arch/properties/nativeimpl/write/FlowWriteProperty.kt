package com.example.myapplication.business.arch.properties.nativeimpl.write

import com.example.myapplication.foundation.arch.properties.write.WriteProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.launch

class FlowWriteProperty<T>(
    private val subject: BroadcastChannel<T>,
    initialValue: T?,
    private val coroutineScope: CoroutineScope
): WriteProperty<T> {

    init {
        if (initialValue != null) {
            set(initialValue)
        }
    }

    override fun set(value: T?) {
        // Find a way to not send null but not have to do this.
        if (value != null) {
            coroutineScope.launch {
                subject.send(value)
            }
        }
    }
}
