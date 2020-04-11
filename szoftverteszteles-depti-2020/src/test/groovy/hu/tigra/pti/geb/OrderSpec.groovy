package hu.tigra.pti.geb

import hu.tigra.pti.geb.page.*

class OrderSpec extends BaseSpec {

    def 'Legolcsóbb termék megrendelése'() {
        given: 'Bejelentkezek az oldalra'
        login(Constant.USERS.HELYES_FELHASZNALO)
        waitFor { at MyAccountPage }

        when: 'Átnavigálok a főoldalra'
        def mainPage = to MainPage

        then: 'Megjelennek a termékek'
        mainPage.products.size() != 0

        when: 'A kurzort a legolcsóbb fölé viszem és rákattintok az "Add to cart" gombra'
        def cheapestProduct = mainPage.products.min { product -> product.price }
        interact {
            moveToElement(cheapestProduct.navigator)
        }
        cheapestProduct.addToCart.click()

        // Órai feladat
        then: 'Megjelenik a kosár popup és a "Product successfully added to your shopping cart" üzenet'
        def productPopUp = waitFor { at ProductPopUpPage }
        productPopUp.message.text() == "Product successfully added to your shopping cart"

        when: 'Rákattintok a "Proceed to checkout" gombra'
        productPopUp.proceed.click()

        then: 'Megjelenik a "SHOPPING-CART SUMMARY" fejlécű oldal'
        def orderPage = waitFor { at OrderPage }
        orderPage.header.text().startsWith("SHOPPING-CART SUMMARY")
        // 3. Házi feladat
        when: 'Rákattintok a plusz gombra az első sorban'
        orderPage.summaryFirstRow.plusButton.click()

        then: 'A mennyiség 2-re változik'
        waitFor { orderPage.summaryFirstRow.quantity == 2 }

        when: 'Rákattintok a "Proceed to checkout" gombra'
        orderPage.paymentCheckButton.click()

        then: 'Megjelenik az "ADDRESSES" fejlécű oldal'
        def addressPage = waitFor { at AddressPage }
        addressPage.header.text() == "ADDRESSES"

        when: 'Rákattintok a "Proceed to checkout" gombra'
        addressPage.paymentCheckButton.click()

        then: 'Megjelenik a "SHIPPING" fejlécű oldal'
        def shippingPage = waitFor { at ShippingPage }
        shippingPage.header.text() == "SHIPPING"

        when: 'Bepipálom a checkboxot és rákattintok a "Proceed to checkout" gombra'
        shippingPage.check.check()
        shippingPage.paymentCheckOut.click()

        then: 'Megjelenik a "PLEASE CHOOSE YOUR PAYMENT METHOD" fejlécű oldal'
        def paymentPage = waitFor { at PaymentPage }
        paymentPage.header.text() == "PLEASE CHOOSE YOUR PAYMENT METHOD"

        when: 'Kiválasztom a csekk fizetési módot'
        paymentPage.csekkPayment.click()

        then: 'Megjelenik az "ORDER SUMMARY" fejlécű oldal'
        def orderSummaryPage = waitFor { at OrderSummaryPage }
        orderSummaryPage.header.text() == "ORDER SUMMARY"

        when: 'Rákattintok az "I confirm my order" gombra'
        orderSummaryPage.submitPayment.click()

        then: 'Megjelenik a sikeres rendelés üzenete: "Your order on My Store is complete."'
        def success = waitFor {orderSummaryPage.success}
        success.text()=="Your order on My Store is complete."
    }
}
