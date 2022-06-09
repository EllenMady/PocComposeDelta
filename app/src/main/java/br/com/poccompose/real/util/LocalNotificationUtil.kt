package br.com.poccompose.real.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.WorkManager
import br.com.poccompose.R

const val DEFAULT_CHANNEL_ID = "1"
const val NOTIFICATION_TITLE_KEY = "title"
const val NOTIFICATION_BODY_KEY = "body"
const val NOTIFICATION_ID_KEY = "identifier"
const val SEND_LOG_ERROR_SERVER = "SEND_LOG_ERROR_SERVER"
const val SEND_LOG_ERROR_SERVER_WITHOUT_SIGNATURE = "SEND_LOG_ERROR_SERVER_WITHOUT_SIGNATURE"
const val SEND_LOG_NEED_UPDATE = "SEND_LOG_NEED_UPDATE"
class LocalNotificationUtil(
    val context: Context
) {

    fun scheduleLocalNotification(identifier:String, title:String, body:String, interValInSeconds:Int){
        val inputData = Data.Builder()
            .putString(NOTIFICATION_TITLE_KEY,title)
            .putString(NOTIFICATION_BODY_KEY,body)
            .putString(NOTIFICATION_ID_KEY,identifier)
            .build()
        WorkManager.getInstance(context).enqueue(NotificationTrigger.getNotificationRequest(inputData = inputData,intervalDelay = interValInSeconds))
    }

    fun sendLocalNotificationNow(identifier:String,title:String,body:String){
        notify(identifier,title, body)
    }

    fun sendLocalNotificationErrorLog(){
       scheduleLocalNotification(
           identifier = SEND_LOG_ERROR_SERVER,
           title = context.getString(R.string.warning),
           body = context.getString(R.string.error_server_occurred),
           interValInSeconds = 5
       )
    }

    fun sendLocalNotificationMultipleUserWithoutSignature(){
        scheduleLocalNotification(
            identifier = SEND_LOG_ERROR_SERVER_WITHOUT_SIGNATURE,
            title = context.getString(R.string.expired_subscription),
            body = context.getString(R.string.you_need_active_subscription),
            interValInSeconds = 5
        )
    }

    fun sendLocalNotificationNeedUpdate(){
        scheduleLocalNotification(
            identifier = SEND_LOG_NEED_UPDATE,
            title = context.getString(R.string.warning),
            body = context.getString(R.string.you_need_to_update_your_app),
            interValInSeconds = 2
        )
    }

    fun createNotificationChannel(){
        val channelName = context.getString(R.string.default_channel)
        val channelDescription = context.getString(R.string.default_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(DEFAULT_CHANNEL_ID,channelName,importance).apply {
            description = channelDescription
        }
        getNotificationManager().createNotificationChannel(channel)
    }

    private fun getNotificationManager() = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun createNotification(identifier: String = DEFAULT_CHANNEL_ID, title: String, body: String) = NotificationCompat.Builder(context, identifier)
            .setSmallIcon(R.drawable.ic_notification_24)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_HIGH)

    fun notify(identifier: String, title: String, body: String){
        with(NotificationManagerCompat.from(context)){
            notify(
                DEFAULT_CHANNEL_ID.toInt(),
                createNotification(
                    identifier,
                    title,
                    body).build())
        }
    }
}