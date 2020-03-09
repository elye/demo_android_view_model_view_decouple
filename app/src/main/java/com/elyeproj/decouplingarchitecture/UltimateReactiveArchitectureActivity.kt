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

        viewModel?.isTextSetSignal?.subscribe {
            if (it) hideKeyboard()
            btn_clear.hideShow { it }
            text_view.hideShow { it }
        }?.addToBag()

        viewModel?.isTextSetSignal?.map { !it }?.subscribe {
            btn_save.hideShow { it }
            edit_text.hideShow { it }
        }?.addToBag()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.dispose()
    }

    private fun View.hideShow(shouldShow: () -> Boolean) {
        visibility = if (shouldShow()) View.VISIBLE else View.GONE
    }

    private fun Disposable.addToBag() {
        disposableBag.add(this)
    }
}
