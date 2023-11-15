package christmas.domain

import christmas.data.ChristmasDiscountDayInfo
import christmas.util.StoreMessageConstants.ACTUAL_CHRISTMAS_DAY
import christmas.util.StoreMessageConstants.CHRISTMAS_DAY
import christmas.util.StoreMessageConstants.FIRST_NUM
import christmas.util.StoreMessageConstants.STAR_DAY
import christmas.util.StoreMessageConstants.ZERO_NUM

class TotalPriceDiscountCalculator(private val day: ChristmasDiscountDayInfo) {
    //총액 할인에 적용되는 금액
    //달력에 별이 붙어있는 경우와 크리스마스 이전의 경우 적용되는 총액 할인을 계산한다.
    //총 할인되는 금액을 반환해줌

    fun calculateDiscountSpecialDay(): Int {
        return when (day.dayType) {
            CHRISTMAS_DAY -> calculateChristMasDiscount()
            STAR_DAY -> calculateStarDayDiscount()
            else -> ZERO_NUM
        }
    }

    fun calculateNearChristmasDiscount(): Int {
        //1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
        return when {
            day.actualDay <= ACTUAL_CHRISTMAS_DAY -> STAR_DISCOUNT_NUM + (day.actualDay - FIRST_NUM) * 100
            else -> ZERO_NUM
        }
    }

    private fun calculateChristMasDiscount(): Int {
        //12월 25일에 해당하는 경우는 (별 할인이랑) + (크리스마스 디데이 할인 = 3,400원) 할인을 받음
        //총 할인되는 금액을 반환해줌
        return (STAR_DISCOUNT_NUM + CHRISTMAS_DAY_DISCOUNT_NUM)
    }

    private fun calculateStarDayDiscount(): Int = STAR_DISCOUNT_NUM  //일요일의 경우 할인

    companion object {
        private const val STAR_DISCOUNT_NUM = 1000
        private const val CHRISTMAS_DAY_DISCOUNT_NUM = 3400
    }

}
