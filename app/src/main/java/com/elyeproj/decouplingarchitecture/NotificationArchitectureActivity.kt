package com.elyeproj.decouplingarchitecture

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_architecture.*

class NotificationArchitectureActivity : AppCompatActivity() {

    private var viewModel: NotificationViewModel? = null

    private val messageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            setTextUpdate(intent.getStringExtra(NotificationModel.textKey))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture)
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPurple))

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(
                messageReceiver,
                IntentFilter(NotificationModel.textSetNotification)
            )

        viewModel = NotificationViewModel()

        btn_save.setOnClickListener { save() }
        btn_clear.setOnClickListener { clear() }
        setupInitialView()
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver)
        super.onDestroy()
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

    private fun setTextUpdate(text: String?) {
        if (text != null && text.isNotEmpty()) {
            enterViewMode(text)
        } else {
            enterEditMode()
        }
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
