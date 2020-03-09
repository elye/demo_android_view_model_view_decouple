package com.elyeproj.decouplingarchitecture

class FunctionalViewModel {

    fun initialSetup(enterEditMode: () -> Unit,
                     enterViewMode: (text: String) -> Unit) {
        if (MainActivity.persistedText.isEmpty()) {
            enterEditMode()
        } else {
            enterViewMode(MainActivity.persistedText)
        }
    }

    fun save(text: String, enterViewMode: (text: String) -> Unit) {
        if (text.isEmpty()) return
        MainActivity.persistedText = text
        enterViewMode(text)
    }

    fun clear(enterEditMode: () -> Unit) {
        MainActivity.persistedText = String()
        enterEditMode()
    }
}
