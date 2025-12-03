@UIs
@PurchaseFeature
Feature: Complete purchase in Advantage Demo

    Background: User logs in
        Given user logs in with user name "nuevoUser" and password "Pass123"

    Scenario: Purchase of a product
        When user chooses the "Laptops" category
        And selects the product "HP Pavilion"
        And user adds it to the cart
        Then the product should display in the cart preview
        When user proceeds to checkout
        And selects the SafePay payment type
        And perform payment with the following SafePay credentials:
            | username | userData-1 |
            | password | Pass123    |
        Then the message "Thank you for buying with Advantage" should display
