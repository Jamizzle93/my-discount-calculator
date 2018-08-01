package com.mysticwater.mydiscountcalculator.calculatediscount.views

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.mysticwater.mydiscountcalculator.util.MoneyUtils

class MoneyEditText(context: Context?, attrs: AttributeSet?) :
        AppCompatEditText(context, attrs), TextWatcher {

    var currentText = "";

    init {
        addTextChangedListener(this)
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        val textString = text.toString();

        if (textString != currentText) {
            this.removeTextChangedListener(this);

            val monetaryValue = MoneyUtils.getSmallestDenomination(text.toString())
            println(monetaryValue)

            this.addTextChangedListener(this)
        }
    }

}
