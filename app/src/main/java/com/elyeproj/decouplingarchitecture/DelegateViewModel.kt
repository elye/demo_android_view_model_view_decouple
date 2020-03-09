package com.elyeproj.decouplingarchitecture

class DelegateViewModel(private val delegate: DelegateView) {

    fun initialSetup() {
        if (MainActivity.persistedText.isEmpty()) {
            delegate.enterEditMode()
        } else {
            delegate.enterViewMode(MainActivity.persistedText)
        }
    }

    fun save(text: String) {
        if (text.isEmpty()) return
        MainActivity.persistedText = text
        delegate.enterViewMode(text)
    }

    fun clear() {
        MainActivity.persistedText = String()
        delegate.enterEditMode()
    }
}