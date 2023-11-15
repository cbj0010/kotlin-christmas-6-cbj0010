package christmas.domain

import christmas.data.BenefitInfo
import christmas.data.ChristmasDiscountDayInfo
import christmas.data.Order
import christmas.view.OutputView

class SaleController(private val inputDay: Int, private val menuList: List<Order>) {

    //입력한 요일을 판단하는 함수 , 검증 된 날짜와 검증된 Order(name,count)형식의 메뉴리스트를 받는다.
    fun sumSaleMoney(): Int {
        val totalPriceDiscountCalculator = TotalPriceDiscountCalculator(checkDayForSpecialDiscount())
        val menuDiscountDay = MenuSaleCalculator(checkDayForMenuDiscount()).calculateDiscountRate(menuList)
        val christmasNearDiscount =
            totalPriceDiscountCalculator.calculateNearChristmasDiscount()
        val specialDiscountDay =
            totalPriceDiscountCalculator.calculateDiscountSpecialDay()
        println("$menuDiscountDay + $christmasNearDiscount + $specialDiscountDay")
        return menuDiscountDay + christmasNearDiscount + specialDiscountDay
    }

    fun saveDiscountInfo(totalPrice: Int) {
        val menuDiscountDay = MenuSaleCalculator(checkDayForMenuDiscount()).calculateDiscountRate(menuList)
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
            (totalPrice > 120000) -> 25000
            else -> 0
        }
    }

    private fun checkDayForSpecialDiscount(): ChristmasDiscountDayInfo {
        return when {
            inputDay % 7 == 3 -> ChristmasDiscountDayInfo(STAR_DAY, inputDay)
            inputDay == 25 -> ChristmasDiscountDayInfo(CHRISTMAS_DAY, 25)
            else -> ChristmasDiscountDayInfo(NONE, inputDay)
        }
    }

    private fun checkDayForMenuDiscount(): String {
        return when (inputDay % 7) {
            1, 2 -> "주말"
            else -> "평일"
        }
    }


    companion object {
        const val STAR_DAY = "STAR_DAY"
        const val CHRISTMAS_DAY = "CHRISTMAS_DAY"
        const val NONE = "NONE"
    }
}