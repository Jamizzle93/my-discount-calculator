package com.mysticwater.mydiscountcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import com.mysticwater.mydiscountcalculator.calculatediscount.CalculateDiscountFragment
import com.mysticwater.mydiscountcalculator.util.replaceFragmentInActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, getString(R.string.admob_app_id))

        setSupportActionBar(toolbar)

        setupFragment()
    }

    private fun setupFragment() {
        val fragment = findOrCreateViewFragment()
        replaceFragmentInActivity(fragment, R.id.frame_content)
    }

    private fun findOrCreateViewFragment() =
            supportFragmentManager.findFragmentById(R.id.frame_content)
                    ?: CalculateDiscountFragment.newInstance()

}
