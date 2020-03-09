package com.elyeproj.decouplingarchitecture

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    currentFocus?.let {
        val inputManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
