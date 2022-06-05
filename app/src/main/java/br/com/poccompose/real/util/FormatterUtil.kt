package br.com.poccompose.real.util

import br.com.poccompose.real.extensions.truncate
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

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

    //TODO o que é pra fazer aqui
    fun formaterToMoneyWithSignWithoutCents(value: Double): String{  // $10 formatar o valor de acordo com o locale configurado
        val decimalFormat = DecimalFormatSymbols(Locale.getDefault())
        with(getCurrencyFormat()){
            roundingMode = RoundingMode.DOWN
            maximumFractionDigits = 0
            return format(value).replace(decimalFormat.decimalSeparator.toString(),"",true)
        }
    }

    //TODO ?
    fun formaterPercentTextField(str:String): String {
        if(str.trim() == ""){
            return "0.00"
        }
        val auxInt:Int = str.replace(".", "").toInt()
        val auxArray = arrayOf(auxInt.toString())
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
            return  auxArray[0] + "." + auxArray[1] + auxArray[2]
        }

        if(auxInt < 10000){
            return  auxArray[0] + auxArray[1] + "." + auxArray[2] + auxArray[3]
        }

        if(auxInt < 100000){
            return  auxArray[0] + auxArray[1] + auxArray[2] + "." + auxArray[3] + auxArray[4]
        }
        return auxArray[0] + auxArray[1] + auxArray[2] + auxArray[3] + "." + auxArray[4] + auxArray[5]
    }

    fun formaterCellPhone(phone:String): String{
        //TODO
        return phone
    }

    fun removeSpecialCharsFromString(text: String) : String { // retira caracteres especiais e acentos das palavras
        val regex = Regex("[^A-Za-z0-9 ]")
        return regex.replace(text,"")
    }

}