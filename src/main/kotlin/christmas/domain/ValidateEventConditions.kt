package christmas.domain

import christmas.util.StoreMessageConstants.MINIMUM_GIFT_PRICE
import christmas.util.StoreMessageConstants.MIN_ORDER_AMOUNT_FOR_EVENT

class ValidateEventConditions(private val totalPrice: Int) {
    //이벤트 혜택을 받을 수 있는지 판별
    /*
    고객에게 적용된 이벤트 내역만 보여 주세요.
적용된 이벤트가 하나도 없다면 혜택 내역 "없음"으로 보여 주세요.
증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
     */
    fun checkEventRequirements(): Boolean {
        return when {
            totalPrice >= MIN_ORDER_AMOUNT_FOR_EVENT -> true//eventApplied()
            else -> false//eventNotApplied()
        }
    }

    fun checkValidGift(): Boolean {
        return totalPrice >= MINIMUM_GIFT_PRICE
    }

}
