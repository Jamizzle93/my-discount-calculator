package com.mysticwater.mydiscountcalculator.calculatediscount.views

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.text.Spanned
import android.text.method.DigitsKeyListener
import android.util.AttributeSet

class MoneyEditText(context: Context?, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    init {
        setFilters()
    }

    private fun setFilters() {
        this.filters = arrayOf(MoneyInputFilter)
    }

    object MoneyInputFilter : DigitsKeyListener(false, true) {

        private const val maxDigitsAfterDecimal = 2

        override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart:
        Int, dend: Int): CharSequence? {

            val builder = StringBuilder(dest)
            builder.insert(dstart, source)

            val enteredText = builder.toString()

            val decimalIndex = enteredText.indexOf(".")
            if (decimalIndex > 0) {
                val afterDecimalText = enteredText.substring(decimalIndex + 1)
                if (afterDecimalText.length > maxDigitsAfterDecimal) {
                    return ""
                }
            }

            return super.filter(source, start, end, dest, dstart, dend)
        }
    }
}
