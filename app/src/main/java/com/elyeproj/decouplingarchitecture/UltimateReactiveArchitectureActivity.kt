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

    private fun setupBindings() {
        btn_save.clicks().subscribe {
            viewModel?.save(edit_text.text.toString())
        }.addToBag()

        btn_clear.clicks().subscribe {
            viewModel?.clear()
        }.addToBag()

        viewModel?.textSubject?.subscribe { text ->
            edit_text.setText(text)
            text_view.text = text
        }?.addToBag()

        viewModel?.modeTextSignal?.subscribe {
            text_status.text = it
        }?.addToBag()

        viewModel?.hideKeyboardSignal?.subscribe {
            hideKeyboard()
        }?.addToBag()

        viewModel?.hideClearButton?.subscribe {
            btn_clear.hideIt(it)
        }?.addToBag()

        viewModel?.hideTextField?.subscribe {
            edit_text.hideIt(it)
        }?.addToBag()

        viewModel?.hideSaveButton?.subscribe {
            btn_save.hideIt(it)
        }?.addToBag()

        viewModel?.hideTextLabel?.subscribe {
            text_view.hideIt(it)
        }?.addToBag()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.dispose()
    }

    private fun View.hideIt(shouldHide: Boolean) {
        visibility = if (shouldHide) View.GONE else View.VISIBLE
    }

    private fun Disposable.addToBag() {
        disposableBag.add(this)
    }
}
