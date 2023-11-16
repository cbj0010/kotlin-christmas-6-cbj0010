package christmas.domain

import christmas.util.StoreMessageConstants.MINIMUM_GIFT_PRICE
import christmas.util.StoreMessageConstants.MIN_ORDER_AMOUNT_FOR_EVENT

class ValidateEventConditions(private val totalPrice: Int) {

    fun checkEventRequirements(): Boolean {
        return when {
            totalPrice >= MIN_ORDER_AMOUNT_FOR_EVENT -> true
            else -> false
        }
    }

    fun checkValidGift(): Boolean {
        return totalPrice >= MINIMUM_GIFT_PRICE
    }

}
