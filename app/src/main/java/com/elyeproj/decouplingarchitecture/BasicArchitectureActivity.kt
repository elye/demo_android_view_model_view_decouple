package com.elyeproj.decouplingarchitecture

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_architecture.*

class BasicArchitectureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture)
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCyan))

        btn_save.setOnClickListener { save() }
        btn_clear.setOnClickListener { clear() }
        setupInitialView()

    }

    private fun save() {
        if (edit_text.text.isNotEmpty()) {
            enterViewModel(edit_text.text.toString())
        }
    }

    private fun clear() {
        enterEditMode()
    }

    private fun setupInitialView() {
        if (MainActivity.persistedText.isNotEmpty()) {
            enterViewModel(MainActivity.persistedText)
        } else {
            enterEditMode()
        }
    }

    private fun enterViewModel(text: String) {
        hideKeyboard()
        text_status.text = MainActivity.VIEW_MODE
        text_view.text = text
        MainActivity.persistedText = text
        btn_clear.visibility = View.VISIBLE
        text_view.visibility = View.VISIBLE
        btn_save.visibility = View.GONE
        edit_text.visibility = View.GONE
    }

    private fun enterEditMode() {
        text_status.text = MainActivity.EDIT_MODE
        edit_text.setText(String())
        btn_clear.visibility = View.GONE
        text_view.visibility = View.GONE
        btn_save.visibility = View.VISIBLE
        edit_text.visibility = View.VISIBLE
    }
}
