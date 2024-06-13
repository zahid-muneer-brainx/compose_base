package com.brainxtech.todolist.datasource.appUtils

import com.brainxtech.todolist.R

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): Int
}
class NotificationPermissionTextProvider
    :PermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): Int {
        return if (isPermanentlyDeclined) R.string.notification_permission_denied_message else R.string.notification_permission_desc
    }
}