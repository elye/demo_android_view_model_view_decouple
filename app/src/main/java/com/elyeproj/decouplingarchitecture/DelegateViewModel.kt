package com.elyeproj.decouplingarchitecture

class DelegateViewModel(private val delegate: DelegateView) {

    fun initialSetup() {
        if (MainActivity.persistedText.isEmpty()) {
            delegate.enterEditMode()
        } else {
            delegate.enterViewModel(MainActivity.persistedText)
        }
    }

    fun save(text: String) {
        if (text.isEmpty()) return
        MainActivity.persistedText = text
        delegate.enterViewModel(text)
    }

    fun clear() {
        MainActivity.persistedText = String()
        delegate.enterEditMode()
    }
}