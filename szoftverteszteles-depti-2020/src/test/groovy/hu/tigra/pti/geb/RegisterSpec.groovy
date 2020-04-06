package hu.tigra.pti.geb

import hu.tigra.pti.geb.page.LoginPage
import hu.tigra.pti.geb.page.RegisterPage

class RegisterSpec extends BaseSpec {

    def 'Regisztráció'() {
        given: 'Bejelentkező felületre navigálok'
        def loginPage = to LoginPage

        when: 'Kitöltöm a "CREATE AN ACCOUNT" blokkban az "Email address" mezőt egy még nem regisztrált email címmel és a "Create an account" funkciógombra kattintok.'
        loginPage.registerEmailAddress = 'sss@email.hu'
        loginPage.registerButton.click()

        then: 'Megjelenik a "Create an account" űrlap.'
        def registerPage = waitFor { at RegisterPage }

        when: 'Kitöltöm a "Your personal information" blokkban az összes mezőt és a "Register" funkciógombra kattintok.'
        registerPage.gender.checked = 'Mr.'
        registerPage.firstName = 'Elek'
        registerPage.lastName = 'Teszt'
        registerPage.password = 'teszt123'
        registerPage.dateOfBirth.value('1990', 'May', '12')
        registerPage.newsletter.check()
        registerPage.offers.check()
        registerPage.registerButton.click()

        then: '5 hibaüzenet jelenik meg'
        registerPage.errorMessages.values.size() == 5

        when: 'Kitöltöm a „Your address” blokkban az összes mezőt és „Register” funkciógombra kattintok.'
        registerPage.password = 'teszt123'
        registerPage.addressFirstName = 'Elek'
        registerPage.addressLastName = 'Mekk'
        registerPage.addressCompany = 'gyuszi és fia bt.'
        registerPage.address1 = '4821 Debrecen kis utca 32'
        //registerPage.address2 = '4'
        registerPage.city = 'Debrecen'
        registerPage.state = 'Alaska'
        registerPage.postcode = '00000'
        registerPage.country = 'United States'
        registerPage.phone = '06401234567'
        registerPage.phone_mobile = '06401234567'
        registerPage.alias="gyuri"
        registerPage.registerButton.click()


        then: 'Megjelenik a felhasználó adatai felület: “My account”.'
        def myAccountPage = waitFor { at hu.tigra.pti.geb.page.MyAccountPage }
        myAccountPage.header.text() == "MY ACCOUNT"

    }
}
