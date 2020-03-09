package com.elyeproj.decouplingarchitecture

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_architecture.*

class FunctionalArchitectureActivity : AppCompatActivity() {

    private var viewModel: FunctionalViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture)
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGray))

        viewModel = FunctionalViewModel()

        btn_save.setOnClickListener { save() }
        btn_clear.setOnClickListener { clear() }
        setupInitialView()
    }

    private fun save() {
        viewModel?.save(edit_text.text.toString(), ::enterViewMode)
    }

    private fun clear() {
        viewModel?.clear(::enterEditMode)
    }

    private fun setupInitialView() {
        viewModel?.initialSetup(::enterEditMode, ::enterViewMode)
    }

    private fun enterViewMode(text: String) {
        hideKeyboard()
        text_view.text = text
        btn_clear.visibility = View.VISIBLE
        text_view.visibility = View.VISIBLE
        btn_save.visibility = View.GONE
        edit_text.visibility = View.GONE
    }

    private fun enterEditMode() {
        edit_text.setText(String())
        btn_clear.visibility = View.GONE
        text_view.visibility = View.GONE
        btn_save.visibility = View.VISIBLE
        edit_text.visibility = View.VISIBLE
    }
}
