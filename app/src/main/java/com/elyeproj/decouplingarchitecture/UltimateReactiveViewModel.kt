package com.elyeproj.decouplingarchitecture

import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

class UltimateReactiveViewModel {

    val textSubject: ReplaySubject<String> = ReplaySubject.create()
    private val isTextSetSignal: Observable<Boolean> = textSubject.map { it.isNotEmpty() }
    val hideTextField: Observable<Boolean> = isTextSetSignal
    val hideSaveButton: Observable<Boolean> = isTextSetSignal
    val hideTextLabel: Observable<Boolean> = isTextSetSignal.map { !it }
    val hideClearButton: Observable<Boolean> = isTextSetSignal.map { !it }
    val hideKeyboardSignal: Observable<Unit> = isTextSetSignal.filter{ it }.map {  }
    val modeTextSignal: Observable<String> = isTextSetSignal.map { editModeText(it) }

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

    private fun editModeText(isTextSet: Boolean) : String {
        return if (isTextSet) {
            MainActivity.VIEW_MODE
        } else {
            MainActivity.EDIT_MODE
        }
    }
}
