package christmas.domain

import christmas.util.ErrorMessage

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
        require(inputDay.toInt() in 1..31) {
            ErrorMessage.ERROR_INPUT_DAY.getMessage()
        }
    }

}