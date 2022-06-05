package br.com.poccompose.real.util

import android.content.Intent
import android.net.Uri

object EmailUtil {

    fun getIntent(emailDetail: EmailDetail): Intent{
        return Intent(Intent.ACTION_SEND).apply {
            data = Uri.parse("mailto:")
            type = TEXT_PLAIN
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailDetail.recipient))
            putExtra(Intent.EXTRA_SUBJECT,emailDetail.subject)
            putExtra(Intent.EXTRA_TEXT,emailDetail.message)
        }
    }

}

data class EmailDetail(
    val recipient: String,
    val subject: String,
    val message: String
)