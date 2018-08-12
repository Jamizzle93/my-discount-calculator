package com.mysticwater.mydiscountcalculator.util

class MoneyUtils {

    companion object {
        fun getSmallestDenomination(moneyText: String): Long {
            val fullMoneyString = formatStringToFullMoney(moneyText)

            val numbersRegex = Regex("[^0-9 ]")
            val numbers = numbersRegex.replace(fullMoneyString, "")

            return numbers.toLong()
        }

        private fun formatStringToFullMoney(moneyString: String): String {
            val decimalIndex = moneyString.indexOf(".")
            return if (decimalIndex == -1 || decimalIndex == (moneyString.length - 1)) {
                "$moneyString.00"
            } else {
                val afterDecimalStr = moneyString.substring(decimalIndex + 1)
                if (afterDecimalStr.length == 1) {
                    moneyString + "0"
                } else {
                    moneyString
                }
            }
        }
    }
}