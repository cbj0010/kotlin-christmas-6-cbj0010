package christmas

import christmas.data.Order
import christmas.domain.*
import christmas.view.InputView
import christmas.view.OutputView


class FoodStore() {
    private val outputView = OutputView()
    fun start() {
        outputView.startMessage()
        val date = checkUserValidate()
        val menu = checkUserMenuValidate()
        showOrderInfo(date, menu)
    }

    private fun checkUserValidate(): Int {
        return try {
            InputDayValidator(InputView().inputUser()).isValidDate()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            checkUserValidate()
        }
    }

    private fun checkUserMenuValidate(): List<Order> {
        return try {
            outputView.showOrderPrompt()
            val parsedMenu: List<Order> = OrderParse().parseOrder(InputView().inputUser())
            val menu = MenuValidator(parsedMenu).isValidMenu()
            return menu
        } catch (e: IllegalArgumentException) {
            println(e.message)
            checkUserMenuValidate()
        }
    }

    private fun showOrderInfo(date: Int, menu: List<Order>) {
        val totalOrderPrice = CalculatorTotalPrice(menu).checkSumOrderMoney()
        val totalDiscountPrice = SaleController(date, menu)
        val christMasBadge =
            totalDiscountPrice.sumSaleMoney() + totalDiscountPrice.calculateGiftEventReward(totalOrderPrice)
        outputView.startMessage()

        outputView.displayMenu(menu)

        //할인 전 총 주문금액
        outputView.displayTotalPriceOrder(totalOrderPrice)
        //증정메뉴
        showGiftEvent(ValidateEventConditions(totalOrderPrice).checkValidGift())
        //혜택내역
        showDiscount(totalOrderPrice, totalDiscountPrice)
        //할인 후 예상 결제 금액
        outputView.displayFinalMoney(totalOrderPrice - totalDiscountPrice.sumSaleMoney())

        outputView.displayChristMasBadge(christMasBadge)
    }

    private fun showGiftEvent(isGift: Boolean) {
        when (isGift) {
            true -> outputView.displayGiftEvent()
            false -> outputView.displayGiftNoneEvent()
        }
    }

    private fun showDiscount(totalPrice: Int, totalDiscountPrice: SaleController) {
        val checkEvent = ValidateEventConditions(totalPrice)

        if (checkEvent.checkEventRequirements()) {
            totalDiscountPrice.saveDiscountInfo(totalPrice)
        } else {
            outputView.displayNoBenefits()
        }
    }
}
