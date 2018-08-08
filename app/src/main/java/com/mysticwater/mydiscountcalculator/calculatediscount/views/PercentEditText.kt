package com.mysticwater.mydiscountcalculator.calculatediscount.views

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet

class PercentEditText(context: Context?, attrs: AttributeSet?) : AppCompatEditText(context,
        attrs), TextWatcher {

    var currentText = ""

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        val newText = text.toString()
        if (isValidText(newText)) {

            if (isValidPercentage(newText)) {
                currentText = newText
            } else {
                setText(currentText)
                setSelection(currentText.length)
            }

        }
    }

    private fun isValidPercentage(text: String): Boolean {
        val percentageValue = Integer.parseInt(text)
        return percentageValue <= 100
    }

    private fun isValidText(text: String): Boolean {
        return !TextUtils.isEmpty(text) && text != currentText
    }


}
