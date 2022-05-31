package br.com.poccompose.real.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import br.com.poccompose.R.string as RStr
import java.util.*

object DateUtil {

    private const val formaterToDatabase = "yyyy-MM-dd HH:mm:ss"
    private const val formaterToDatabaseWithMil = "yyyy-MM-dd HH:mm:ss.SSS"
    private const val formaterToFilterReportsNoTime = "yyyy-MM-dd"
    private const val ZERO_TIME =  "00:00:00"

    private val nameMonths =
        LocalUtil.let {
            listOf(
                it.getString(RStr.jan) , it.getString(RStr.fev), it.getString(RStr.mar),
                it.getString(RStr.apr), it.getString(RStr.may), it.getString(RStr.june),
                it.getString(RStr.july), it.getString(RStr.aug), it.getString(RStr.sept),
                it.getString(RStr.oct), it.getString(RStr.nov), it.getString(RStr.dec)
            )
        }

    private val nameDays =
        LocalUtil.let {
            listOf(
                it.getString(RStr.sun),it.getString(RStr.mon),it.getString(RStr.tue),it.getString(RStr.wed),
                it.getString(RStr.thu),it.getString(RStr.fri),it.getString(RStr.sat),
            )
        }

    fun formaterToFilterReports(date: Date?): String?{
        return date?.let {
            formatDateToString(date = it,format = formaterToFilterReportsNoTime).plus(" $ZERO_TIME")
        }
    }

    fun formatDateToReceipt(date: Date?) : String{
        return date?.let {
            val locale = Locale.getDefault().language
            if(locale == PT || locale == ES){
                formatDateToString(date = it,format = "dd")
            }
            ""
        }.orEmpty()
    }

    fun formatDateToString(date: Date?, format: String) : String? {
        return date?.let {
            val dateTime = if(format == "MMMM" || format == "LLLL"){
                SimpleDateFormat(format, Locale.getDefault())
            }else{
                SimpleDateFormat(format, Locale.US)
            }
            dateTime.timeZone = TimeZone.getDefault()
            dateTime.format(it)
        }
    }
}