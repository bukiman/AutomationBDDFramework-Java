@APIs
Feature: API Tests

    Background: Create request specification
        Given basepath "https://www.advantageonlineshopping.com"
        And calling loginAPI with username "nuevoUser" to store the Authorization token
        When creating a REST request

    @CategoriesAPI
    Scenario: Get categories
        Given the endpoint "catalog/api/v1/categories"
        When sending a GET request
        Then the status code should be 200
        And the field "categoryName" values in the response array should be:
            | LAPTOPS    |
            | HEADPHONES |
            | TABLETS    |
            | SPEAKERS   |
            | MICE       |

    @CategoriesAPI
    Scenario: Get categories
        Given the endpoint "catalog/api/v1/categories/all_data"
        When sending a GET request
        Then the status code should be 200
        And the field "categoryName" values in the response array should be:
            | LAPTOPS    |
            | HEADPHONES |
            | TABLETS    |
            | SPEAKERS   |
            | MICE       |

    @ProductsAPI
    Scenario Outline: Call products by category endpoint
        Given the endpoint "catalog/api/v1/categories/{categoryId}/products"
        When setting the value "<categoryId>" in path variable "categoryId"
        And sending a GET request
        Then the status code should be 200
        And the field "products" in the response body should be a non empty array
        And the response matches the json response schema "schemas/products-schema.json"
    Examples:
        | categoryId |
        | 1          |
        | 2          |
        | 3          |
        | 4          |
        | 5          |

    @ProductsAPI
    Scenario: Call products by product id
        Given the endpoint "catalog/api/v1/products/{productId}"
        When setting the value "9" in path variable "productId"
        And sending a GET request
        Then the status code should be 200
        And the field "productStatus" in the response body should have the value "Active"

    @OrderAPI
    Scenario: Call order API by userId
        Given the endpoint "order/api/v1/carts/{userId}"
        And setting the value "115736366" in path variable "userId"
        And setting the request body
        """
        [
          {
            "hexColor": "C3C3C3",
            "productId": 1,
            "quantity": 1
          }
        ]
        """
        And sending a PUT request
        Then the status code should be 200
        And the response matches the json response schema "schemas/order-byUserId-PUT-schema.json"
        And the field "success" in the response body should have the value "true" of type boolean
        And the field "reason" in the response body should have the value "We updated your cart based on the items in stock"
        And the field "id" in the response body should have the value "0" of type integer

    @OrderAPI
    Scenario: Call carts API by userId
        Given the endpoint "order/api/v1/carts/{userId}"
        And setting the value "115736366" in path variable "userId"
        When sending a GET request
        Then the status code should be 200
        And the field "productsInCart" in the response body should be a non empty array
        And the response matches the json response schema "schemas/order-byUserId-GET-schema.json"

    @OrderAPI
    Scenario: Call shippingcost API
        Given the endpoint "order/api/v1/shippingcost"
        When setting the request body
        """
        {
            "seaddress": {
                "addressLine1": "",
                "addressLine2": "",
                "city": "",
                "country": "us",
                "postalCode": "",
                "state": ""
            },
            "secustomerName": "nuevoUser ",
            "secustomerPhone": "",
            "senumberOfProducts": 1,
            "setransactionType": "SHIPPING_COST",
            "sessionId": "BEFF1BA34FC67ECC522AADB7EEB18742"
        }
        """
        And sending a POST request
        Then the status code should be 200
        And the response matches the json response schema "schemas/order-shippingCost-schema.json"

    @OrderAPI
    Scenario: Call user orders API by userId
        Given the endpoint "order/api/v1/orders/users/{userId}"
        And setting the value "115736366" in path variable "userId"
        When setting the request body
        """
        {
            "orderPaymentInformation": {
                "Transaction_AccountNumber": "843200971",
                "Transaction_Currency": "USD",
                "Transaction_CustomerPhone": "",
                "Transaction_MasterCredit_CVVNumber": 453,
                "Transaction_MasterCredit_CardNumber": "4886345352345234",
                "Transaction_MasterCredit_CustomerName": "test data",
                "Transaction_MasterCredit_ExpirationDate": "122027",
                "Transaction_PaymentMethod": "SafePay",
                "Transaction_ReferenceNumber": 0,
                "Transaction_SafePay_Password": "cfe7d963808f1eecaff59694f25c442f95a70f05",
                "Transaction_SafePay_UserName": "userData-1",
                "Transaction_TransactionDate": "27112025",
                "Transaction_Type": "PAYMENT"
            },
            "orderShippingInformation": {
                "Shipping_Address_Address": "",
                "Shipping_Address_City": "",
                "Shipping_Address_CountryCode": 40,
                "Shipping_Address_CustomerName": "nuevoUser ",
                "Shipping_Address_CustomerPhone": "",
                "Shipping_Address_PostalCode": "",
                "Shipping_Address_State": "",
                "Shipping_Cost": 519.99,
                "Shipping_NumberOfProducts": 1,
                "Shipping_TrackingNumber": 0
            },
            "purchasedProducts": [
                {
                    "hexColor": "C3C3C3",
                    "productId": 1,
                    "quantity": 1,
                    "hasWarranty": false
                }
            ]
        }
        """
        And sending a POST request
        Then the status code should be 200

    @OrderAPI
    Scenario: Call cart API by userId to delete it
        Given the endpoint "order/api/v1/carts/{userId}"
        And setting the value "115736366" in path variable "userId"
        And sending a DELETE request
        Then the status code should be 200
