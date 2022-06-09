package br.com.poccompose.real.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.poccompose.application.App
import br.com.poccompose.real.extensions.getActivity
import com.google.android.play.core.review.ReviewManagerFactory

object RedirectUtil {


    fun redirectToAppStore(context: Context = App.getInstance()){
       val packageName = context.packageName
        context.startActivity(
           Intent(Intent.ACTION_VIEW).apply {
               data = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
               setPackage("com.android.vending")
               addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
           }
       )
    }

    /**
     * Do not use this method
     */
    fun redirectToReviewPlayStore(context: Context){
        context.startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("market://details?id=br.com.transferr.driver")
                setPackage("com.android.vending")
                addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            }
        )
    }

    fun redirectToReviewAppStore(context: Context = App.getInstance()){
        val manager = ReviewManagerFactory.create(context)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = task.result
                reviewInfo?.let {
                    val activity = context.getActivity()
                    activity?.let { act ->
                        val flow = manager.launchReviewFlow(act, reviewInfo)
                        flow.addOnCompleteListener {
                            // The flow has finished. The API does not indicate whether the user
                            // reviewed or not, or even whether the review dialog was shown. Thus, no
                            // matter the result, we continue our app flow.
                            val complete = it.isComplete
                            val success = it.isSuccessful
                            Log.d("STORE_REVIEW", "complet: $complete success: $success")
                            if(complete && success){
                                Log.d("STORE_REVIEW", "OK sucesso")
                            }else{
                                Log.d("STORE_REVIEW", "Falha")
                            }
                        }
                    }
                }?: redirectToAppStore(context)

            } else {
                redirectToAppStore(context)
            }
        }
    }
    fun redirectToWhatsapp(phone:Long,text:String?,context: Context = App.getInstance()){
        context.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(
                String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phone.toString(), text)
            ))
        )
    }
    fun redirectToWhatsapp(phone:Long,context: Context = App.getInstance()){
        redirectToWhatsapp(phone,null,context)
    }

    fun redirectToWhatsappWithMensageError(activity: AppCompatActivity?, text:String?, phone:Long, alertMessage:String, context: Context = App.getInstance()) : String?{
        return try{
            if(isDeviceWithWhatsApp(context.packageManager)) {
                redirectToWhatsapp(phone, text, context)
                null
            }else{
                alertMessage
            }
        }catch (e: Exception){
            alertMessage
        }
    }

    fun isDeviceWithWhatsApp(packageManager: PackageManager): Boolean{
        return try {
            val pkgInfo = packageManager.getPackageInfo(WHATS_APP_PACKAGE, PackageManager.GET_META_DATA)
            pkgInfo != null
        }catch (e: PackageManager.NameNotFoundException){
            e.printStackTrace()
            false
        }
    }
}