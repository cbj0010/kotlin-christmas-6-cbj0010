package christmas.domain

import christmas.util.ErrorMessage
import christmas.util.StoreMessageConstants.END_DAY
import christmas.util.StoreMessageConstants.FIRST_NUM

class InputDayValidator(private val inputDay: String) {

    fun isValidDate(): Int {
        validateNatural()
        rangeDate()
        return inputDay.toInt()
    }

    private fun validateNatural() =
        require(inputDay.toIntOrNull() != null) {
            ErrorMessage.ERROR_INPUT_DAY.getMessage()
        }

    private fun rangeDate() {
        require(inputDay.toInt() in FIRST_NUM..END_DAY) {
            ErrorMessage.ERROR_INPUT_DAY.getMessage()
        }
    }

}
