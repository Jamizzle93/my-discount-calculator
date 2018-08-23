package com.mysticwater.mydiscountcalculator.calculatediscount

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
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
        calculatedPriceText.text = MoneyUtils.formatPrice(0)

        setupFab(view)
        setupEditTexts(view)

        loadAd(view)

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
            return MoneyUtils.calculateNewPrice(moneyText, percentText);
        }

        return MoneyUtils.formatPrice(0)
    }

    private fun loadAd(view: View) {
        val adView = view.findViewById<AdView>(R.id.view_ad)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    companion object {
        fun newInstance() = CalculateDiscountFragment()
    }

}