package com.example.myapplication.business.arch.properties.rx

import com.example.myapplication.business.arch.properties.rx.read.RxPublishSubjectObservableReadProperty
import com.example.myapplication.business.arch.properties.rx.write.RxPublishSubjectWriteProperty
import com.example.myapplication.foundation.arch.properties.ObservableProperty
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty
import com.example.myapplication.foundation.arch.properties.write.WriteProperty
import io.reactivex.subjects.PublishSubject

/**
 * Observable Property that uses Rx underneath. Note, an implementor can check the type and
 * cast a property to this object to attain access to the subject. This is meant to allow
 * for the user of Rx in the implementation layer, while hiding it in the framework (abstractions)
 * layer.
 */
class RxPublishSubjectObservableProperty<T>(
    // Expose subject for customization for those who want to depend on this property.
    val subject: PublishSubject<T> = PublishSubject.create<T>(),
    private val initialValue: T
): ObservableProperty<T>,
    WriteProperty<T> by RxPublishSubjectWriteProperty(
        subject,
        initialValue
    ),
    ObservableReadProperty<T> by RxPublishSubjectObservableReadProperty(subject) {

    constructor(initialValue: T): this(PublishSubject.create<T>(), initialValue)
}
