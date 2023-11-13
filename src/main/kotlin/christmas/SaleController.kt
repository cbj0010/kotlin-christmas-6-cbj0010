package christmas

import christmas.data.ChristmasDiscountDayInfo
import christmas.data.Order
import christmas.domain.MenuSaleCalculator
import christmas.domain.TotalPriceDiscountCalculator

class SaleController(private val inputDay: Int, private val menuList: List<Order>) {

    //입력한 요일을 판단하는 함수 , 검증 된 날짜와 검증된 Order(name,count)형식의 메뉴리스트를 받는다.
    fun sumSaleMoney() {
        val menuDiscountDay = checkDayForMenuDiscount()
        val sumDiscountDay = checkDayForSpecialDiscount()
        MenuSaleCalculator(menuDiscountDay).calculateDiscountRate(menuList)
        TotalPriceDiscountCalculator(sumDiscountDay).calculateDiscountSpecialDay()
    }

    private fun checkDayForSpecialDiscount(): ChristmasDiscountDayInfo {
        return when {
            inputDay % 7 == 3 -> ChristmasDiscountDayInfo(STAR_DAY, inputDay)
            inputDay == 25 -> ChristmasDiscountDayInfo(CHRISTMAS_DAY, 25)
            else -> ChristmasDiscountDayInfo(NONE, 0)
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