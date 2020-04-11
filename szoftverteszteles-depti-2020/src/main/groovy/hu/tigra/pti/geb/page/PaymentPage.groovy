package hu.tigra.pti.geb.page

import geb.Page

class PaymentPage extends Page {
    static url = "/index.php?controller=order&multi-shipping="

    static at = { header.displayed }

    static content = {
        header { $('h1[class="page-heading"]') }

        csekkPayment { $('a[class="cheque"]')}

    }

}
