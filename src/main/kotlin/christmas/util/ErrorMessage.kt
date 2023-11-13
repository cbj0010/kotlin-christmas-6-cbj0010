package christmas.util

enum class ErrorMessage(private val message: String) {
    ERROR_MENU_INPUT("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    fun getMessage(): String = "[ERROR] $message"
}