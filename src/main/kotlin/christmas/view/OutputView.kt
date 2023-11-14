package christmas.view

import christmas.data.BenefitInfo

class OutputView {

    fun displayBenefits(discountInfo: BenefitInfo) {
        println("<혜택 내역>")
        println("크리스마스 디데이 할인: -${discountInfo.christmasNearDiscount}")
        println("평일 할인: -${discountInfo.menuDiscountDay}")
        println("특별 할인: -${discountInfo.specialDiscountDay}")
        println("증정 이벤트: - 아직 값 계산안함")
        println()
        println("<총혜택 금액>")
        println("-$discountInfo.sumDiscountMoney")
    }

    fun displayNoBenefits() {
        println("<혜택 내역>")
        println("없음")
        println()
        println("<총혜택 금액>")
        println("0원")
        println()
    }

    fun displayMenu() {

    }
}