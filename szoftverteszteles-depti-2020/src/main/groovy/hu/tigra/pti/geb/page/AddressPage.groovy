package hu.tigra.pti.geb.page

import geb.Page

class AddressPage extends Page {
    static url = "/index.php?controller=order&step=1"

    static at = { header.displayed }

    static content = {
        header { $('h1[class="page-heading"]') }

        paymentCheckButton { $('button[name="processAddress"]') }

    }

}
