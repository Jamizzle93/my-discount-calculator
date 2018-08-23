package com.mysticwater.mydiscountcalculator

import com.mysticwater.mydiscountcalculator.util.MoneyUtils
import org.junit.Test

import org.junit.Assert.*


class MoneyUtilsTest {

    @Test
    fun moneyUtils_ZeroValues_ReturnsZero() {

        val money = "0"
        val percent = "0"

        val currencySymbol = MoneyUtils.getCurrencyInstance().symbol
        val expected = currencySymbol + "0.00"
        val actual = MoneyUtils.calculateNewPrice(money, percent)

        assertEquals(expected, actual)
    }

}
