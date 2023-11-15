package christmas.domain

import christmas.data.ChristmasDiscountDayInfo
import christmas.util.StoreMessageConstants.ACTUAL_CHRISTMAS_DAY
import christmas.util.StoreMessageConstants.CHRISTMAS_DAY
import christmas.util.StoreMessageConstants.FIRST_NUM
import christmas.util.StoreMessageConstants.STAR_DAY
import christmas.util.StoreMessageConstants.ZERO_NUM

class TotalPriceDiscountCalculator(private val day: ChristmasDiscountDayInfo) {

    fun calculateDiscountSpecialDay(): Int {
        return when (day.dayType) {
            CHRISTMAS_DAY -> calculateChristMasDiscount()
            STAR_DAY -> calculateStarDayDiscount()
            else -> ZERO_NUM
        }
    }

    fun calculateNearChristmasDiscount(): Int {
        return when {
            day.actualDay <= ACTUAL_CHRISTMAS_DAY -> STAR_DISCOUNT_NUM + (day.actualDay - FIRST_NUM) * 100
            else -> ZERO_NUM
        }
    }

    private fun calculateChristMasDiscount(): Int {

        return (STAR_DISCOUNT_NUM + CHRISTMAS_DAY_DISCOUNT_NUM)
    }

    private fun calculateStarDayDiscount(): Int = STAR_DISCOUNT_NUM

    companion object {
        private const val STAR_DISCOUNT_NUM = 1000
        private const val CHRISTMAS_DAY_DISCOUNT_NUM = 3400
    }

}
