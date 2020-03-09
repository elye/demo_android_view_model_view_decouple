package com.elyeproj.decouplingarchitecture

import io.reactivex.subjects.ReplaySubject

class ReactiveViewModel {

    val textSubject: ReplaySubject<String> = ReplaySubject.create()

    init {
        textSubject.onNext(MainActivity.persistedText)
    }

    fun save(text: String) {
        if (text.isEmpty()) return
        MainActivity.persistedText = text
        textSubject.onNext(text)
    }

    fun clear() {
        MainActivity.persistedText = String()
        textSubject.onNext(String())
    }
}
