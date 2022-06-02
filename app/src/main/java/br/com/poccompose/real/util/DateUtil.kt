package br.com.poccompose.real.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import br.com.poccompose.R.string as RStr

object DateUtil {

    private const val formaterToDatabase = "yyyy-MM-dd HH:mm:ss"
    private const val formaterToDatabaseWithMil = "yyyy-MM-dd HH:mm:ss.SSS"
    private const val formaterToFilterReportsNoTime = "yyyy-MM-dd"
    private const val ZERO_TIME = "00:00:00"
    private const val M_FORMAT = "MMMM"
    private const val YEAR_FORMAT = "yyyy"
    private const val DAY_FORMAT = "dd"
    private const val DATE_EMPTY = ""
    private const val SQLITE_NULL = "NULL"
    const val YYYY_MM_DD = "yyyy-MM-dd"
    const val DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss"

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

    fun getMonthListAsString() : List<String>{
        return nameMonths
    }

    fun getDaysOfWeekListAsString(): List<String>{
        return nameDays
    }

    fun formaterToFilterReports(date: Date?): String?{
        return date?.let {
            formatDateToString(date = it,format = formaterToFilterReportsNoTime).plus(" $ZERO_TIME")
        }
    }
    private fun getCurrentTimeZone() = TimeZone.getDefault()
    private fun getExtenso() = LocalUtil.getString(RStr.date_util_de_extenso)
    fun formatDateToReceipt(date: Date?, language: String? = null) : String{
        return date?.let { it ->
            val locale = language ?: Locale.getDefault().language
            if(locale == PT || locale == ES){
                "${formatDateToString(date = it,format = DAY_FORMAT)}${getExtenso()}"
                    .plus("${formatDateToString(date = it,format = M_FORMAT)}${getExtenso()}${formatDateToString(date = it,format = YEAR_FORMAT)}")
            }else{
                "${formatDateToString(date = it, format = M_FORMAT)} ${formatDateToString(it,
                    DAY_FORMAT)},${formatDateToString(date = it,format = YEAR_FORMAT)}"
            }
        }.orEmpty()
    }

    fun formatDateToString(date: Date?, format: String) : String? {
        return date?.let {
            val dateTime = if(format == M_FORMAT || format == "LLLL"){
                SimpleDateFormat(format, Locale.getDefault())
            }else{
                SimpleDateFormat(format, Locale.US)
            }
            dateTime.timeZone = getCurrentTimeZone()
            dateTime.format(it)
        }
    }

    fun getDateFormaterShortCurrent(): String{
        val format = SimpleDateFormat.getDateInstance(DateFormat.SHORT) as SimpleDateFormat
        return format.toPattern()
    }

    fun getDateFormaterWithHourLongCurrent(date: Date): String{
        val formatDate = SimpleDateFormat.getDateInstance(DateFormat.SHORT) as SimpleDateFormat
        val formatTime = SimpleDateFormat.getTimeInstance(DateFormat.MEDIUM) as SimpleDateFormat
        val finalFormat = "${formatDate.toPattern()} ${formatTime.toPattern()}"
        return SimpleDateFormat(finalFormat).format(date)
    }

    fun getDateFormaterWithHourShortCurrent(): String{
        val formatDate = SimpleDateFormat.getDateInstance(DateFormat.SHORT) as SimpleDateFormat
        val formatTime = SimpleDateFormat.getTimeInstance(DateFormat.SHORT) as SimpleDateFormat
        return "${formatDate.toPattern()} ${formatTime.toPattern()}"
    }

    fun formatDateShowTextField(date: Date?) : String{
        return date?.let {
            formatDateToString(date = it, format = getDateFormaterShortCurrent())
        }?: DATE_EMPTY
    }

    fun formatDateShowTextFieldWithHour(date: Date?): String{
        return date?.let {
            formatDateToString(date = it, getDateFormaterWithHourShortCurrent())
        }?: DATE_EMPTY
    }

    fun formatDateToSqlLite(date: Date?): String{
        return date?.let {
            formatDateToString(date = it, format = formaterToDatabase)
        }?: SQLITE_NULL
    }

    fun formatDateMilToSqlLite(date: Date?): String{
        return date?.let {
            formatDateToString(date = it, format = formaterToDatabaseWithMil)
        }?: SQLITE_NULL
    }

    fun formatSqliteToModel(date: String?): Date?{
        //TODO: Qual o formato da data de entrada
        return formatStringToDate(date, format = formaterToDatabase)
    }

    fun formatStringToDate(date: String?, format: String): Date?{
        return date?.let {
            //TODO parse this:  dateFormatter.locale = Locale(identifier: "en_US_POSIX")
            val sFormatter = SimpleDateFormat(format, Locale.US)
            sFormatter.timeZone = getCurrentTimeZone()
            sFormatter.parse(it)
        }
    }

    fun addToDate(date:Date,day:Int?,month:Int? = null,year:Int? = null): Date{
        val calendar = Calendar.getInstance()
        calendar.time = date
        day?.let {
            calendar.add(Calendar.DAY_OF_MONTH,it)
        }
        month?.let {
            calendar.add(Calendar.MONTH,it)
        }
        year?.let {
            calendar.add(Calendar.YEAR,it)
        }
        return calendar.time
    }

    fun setHourZero(date:Date): Date{
        return formatStringToDate(date = formatDateToString(date, YYYY_MM_DD),format = YYYY_MM_DD)!!
    }

    fun getDate(numberOfYear:Int?,numberOfMonth:Int?,numberOfDay:Int?): Date{
        if(numberOfYear == null || numberOfMonth == null || numberOfDay == null){
            return getCurrentDate()
        }
        return getDateTime(numberOfYear,numberOfMonth,numberOfDay,0,0,0)
    }

    fun getMonth(date:Date): Int{
        //TODO: o mes no iOS comeca com zero?
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.MONTH)
    }

    fun getYear(date:Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.YEAR)
    }

    fun formatToJson(date:Date?) : Any?{
        return date?.let {
            formatterDate(date= date, format= "yyyy-MM-dd'T'HH:mm:ss.SSSZ"   ) as Any
        }
    }

    fun formatterDate (date:Date,format: String): String{
        val dateFormatter = SimpleDateFormat(format)
        return dateFormatter.format(date)
    }

    fun formatResponseJson(dateString:Any?): Date?{
        return dateString?.let{
            //Nao fazer
            getCurrentDate()
        }
    }

    fun formatResponseJsonDateServerRest(dateString:Any?): Date?{
        return dateString?.let {
            //Nao fazer
            getCurrentDate()
        }
    }

    internal fun getFromDate(date: Date, field: Int): Int {
        val calendar= Calendar.getInstance()
        calendar.time = date
        return calendar.get(field)
    }

    fun getNameLast12Months(date:Date): Months{
        val months = mutableListOf<String>()
        val monthsInt= mutableListOf<Int>()
        var currentMonth = getFromDate(date,Calendar.MONTH)
        var monthsAdd = 0
        while (monthsAdd < 12){
            months.add(getMonthListAsString()[currentMonth])
            monthsInt.add(currentMonth)
            currentMonth -= 1
            if (currentMonth == 0) {
                currentMonth = 11
            }
            monthsAdd += 1
        }
        return Months(
            months = months,
            monthsInt = monthsInt
        )
    }

    fun getNameLast7Days(date:Date): Days{
        val days = mutableListOf<String>()
        val daysInt= mutableListOf<Int>()
        var currentDay = getFromDate(date,Calendar.DAY_OF_WEEK)
        var dayAdd = 0
        while(dayAdd < 7){
            days.add(getDaysOfWeekListAsString()[currentDay - 1])
            daysInt.add(currentDay)
            currentDay -= 1
            if(currentDay == 0){
                currentDay = 7
            }
            dayAdd += 1
        }
        return Days(
            days = days,
            daysInt = daysInt
        )
    }

    fun getCurrentDate() = Date()
    fun getWeekFromDate(date:Date): Period{
        val currentDay = getFromDate(date,Calendar.DAY_OF_WEEK)
        var dateInitial = getCurrentDate()
        var dateFinal = getCurrentDate()
        when (currentDay) {
            Calendar.SUNDAY -> {
                dateInitial = addToDate(date = date, day = -6)
                dateFinal = date
            }
            Calendar.MONDAY -> {
                dateInitial = date
                dateFinal = addToDate(date = date, day= 6)
            }
            Calendar.TUESDAY -> {
                dateInitial = addToDate(date= date, day= -1)
                dateFinal = addToDate(date= date, day= 5)
            }
            Calendar.WEDNESDAY -> {
                dateInitial = addToDate(date= date, day= -2)
                dateFinal = addToDate(date= date, day= 4)
            }
            Calendar.THURSDAY -> {
                dateInitial = addToDate(date= date, day= -3)
                dateFinal = addToDate(date= date, day= 3)
            }
            Calendar.FRIDAY -> {
                dateInitial = addToDate(date= date, day= -4)
                dateFinal = addToDate(date= date, day= 2)
            }
            Calendar.SATURDAY -> {
                dateInitial = addToDate(date= date, day= -5)
                dateFinal = addToDate(date= date, day= 1)
            }
        }
        return Period(initialDate = dateInitial,finalDate = dateFinal)
    }

    fun getDateTime(year:Int,month:Int,day:Int, hour: Int? = null, minute: Int? = null, second: Int? = null, mili: Int? = null): Date{
        val calendar = Calendar.getInstance()
        with(calendar){
            set(Calendar.DAY_OF_MONTH,day)
            set(Calendar.MONTH,month-1)
            set(Calendar.YEAR,year)
            hour?.let {
                set(Calendar.HOUR_OF_DAY,hour)
            }
            minute?.let {
                set(Calendar.MINUTE,minute)
            }
            second?.let {
                set(Calendar.SECOND,second)
            }
            mili?.let {
                set(Calendar.MILLISECOND,mili)
            }
        }
        return calendar.time
    }
}


data class Months(
    val months: List<String>,
    val monthsInt: List<Int>
)
data class Days(
    val days: List<String>,
    val daysInt: List<Int>
)

data class Period(
    val initialDate: Date,
    val finalDate: Date
)