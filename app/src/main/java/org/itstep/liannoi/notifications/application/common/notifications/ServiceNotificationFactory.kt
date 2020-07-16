package org.itstep.liannoi.notifications.application.common.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class ServiceNotificationFactory<TService>(
    context: Context,
    details: Details,
    private vararg val actions: Action<TService>
) : DefaultNotificationFactory(context, details) {

    class Action<TService>(
        val icon: Int,
        val requestCode: Int,
        val service: Class<TService>,
        val action: String
    )

    override fun create(): Notification {
        val builder = prepare()

        actions.forEach {
            builder.addAction(
                it.icon,
                it.action,
                PendingIntent.getService(
                    context,
                    it.requestCode,
                    Intent(context, it.service).setAction(it.action),
                    0
                )
            )
        }

        return builder.build()
    }
}
