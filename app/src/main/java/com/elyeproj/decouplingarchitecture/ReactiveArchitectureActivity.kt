package com.elyeproj.decouplingarchitecture

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import kotlinx.android.synthetic.main.activity_architecture.*

class ReactiveArchitectureActivity : AppCompatActivity() {

    private var viewModel: ReactiveViewModel? = null
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture)
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorOrange))

        viewModel = ReactiveViewModel()

        btn_save.setOnClickListener { save() }
        btn_clear.setOnClickListener { clear() }

        setupBindings()
    }

    private fun save() {
        viewModel?.save(edit_text.text.toString())
    }

    private fun clear() {
        viewModel?.clear()
    }

    private fun setupBindings() {
        disposable = viewModel?.textSubject?.subscribe { text ->
            if (text.isEmpty()) {
                enterEditMode()
            } else {
                enterViewMode(text)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
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
