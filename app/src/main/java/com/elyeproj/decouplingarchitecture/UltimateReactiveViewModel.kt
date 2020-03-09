package com.elyeproj.decouplingarchitecture

import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

class UltimateReactiveViewModel {

    val textSubject: ReplaySubject<String> = ReplaySubject.create()
    val isTextSetSignal: Observable<Boolean> = textSubject.map { it.isNotEmpty() }

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
