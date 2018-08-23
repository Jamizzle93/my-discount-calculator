package com.mysticwater.mydiscountcalculator.util

import java.util.*
import kotlin.math.roundToLong

class MoneyUtils {

    companion object {

        private var CURRENCY: Currency? = null

        fun getCurrencyInstance(): Currency {
            if (CURRENCY == null) {
                synchronized(MoneyUtils::javaClass) {
                    CURRENCY = Currency.getInstance(Locale.getDefault())
                }
            }

            return CURRENCY!!
        }

        fun calculateNewPrice(moneyString: String, percentString: String): String {
            val money = getSmallestDenomination(moneyString)
            val percent = Integer.parseInt(percentString)

            val newPrice = calculatePrice(money, percent)

            return formatPrice(newPrice)
        }

        private fun getSmallestDenomination(moneyText: String): Long {
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

        private fun calculatePrice(money: Long, percent: Int): Long {
            val percentageDecimal = 1 - (percent.toDouble() / 100)

            return (money * percentageDecimal).roundToLong()
        }

        fun formatPrice(price: Long): String {
            val moneyString = longToMoneyString(price)
            val symbol = MoneyUtils.getCurrencyInstance().symbol

            return symbol + moneyString
        }

        private fun longToMoneyString(money: Long): String {
            val moneyStr = money.toString()
            return if (moneyStr.length > 2) {
                moneyStr.substring(0, moneyStr.length - 2) +
                        "." +
                        moneyStr.substring(moneyStr.length - 2)
            } else {
                if (moneyStr.length == 2) {

                    "0.$moneyStr"
                } else {
                    "0.0$moneyStr"
                }
            }
        }

    }
}