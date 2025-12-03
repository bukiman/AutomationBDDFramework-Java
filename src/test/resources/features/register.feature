@RegisterFeature
Feature: Registro de usuario en Advantage Demo

    Scenario: Registro exitoso
        Given user opens Advantage Demo site
        When user clicks on the user icon
        And navigates to the register form
        And fills the form with the following data:
            | userName | nuevoUser        |
            | email    | nuevo@correo.com |
            | password | Pass123          |
        And submits the form
        Then the username should display "nuevoUser"
