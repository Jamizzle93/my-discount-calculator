package com.mysticwater.mydiscountcalculator.calculatediscount

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mysticwater.mydiscountcalculator.R
import com.mysticwater.mydiscountcalculator.calculatediscount.views.MoneyEditText
import com.mysticwater.mydiscountcalculator.calculatediscount.views.PercentEditText
import com.mysticwater.mydiscountcalculator.util.MoneyUtils
import kotlin.math.roundToLong

class CalculateDiscountFragment : Fragment() {

    private lateinit var moneyEditText: MoneyEditText
    private lateinit var percentEditText: PercentEditText
    private lateinit var calculatedPriceText: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculate, container, false)

        calculatedPriceText = view.findViewById(R.id.text_calculated_price)
        calculatedPriceText.text = formatPrice(0)

        setupFab(view)
        setupEditTexts(view)

        return view
    }

    private fun setupFab(view: View) {
        view.findViewById<FloatingActionButton>(R.id.fab_calculate).apply {
            setOnClickListener {
                val newPriceView = view.findViewById<TextView>(R.id.text_calculated_price)

                newPriceView.text = getNewPrice()
            }
        }
    }

    private fun setupEditTexts(view: View) {
        moneyEditText = view.findViewById(R.id.input_price)
        percentEditText = view.findViewById(R.id.input_percentage)
    }

    private fun getNewPrice(): String {
        val moneyText = moneyEditText.text.toString()
        val percentText = percentEditText.text.toString()

        if (!TextUtils.isEmpty(moneyText) && !TextUtils.isEmpty(percentText)) {
            val money = MoneyUtils.getSmallestDenomination(moneyText)
            val percent = Integer.parseInt(percentText)
            val newPrice = calculatePrice(money, percent)

            return formatPrice(newPrice)
        }

        return "0.00"
    }

    private fun calculatePrice(money: Long, percent: Int): Long {
        val percentageDecimal = 1 - (percent.toDouble() / 100)

        return (money * percentageDecimal).roundToLong()
    }

    private fun formatPrice(price: Long): String {
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

    companion object {
        fun newInstance() = CalculateDiscountFragment()
    }

}