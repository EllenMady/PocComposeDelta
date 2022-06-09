package br.com.poccompose.real.util

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class NotificationTrigger(val context: Context, private val workerParameters: WorkerParameters)
    : Worker(context,workerParameters){
    override fun doWork(): Result {
        with(workerParameters.inputData){
            val title = getString(NOTIFICATION_TITLE_KEY) ?: "Notification"
            val body = getString(NOTIFICATION_BODY_KEY)
            return body?.let {
                LocalNotificationUtil(context)
                    .notify(getString(NOTIFICATION_ID_KEY).orEmpty(), title,it)
                Result.success()
            } ?: Result.failure()
        }
    }
    companion object {
        fun getNotificationRequest(inputData: Data, intervalDelay: Int = 5) =
            OneTimeWorkRequestBuilder<NotificationTrigger>()
                .addTag("Notification")
                .setInputData(inputData)
                .setInitialDelay(intervalDelay.toLong(),TimeUnit.SECONDS)
                .build()
    }
}