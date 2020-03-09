package com.elyeproj.decouplingarchitecture

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_architecture.*

class UltimateReactiveArchitectureActivity : AppCompatActivity() {

    private var viewModel: UltimateReactiveViewModel? = null
    private val disposableBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture)
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen))

        viewModel = UltimateReactiveViewModel()

        setupBindings()
    }

    private fun save() {
        viewModel?.save(edit_text.text.toString())
    }

    private fun clear() {
        viewModel?.clear()
    }

    private fun setupBindings() {
        btn_save.clicks().subscribe { save() }.addToBag()
        btn_clear.clicks().subscribe { clear() }.addToBag()

        viewModel?.textSubject?.subscribe { text ->
            edit_text.setText(text)
            text_view.text = text
        }?.addToBag()

        viewModel?.isTextSetSignal?.subscribe {
            if (it) hideKeyboard()
            btn_clear.visibility = if (it) View.VISIBLE else View.GONE
            text_view.visibility = if (it) View.VISIBLE else View.GONE
            btn_save.visibility = if (it) View.GONE else View.VISIBLE
            edit_text.visibility = if (it) View.GONE else View.VISIBLE
        }?.addToBag()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.dispose()
    }

    fun Disposable.addToBag() {
        disposableBag.add(this)
    }
}
