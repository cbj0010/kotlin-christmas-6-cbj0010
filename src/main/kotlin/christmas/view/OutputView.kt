package christmas.view

import christmas.data.BenefitInfo
import christmas.data.Order

class OutputView {

    fun startMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
    }

    fun showOrderPrompt(){
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
    }

    fun displayMenu(menu: List<Order>) {
        println()
        println("<주문 메뉴>")
        for (selectMenus in menu) {
            println("${selectMenus.menuName} ${selectMenus.quantity}게")
        }
        println()
    }

    fun displayTotalPriceOrder(price: Int) {
        println("<할인 전 총주문 금액>")
        println(price.toDecimalFormatWithCurrency())
        println()
    }

    fun displayGiftEvent() {
        println("<증정 메뉴>")
        println("샴페인 1개")
        println()
    }

    fun displayGiftNoneEvent() {
        println("<증정 메뉴>")
        println("없음")
        println()
    }

    fun displayBenefits(discountInfo: BenefitInfo) {
        println("<혜택 내역>")
        println("크리스마스 디데이 할인: -${discountInfo.christmasNearDiscount.toDecimalFormatWithCurrency()}")
        println("평일 할인: -${discountInfo.menuDiscountDay.toDecimalFormatWithCurrency()}")
        println("특별 할인: -${discountInfo.specialDiscountDay.toDecimalFormatWithCurrency()}")
        println("증정 이벤트: -${discountInfo.giftEventMoney.toDecimalFormatWithCurrency()}")
        println()
        println("<총혜택 금액>")
        println("-${discountInfo.sumDiscountMoney.toDecimalFormatWithCurrency()}")
        println()
    }

    fun displayNoBenefits() {
        println("<혜택 내역>")
        println("없음")
        println()
        println("<총혜택 금액>")
        println("0원")
        println()
    }

    fun displayFinalMoney(finalPrice: Int) {
        println("<할인 후 예상 결제 금액>")
        println(finalPrice.toDecimalFormatWithCurrency())
        println()
    }

    fun displayChristMasBadge(totalDiscountMoney: Int) {
        println("<12월 이벤트 배지>")
        when {
            totalDiscountMoney >= 20000 -> println("산타")
            totalDiscountMoney >= 10000 -> println("트리")
            totalDiscountMoney >= 5000 -> println("별")
            else -> println("없음")
        }
    }

    fun Int.toDecimalFormatWithCurrency(): String {
        return String.format("%,d원", this)
    }
}