package hu.tigra.pti.geb.page

import geb.Page
import geb.module.Checkbox

class ShippingPage extends Page {
    static url = "/index.php?controller=order"

    static at = { header.displayed }

    static content = {
        header { $('h1[class="page-heading"]') }
        check { $('input[id="cgv"]').module(Checkbox)}
        paymentCheckOut { $('button[name="processCarrier"]')}
    }
}
