package com.elyeproj.decouplingarchitecture

class NotificationViewModel {

    fun initialSetup() {
        NotificationModel.sharedInstance.text = NotificationModel.sharedInstance.text
    }

    fun save(text: String) {
        if (text.isEmpty()) return
        NotificationModel.sharedInstance.text = text
    }

    fun clear() {
        NotificationModel.sharedInstance.text = null
    }
}
