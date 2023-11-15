package christmas.domain

import christmas.data.ChristmasDiscountDayInfo
import christmas.domain.SaleController.Companion.CHRISTMAS_DAY
import christmas.domain.SaleController.Companion.STAR_DAY

class TotalPriceDiscountCalculator(private val day: ChristmasDiscountDayInfo) {
    //총액 할인에 적용되는 금액
    //달력에 별이 붙어있는 경우와 크리스마스 이전의 경우 적용되는 총액 할인을 계산한다.
    //총 할인되는 금액을 반환해줌

    fun calculateDiscountSpecialDay(): Int {
        return when (day.dayType) {
            CHRISTMAS_DAY -> calculateChristMasDiscount()
            STAR_DAY -> calculateStarDayDiscount()
            else -> 0
        }
    }

    fun calculateNearChristmasDiscount(): Int {
        //1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
        return when {
            day.actualDay <= 25 -> 1000 + (day.actualDay - 1) * 100
            else -> 0
        }
    }

    private fun calculateChristMasDiscount(): Int {
        //12월 25일에 해당하는 경우는 (별 할인이랑) + (크리스마스 디데이 할인 = 3,400원) 할인을 받음
        //총 할인되는 금액을 반환해줌
        return (1000 + 3400)
    }

    private fun calculateStarDayDiscount(): Int = 1000  //일요일의 경우 할인

}
