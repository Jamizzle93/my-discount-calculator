package com.mysticwater.mydiscountcalculator.util

class MoneyUtils {

    companion object {
        fun getSmallestDenomination(currencyText: String): Long {
            val numbersRegex = Regex("[^0-9 ]")
            val numbers = numbersRegex.replace(currencyText, "")

            return numbers.toLong()
        }
    }
}