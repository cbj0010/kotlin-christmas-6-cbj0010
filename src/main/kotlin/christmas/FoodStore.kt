package christmas

import christmas.domain.CalculatorTotalPrice
import christmas.domain.MenuValidator
import christmas.view.InputView
import christmas.view.OutputView


class FoodStore() {
    private val outputView = OutputView()
    fun start() {
        val inputUser = InputView().inputDay()
        val date = InputDayValidator(inputUser).isValidDate()   //검증된 날짜
        val menu = MenuValidator(InputView().inputDay()).isValidMenu()
        val totalOrderPrice = CalculatorTotalPrice(menu).checkSumOrderMoney()
        val totalDiscountPrice = SaleController(date, menu)
        outputView.startMessage()

        outputView.displayMenu(menu)

        //할인 전 총 주문금액
        outputView.displayTotalPriceOrder(totalOrderPrice)
        //증정메뉴
        showGiftEvent(ValidateEventConditions(totalOrderPrice).checkValidGift())
        //혜택
        showDiscount(totalOrderPrice, totalDiscountPrice)

        println("할인 후 예상 결제 금약")
        println(totalOrderPrice - totalDiscountPrice.sumSaleMoney())

        OutputView().displayChristMasBadge(totalDiscountPrice.sumSaleMoney())
    }

    fun showGiftEvent(isGift: Boolean) {
        val outputView = OutputView()
        when (isGift) {
            true -> outputView.displayGiftEvent()
            false -> outputView.displayGiftNoneEvent()
        }
    }

    private fun showDiscount(totalPrice: Int, totalDiscountPrice: SaleController) {
        //이게 true이면 sale이 들어감 false면 세일이 들어가지 않음을 보여줌
        val outputView = OutputView()
        val checkEvent = ValidateEventConditions(totalPrice)

        if (checkEvent.checkEventRequirements()) {
            totalDiscountPrice.saveDiscountInfo(totalPrice)
        } else {
            outputView.displayNoBenefits()
        }
    }
}
