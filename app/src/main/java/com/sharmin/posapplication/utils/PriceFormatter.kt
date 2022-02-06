package com.sharmin.posapplication.utils

import java.text.DecimalFormat

object PriceFormatter {

    private val df = DecimalFormat("#.##")

    fun apply(price: Int): String {
        val priceString = df.format(price)
        return "${priceString}B"
    }
}