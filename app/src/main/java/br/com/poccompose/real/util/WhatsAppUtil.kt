package br.com.poccompose.real.util

import android.content.Intent
import android.content.pm.PackageManager

const val WHATS_APP_PACKAGE = "com.whatsapp"
@Deprecated("Use RedirectUtil")
object WhatsAppUtil {

    fun getWhatsAppIntent(message: String) : Intent{
        return Intent(Intent.ACTION_SEND).apply {
            type = TEXT_PLAIN
            `package` = WHATS_APP_PACKAGE
            putExtra(Intent.EXTRA_TEXT,message)
        }
    }

    fun isDeviceWith(packageManager: PackageManager): Boolean{
        return try {
            val pkgInfo = packageManager.getPackageInfo(WHATS_APP_PACKAGE, PackageManager.GET_META_DATA)
            pkgInfo != null
        }catch (e: PackageManager.NameNotFoundException){
            e.printStackTrace()
            false
        }
    }

}