package com.elyeproj.decouplingarchitecture

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class NotificationModel private constructor() {
    companion object {
        const val textSetNotification = "textSetNotification"
        const val textKey = "text"
        val sharedInstance = NotificationModel()
    }

    var text: String? = null
        set(value) {
            field = value
            MainActivity.persistedText = value ?: String()
            val intent = Intent(textSetNotification)
            // You can also include some extra data.
            intent.putExtra(textKey, value)
            MainActivity.globalContext?.let {
                LocalBroadcastManager.getInstance(it).sendBroadcast(intent)
            }
        }
}
