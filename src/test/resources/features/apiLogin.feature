@APIs
@LoginAPI
Feature: Login using

    @LoginSuccessfulAPI
    Scenario: Successful login with SOAP service
        Given basepath "https://www.advantageonlineshopping.com"
        And the endpoint "/accountservice/ws/AccountLoginRequest"
        When creating a SOAP request
        And setting the request body
        """
        <?xml version="1.0" encoding="UTF-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
            <soap:Body>
                <AccountLoginRequest xmlns="com.advantage.online.store.accountservice">
                    <loginPassword>Pass123</loginPassword>
                    <loginUser>nuevoUser</loginUser>
                </AccountLoginRequest>
            </soap:Body>
        </soap:Envelope>
        """
        And sending a POST request
        Then the status code should be 200
        And setting the response body root to "Envelope.Body.AccountLoginResponse.StatusMessage"
        And the field "reason" in the response body should have the value "Login Successful"
        And the response token should be printed

    @LoginFailedAPI
    Scenario: Failed login
        Given basepath "https://www.advantageonlineshopping.com"
        And the endpoint "/accountservice/ws/AccountLoginRequest"
        When creating a SOAP request
        And setting the request body
        """
        <?xml version="1.0" encoding="UTF-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
            <soap:Body>
                <AccountLoginRequest xmlns="com.advantage.online.store.accountservice">
                    <loginPassword>ljhhgvykj</loginPassword>
                    <loginUser>testuser1</loginUser>
                </AccountLoginRequest>
            </soap:Body>
        </soap:Envelope>
        """
        And sending a POST request
        Then the status code should be 200
        And setting the response body root to "Envelope.Body.AccountLoginResponse.StatusMessage"
        And the field "reason" in the response body should have the value "Incorrect user name or password."
        And the response token should be printed