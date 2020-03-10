package com.elyeproj.decouplingarchitecture

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_architecture.*

class DelegateArchitectureActivity : AppCompatActivity(), DelegateView {

    private var viewModel: DelegateViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture)
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow))

        viewModel = DelegateViewModel(this)

        btn_save.setOnClickListener { save() }
        btn_clear.setOnClickListener { clear() }
        setupInitialView()
    }

    private fun save() {
        viewModel?.save(edit_text.text.toString())
    }

    private fun clear() {
        viewModel?.clear()
    }

    private fun setupInitialView() {
        viewModel?.initialSetup()
    }

    override fun enterViewMode(text: String) {
        hideKeyboard()
        text_status.text = MainActivity.VIEW_MODE
        text_view.text = text
        btn_clear.visibility = View.VISIBLE
        text_view.visibility = View.VISIBLE
        btn_save.visibility = View.GONE
        edit_text.visibility = View.GONE
    }

    override fun enterEditMode() {
        text_status.text = MainActivity.EDIT_MODE
        edit_text.setText(String())
        btn_clear.visibility = View.GONE
        text_view.visibility = View.GONE
        btn_save.visibility = View.VISIBLE
        edit_text.visibility = View.VISIBLE
    }
}
