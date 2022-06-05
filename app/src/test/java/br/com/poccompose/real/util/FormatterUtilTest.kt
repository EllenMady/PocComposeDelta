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
        val textClean = FormatterUtil.removeSpecialCharsFromString(".,,CCBDVFSéãâ")
        assertEquals("CCBDVFS",textClean)
    }

    @Test
    fun test_formaterPercentTextField(){
        val fff = FormatterUtil.formaterPercentTextField("452.56")
        print(fff)
    }
}