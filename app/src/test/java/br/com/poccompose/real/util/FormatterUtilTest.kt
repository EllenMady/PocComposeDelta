package br.com.poccompose.real.util

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import java.util.*

class FormatterUtilTest {

    @Test
    fun test_formaterToMoneyWithSign(){
        val valueUS = FormatterUtil.formaterToMoneyWithSign(10.5, Locale.US)
        val valuePt = FormatterUtil.formaterToMoneyWithSign(10.5, Locale("pt","BR"))
        val valueES = FormatterUtil.formaterToMoneyWithSign(10.5, Locale("es","ES"))
        val valueEnUS = FormatterUtil.formaterToMoneyWithSign(10.5, Locale("en","US"))
        assertEquals("$10.50",valueUS)
        assertEquals("R$ 10,50",valuePt)
        assertEquals("10,50 €",valueES)
        assertEquals("$10.50",valueEnUS)
        print(valuePt)
    }

    @Test
    fun test_isNotExistDecimalPlacesMoney(){
        val isDecimal = FormatterUtil.isNotExistDecimalPlacesMoney()
        assertFalse(isDecimal)
    }

    @Test
    fun test_removeSpecialCharsFromString(){
        val textClean = FormatterUtil.removeSpecialCharsFromString("CCBDVFSéãâç")
        assertEquals("CCBDVFSeaac",textClean)
    }

    @Test
    fun test_formaterPercentTextField(){
        val actual = FormatterUtil.formaterPercentTextField("1")
        val expected = "0.01"
        assertEquals(expected, actual)
        val actual1 = FormatterUtil.formaterPercentTextField("10")
        val expected1 = "0.10"
        assertEquals(expected1, actual1)
        val actual2 = FormatterUtil.formaterPercentTextField("100")
        val expected2 = "1.00"
        assertEquals(expected2, actual2)
        val actual3 = FormatterUtil.formaterPercentTextField("1.000")
        val expected3 = "10.00"
        assertEquals(expected3, actual3)

    }

    @Test
    fun test_formaterToMoneyWithSignWithoutCents(){
        val actual = FormatterUtil.formaterToMoneyWithSignWithoutCents(10.55,Locale.US)
        val expected = "$10"
        assertEquals(expected, actual)
    }

    @Test
    fun test_formaterToMoney(){
        val actual = FormatterUtil.formaterToMoney(10.5)
        val expected = "10.50"
        assertEquals(expected, actual)
        val actual2 = FormatterUtil.formaterToMoney(10.0)
        val expected2 = "10.00"
        assertEquals(expected2, actual2)
    }

    @Test
    fun test_formaterToPercentInt(){
        val actual = FormatterUtil.formaterToPercentInt(11.66)
        val expected = "11"
        assertEquals(expected, actual)
    }
}