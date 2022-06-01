package br.com.poccompose.real.util

import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito

class DateUtilTest {

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
    fun test(){
        Mockito.mockStatic(DateUtil::class.java).use {
            it.`when`<List<String>> { DateUtil.getMonthListAsString() }.thenReturn(getMockMonthList())
            //DateUtil.getNameLast12Months()
        }
    }

    private fun getMockMonthList(): List<String>{
        return listOf(
            "jan","fev","mar","apr","may","june",
            "july","aug","sept","oct","nov","dec"
        )
    }


}