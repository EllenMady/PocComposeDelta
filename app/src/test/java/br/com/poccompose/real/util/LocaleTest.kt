package br.com.poccompose.real.util

import org.junit.Test
import java.text.NumberFormat
import java.util.*

class LocaleTest {

    @Test
    fun test(){
        val ddd = "10.34"
        val number = NumberFormat.getInstance(Locale.getDefault())
        val symbol = number.currency.symbol
        print(symbol)
        val ddd2 = number.parse(ddd)
        print(number)

    }
}