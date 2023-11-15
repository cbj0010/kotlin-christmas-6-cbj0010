package christmas.domain

import christmas.data.BenefitInfo
import christmas.data.ChristmasDiscountDayInfo
import christmas.data.Order
import christmas.util.StoreMessageConstants.ACTUAL_CHRISTMAS_DAY
import christmas.util.StoreMessageConstants.CHRISTMAS_DAY
import christmas.util.StoreMessageConstants.MINIMUM_GIFT_PRICE
import christmas.util.StoreMessageConstants.NONE
import christmas.util.StoreMessageConstants.STAR_DAY
import christmas.util.StoreMessageConstants.WEEKDAY
import christmas.util.StoreMessageConstants.WEEKEND_DAY
import christmas.util.StoreMessageConstants.ZERO_NUM
import christmas.view.OutputView

class SaleController(private val inputDay: Int, private val selectMenus: List<Order>) {

    //입력한 요일을 판단하는 함수 , 검증 된 날짜와 검증된 Order(name,count)형식의 메뉴리스트를 받는다.
    fun sumSaleMoney(): Int {
        val totalPriceDiscountCalculator = TotalPriceDiscountCalculator(checkDayForSpecialDiscount())
        val menuDiscountDay = MenuSaleCalculator(checkDayForMenuDiscount()).calculateDiscountRate(selectMenus)
        val christmasNearDiscount =
            totalPriceDiscountCalculator.calculateNearChristmasDiscount()
        val specialDiscountDay =
            totalPriceDiscountCalculator.calculateDiscountSpecialDay()
        return menuDiscountDay + christmasNearDiscount + specialDiscountDay
    }

    fun saveDiscountInfo(totalPrice: Int) {
        val menuDiscountDay = MenuSaleCalculator(checkDayForMenuDiscount()).calculateDiscountRate(selectMenus)
        val christmasNearDiscount =
            TotalPriceDiscountCalculator(checkDayForSpecialDiscount()).calculateNearChristmasDiscount()
        val specialDiscountDay =
            TotalPriceDiscountCalculator(checkDayForSpecialDiscount()).calculateDiscountSpecialDay()
        val giftEventDiscount = calculateGiftEventReward(totalPrice)
        val sumDiscount = menuDiscountDay + christmasNearDiscount + specialDiscountDay + giftEventDiscount
        OutputView().displayBenefits(
            BenefitInfo(
                christmasNearDiscount,
                menuDiscountDay,
                specialDiscountDay,
                giftEventDiscount,
                sumDiscount
            )
        )
    }

    fun calculateGiftEventReward(totalPrice: Int): Int {
        return when {
            (totalPrice > MINIMUM_GIFT_PRICE) -> GIFT_PRICE
            else -> ZERO_NUM
        }
    }

    private fun checkDayForSpecialDiscount(): ChristmasDiscountDayInfo {
        return when {
            inputDay % SEVEN_DAY == 3 -> ChristmasDiscountDayInfo(STAR_DAY, inputDay)
            inputDay == ACTUAL_CHRISTMAS_DAY -> ChristmasDiscountDayInfo(CHRISTMAS_DAY, ACTUAL_CHRISTMAS_DAY)
            else -> ChristmasDiscountDayInfo(NONE, inputDay)
        }
    }

    private fun checkDayForMenuDiscount(): String {
        return when (inputDay % SEVEN_DAY) {
            1, 2 -> WEEKEND_DAY
            else -> WEEKDAY
        }
    }


    companion object {
        private const val SEVEN_DAY = 7
        private const val GIFT_PRICE = 25000

    }
}