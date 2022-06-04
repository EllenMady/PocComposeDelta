package br.com.poccompose.real.util

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class DateUtilTest {

    @Mock
    lateinit var mock: DateUtil

    @Test
    fun should_test_formater_to_filter_reports(){
        val expected = "2022-05-31 00:00:00"
        val actual = DateUtil.formaterToFilterReports(DateUtil.getDateTime(2022,5,31,14,0,0))
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_format_date_to_string(){
        val dateToFormat = DateUtil.getDateTime(2022,5,31,14,0,0)
        val expectedDate = "2022-05-31"
        val formatedDate = DateUtil.formatDateToString(dateToFormat, DateUtil.YYYY_MM_DD)
        assertEquals(expectedDate,formatedDate)
    }

    @Test
    fun should_test_format_date_to_receipt_for_english(){
        val dateToFormat = DateUtil.getDateTime(2022,5,31,14,0,0)
        val actual = DateUtil.formatDateToReceipt(dateToFormat,"en")
        val expected = "May 31,2022"
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_format_date_to_receipt_for_portuguese(){
        val dateToFormat = DateUtil.getDateTime(2022,5,31,14,0,0)
        val actual = DateUtil.formatDateToReceipt(dateToFormat,"pt")
        val expected = "31 May 2022"
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_get_date_formater_short_current(){
        val actual = DateUtil.getDateFormaterShortCurrent()
        val expected = "M/d/yy"
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_getDateFormaterWithHourLongCurrent(){
        val dateToFormat = DateUtil.getDateTime(2022,5,31,14,0,0)
        val expected = "5/31/22 2:00:00 PM"
        val dateFormated = DateUtil.getDateFormaterWithHourLongCurrent(dateToFormat)
        assertEquals(expected,dateFormated)
    }

    @Test
    fun should_get_date_formater_with_hour_short_current(){
        val expected = "M/d/yy h:mm a"
        val actual = DateUtil.getDateFormaterWithHourShortCurrent()
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_format_date_show_text_field(){
        val date = DateUtil.getDateTime(2022,5,31,14,0,0)
        val actual = DateUtil.formatDateShowTextField(date)
        val expected = "5/31/22"
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_format_date_show_text_field_with_hour(){
        val date = DateUtil.getDateTime(2022,5,31,14,0,0)
        val actual = DateUtil.formatDateShowTextFieldWithHour(date)
        val expected = "5/31/22 2:00 PM"
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_format_date_to_sql_lite(){
        val date = DateUtil.getDateTime(2022,5,31,14,0,0)
        val actual = DateUtil.formatDateToSqlLite(date)
        val expected = "2022-05-31 14:00:00"
        assertEquals(expected,actual)
    }

    @Test
    fun should_test_formatDateMilToSqlLite(){
        val date = DateUtil.getDateTime(2022,5,31,14,0,0,mili = 642)
        val actual = DateUtil.formatDateMilToSqlLite(date)
        val expected = "2022-05-31 14:00:00.642"
        assertEquals(expected, actual)
    }

    @Ignore
    @Test
    fun should_test_formatSqliteToModel(){
        val dateToFormat = DateUtil.getDateTime(2022,5,31,14,0,0)
        val date = DateUtil.formatDateToString(dateToFormat,DateUtil.DD_MM_YYYY_HH_MM_SS)
        val actual = DateUtil.formatSqliteToModel(date)
        val expected = ""
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_addToDate(){
        val date = DateUtil.getDateTime(2022,5,31,14,0,0)
        val newDate = DateUtil.addToDate(date = date,day = 6)
        val actual = DateUtil.formatDateToString(newDate,DateUtil.DD_MM_YYYY_HH_MM_SS)
        val expected = "06/06/2022 14:00:00"
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_setHourZero(){
        val date = DateUtil.getDateTime(2022,5,31,14,30,5)
        val dateZeroHour = DateUtil.setHourZero(date)
        val actual = DateUtil.formatDateToString(dateZeroHour,DateUtil.DD_MM_YYYY_HH_MM_SS)
        val expected = "31/05/2022 00:00:00"
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_formatDateToString(){
        val date = DateUtil.getDate(2022,5,31)
        val actual = DateUtil.formatDateToString(date,DateUtil.DD_MM_YYYY_HH_MM_SS)
        val expected = "31/05/2022 00:00:00"
        assertEquals(expected, actual)
    }


    @Test
    fun should_test_getMonth(){
        val date = DateUtil.getDateTime(2022,5,31,14,30,5)
        val actual = DateUtil.getMonth(date)
        val expected = 4
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_getYear(){
        val date = DateUtil.getDateTime(2022,5,31,14,30,5)
        val actual = DateUtil.getYear(date)
        val expected = 2022
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_formatToJson(){
        val date = DateUtil.getDateTime(2022,5,31,14,30,5,855)
        val actual = DateUtil.formatToJson(date)
        val expected = "2022-05-31T14:30:05.855-0300"
        assertEquals(expected, actual)
    }

    @Test
    fun should_test_getNameLast12Months(){
        val expectedMonths = listOf(
            "may", "apr", "mar", "fev", "dec", "nov",
            "oct", "sept", "aug", "july", "june", "may"
        )
        val expectedMonthsInt = listOf(
            4, 3, 2, 1, 11, 10, 9, 8, 7, 6, 5, 4
        )
        //val mock = Mockito.mock(DateUtil::class.java)
        Mockito.mockStatic(DateUtil::class.java).use {
            val date = DateUtil.getDateTime(2022,5,31,14,30,5)
            it.`when`<List<String>> { mock.getMonthListAsString() }.thenReturn(getMockMonthList())
            it.`when`<Months> { mock.getNameLast12Months(date) }.thenCallRealMethod()
            it.`when`<Int> { mock.getFromDate(date,Calendar.MONTH) }.thenCallRealMethod()
            val result = mock.getNameLast12Months(date = date)
            assertArrayEquals(expectedMonths.toTypedArray(),result.months.toTypedArray())
            assertArrayEquals(expectedMonthsInt.toTypedArray(),result.monthsInt.toTypedArray())
        }
    }

    @Test
    fun should_test_getNameLast7Days(){
        val expectedDays = listOf(
            "tue", "mon", "sun", "sat", "fri", "thu", "wed"
        )
        val expectedDaysAsInt = listOf(
            3, 2, 1, 7, 6, 5, 4
        )
        Mockito.mockStatic(DateUtil::class.java).use {
            val date = DateUtil.getDateTime(2022,5,31,14,30,5)
            it.`when`<List<String>> { mock.getDaysOfWeekListAsString() }.thenReturn(getMockDaysList())
            it.`when`<Days> { mock.getNameLast7Days(date) }.thenCallRealMethod()
            it.`when`<Int> { mock.getFromDate(date,Calendar.DAY_OF_WEEK) }.thenCallRealMethod()
            val days = mock.getNameLast7Days(date)
            assertArrayEquals(expectedDays.toTypedArray(),days.days.toTypedArray())
            assertArrayEquals(expectedDaysAsInt.toTypedArray(),days.daysInt.toTypedArray())
        }
    }

    @Ignore("See later how to mock parameter any at Mockito")
    @Test
    fun should_test_getWeekFromDate(){
        val date = DateUtil.getDateTime(2022,5,31,14,30,5)

        Mockito.mockStatic(DateUtil::class.java).use {
            it.`when`<Date> {mock.getCurrentDate()}.thenReturn(date)
            it.`when`<Period> {mock.getWeekFromDate(date)}.thenCallRealMethod()
            it.`when`<Int> {mock.getFromDate(date,Calendar.DAY_OF_WEEK)}.thenCallRealMethod()
            it.`when`<Period> {mock.getWeekFromDate(date)}.thenCallRealMethod()
            val period = mock.getWeekFromDate(date)
            val actualInitialDate = DateUtil.formatDateToString(period.initialDate,DateUtil.YYYY_MM_DD)
            val actualFinalDate = DateUtil.formatDateToString(period.finalDate,DateUtil.YYYY_MM_DD)
            val expectedInitialDate = "2022-05-31"
            val expectedFinalDate = "2022-05-31"
            assertEquals(expectedInitialDate,actualInitialDate)
            assertEquals(expectedFinalDate,actualFinalDate)
        }
    }

    private fun getMockMonthList(): List<String>{
        return listOf(
            "jan","fev","mar","apr","may","june",
            "july","aug","sept","oct","nov","dec"
        )
    }

    private fun getMockDaysList(): List<String>{
        return listOf(
            "sun","mon","tue","wed","thu","fri","sat"
        )
    }

    @Test
    fun should_test_getLastSevenPeriod(){
        val date = DateUtil.getDateTime(2022,6,2,20,30,5)
        val period = DateUtil.getLastSevenPeriod(date)
        val dateIniExpected = "26/05/2022"
        val dateFinExpected = "02/06/2022"
        val dateIniActual = formatDate(period.initialDate!!)
        val dateFinActual = formatDate(period.finalDate!!)
        assertEquals(dateIniExpected,dateIniActual)
        assertEquals(dateFinExpected,dateFinActual)
    }

    @Test
    fun should_test_getThisMonthPeriod(){
        val date = DateUtil.getDateTime(2022,6,2,20,30,5)
        val period = DateUtil.getThisMonthPeriod(date = date)
        val expectedInitDate = "01/06/2022"
        val expectedFinalDate = "30/06/2022"
        val actualInitDate = formatDate(period.initialDate!!)
        val actualFinalDate = formatDate(period.finalDate!!)
        assertEquals(expectedInitDate,actualInitDate)
        assertEquals(expectedFinalDate,actualFinalDate)
    }

    @Test
    fun should_test_getYesterdayPeriod(){
        val date = DateUtil.getDateTime(2022,6,2,20,30,5)
        val period = DateUtil.getYesterdayPeriod(date)
        val expectedInitDate = "01/06/2022"
        val expectedFinalDate = "01/06/2022"
        val actualInitDate = formatDate(period.initialDate!!)
        val actualFinalDate = formatDate(period.finalDate!!)
        assertEquals(expectedInitDate,actualInitDate)
        assertEquals(expectedFinalDate,actualFinalDate)
    }

    @Test
    fun should_test_getLastMonths(){
        val date = DateUtil.getDateTime(2022,6,2,20,30,5)
        val expectedInitDate = "01/04/2022"
        val expectedFinalDate = "30/06/2022"
        val period = DateUtil.getLastMonths(3,date)
        val actualInitDate = formatDate(period.initialDate!!)
        val actualFinalDate = formatDate(period.finalDate!!)
        assertEquals(expectedInitDate,actualInitDate)
        assertEquals(expectedFinalDate,actualFinalDate)
    }

    @Test
    fun test_getLastMonths_last_12(){
        val date = DateUtil.getDateTime(2022,6,2,20,30,5)
        val expectedInitDate = "01/07/2021"
        val expectedFinalDate = "30/06/2022"
        val period = DateUtil.getLastMonths(12,date)
        val actualInitDate = formatDate(period.initialDate!!)
        val actualFinalDate = formatDate(period.finalDate!!)
        assertEquals(expectedInitDate,actualInitDate)
        assertEquals(expectedFinalDate,actualFinalDate)
    }

    @Test
    fun test_getFirstDateOfTheYear(){
        val date = DateUtil.getDateTime(2022,6,2,20,30,5)
        val firstDate = DateUtil.getFirstDateOfTheYear(date)
        val expectedDate = "01/01/2022"
        val actualDate = formatDate(firstDate)
        assertEquals(expectedDate,actualDate)
    }

    @Test
    fun test(){
        val date = DateUtil.getDateTime(2022,6,2,20,30,5)
        val firstDate = DateUtil.getLastDateOfTheYear(date)
        val expectedDate = "31/12/2022"
        val actualDate = formatDate(firstDate)
        assertEquals(expectedDate,actualDate)
    }

    private fun formatDate(date: Date, format: String = DateUtil.DD_MM_YYYY) : String?{
        return DateUtil.formatDateToString(date,format )
    }
}