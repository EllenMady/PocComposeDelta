package br.com.poccompose.real.util

import br.com.poccompose.real.extensions.truncate
import java.math.RoundingMode
import java.text.DecimalFormatSymbols
import java.text.Normalizer
import java.text.NumberFormat
import java.util.*

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()
object FormatterUtil {

    fun formaterToMoney(value: Double): String{ // 10.00
        val valueTrunc = value.truncate(places= 2)
        val split = valueTrunc.toString().split(".")

        if(split.size == 1) {
            return "$valueTrunc.00"
        }

        if (split.size == 2){
            val splitDecimais = split[1]
            return if(splitDecimais.length == 1){
                "${valueTrunc}0"
            }else{
                valueTrunc.toString()
            }
        }
        return "0.00"
    }

    fun formaterToPercentInt(value:Double): String{ // 1
        val valueTrunc = value.truncate(2)
        val split = valueTrunc.toString().split(".")
        return split[0]
    }

    fun formaterToMoneyWithSign(value: Double, locale: Locale = Locale.getDefault()): String{ // $10.00 formatar o valor de acordo com o locale configurado
        val formatter = getCurrencyFormat(locale)
        return formatter.let {
            it.maximumFractionDigits = 2
            it.format(value).replace(" "," ")
        }
    }

    private fun getCurrencyFormat(locale: Locale = Locale.getDefault()): NumberFormat {
        return NumberFormat.getCurrencyInstance(locale)
    }

    fun isNotExistDecimalPlacesMoney(): Boolean{
        val formatter = getCurrencyFormat()
        return formatter.maximumFractionDigits == 0
    }

    fun formaterToMoneyWithSignWithoutCents(value: Double, locale: Locale = Locale.getDefault()): String{  // $10 formatar o valor de acordo com o locale configurado
        val decimalFormat = DecimalFormatSymbols(locale)
        with(getCurrencyFormat()){
            roundingMode = RoundingMode.DOWN
            maximumFractionDigits = 0
            return format(value).replace(decimalFormat.decimalSeparator.toString(),"",true)
        }
    }

    fun formaterPercentTextField(str:String): String {
        if(str.trim() == ""){
            return "0.00"
        }
        val auxInt:Int = str.replace(".", "").toInt()
        val auxArray = auxInt.toString().toCharArray()
        if (auxInt == 0) {
            return "0.00"
        }
        if(auxInt < 10){
            return "0.0$auxInt"
        }
        if(auxInt < 100){
            return "0.$auxInt"
        }

        if(auxInt < 1000){
            return  "${auxArray[0]}.${auxArray[1]}${auxArray[2]}"
        }

        if(auxInt < 10000){
            return  "${auxArray[0]}${auxArray[1]}.${auxArray[2]}${auxArray[3]}"
        }

        if(auxInt < 100000){
            return "${auxArray[0]}${auxArray[1]}${auxArray[2]}.${auxArray[3]}${auxArray[4]}"
        }
        return "${auxArray[0]}${auxArray[1]}${auxArray[2]}${auxArray[3]}.${auxArray[4]}${auxArray[5]}"
    }

    fun formaterCellPhone(phone:String): String{
        //TODO
        return phone
    }

    fun removeSpecialCharsFromString(text: String) : String { // retira caracteres especiais e acentos das palavras
        return text.unaccent()
    }

}

fun CharSequence.unaccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}