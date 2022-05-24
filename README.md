
# ReadingIsGood App
It is a backend service developed by using SpringBoot and MySQL. In the architecture design there are three layer. 
- First layer is API Gateway : Spring Cloud Provided that this feature
- Second layer is Eureka Discovery Server which is developed by Netflix engineers.
- Third layer is ReadingIsGood Service: This is a spring boot application which contains 4 rest controller. The controller methods can call with secured token.


#### Before you start, you need to register the application with ```/customer``` url. After that you can create Bearer token to send request ```/login``` endpoint using email and password, then use the Bearer token to send request for each controller endpoints. Otherwise, you cannot access application.


![alt text](https://github.com/elifAkgun/ReadingIsGood/blob/master/readingIsGoodArchitecture.png)

## Controllers
* CustomerController 
    * Create New Customer, 
    * List Customer's Orders
* BookController 
    * Create New Book, 
    * Update Book Stock
* OrderController 
    * Create New Order, 
    * List order Detail
    * List orders by date interval
* StaticsController 
    * List Customer's Monthly Statics

#End Points
## Login
 Login endpoint provided by Spring Starter Security.

    Important Note: Only authenticated customer's can log in the system. 
    First you should use create customer URL to register the system.
  
  Must Request
  ``` http request
  POST http://192.168.1.102:8082/customer-ws/login
  ```
  With Body
  ``` json
  {
       "email": "Marta1@gmail.com",
       "password": "123456"
  }
  ```
  Sample Response in the Header
  ``` json  
 
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbGlmMkBjb2RlLWVsaWYuY29tIiwiZXhwIjoxNjU0MDI1MjExfQ.c4OFQbQ9SAmwLAFm7Gg-R1mPAPiMsq4IlDfy4xMAUi3IDG0tPhnjqMeNOMleiYYoVix-2wabmnxMKcD_ArQlQw"
  
  ```
### 1. CustomerController
#### 1.1 Create customer endpoint

  Sample request body: 
  ````http request
  POST http://localhost:8082/customer-ws/customer
  ````
    
  ```` json
    {
        "firstName": "Marta",
        "lastName": "Johnatan",
        "email": "Marta1@gmail.com",
        "password": "123456"
    }
  ````
####1.2. List Customer's orders endpoint

  Sample request
  ``` http request
  GET http://localhost:8082/customer-ws/customer/{CUSTOMER_ID}/order?page=0&size=1
  ```
  Sample Response:
  
  ```` json
  {
      "numberOfElements": 1,
      "totalElements": 4,
      "totalPages": 4,
      "number": 0,
      "size": 1,
      "first": true,
      "last": false,
      "previous": false,
      "next": true,
      "content": [
          {
              "id": "255379694",
              "date": "2022-05-21T15:40:34",
              "customer": {
                  "id": "580761146",
                  "firstName": "Marta",
                  "lastName": "Jonathan",
                  "email": "Marta1@gmail.com",
                  "password": "$2a$10$xNpHXMdlF4562ItSQsTg7.Xo/1gGJ2di9rbJ33K0QLwSc.YFkBeJi"
              },
              "books": [
                  {
                      "id": "1",
                      "name": "LIFE OF APPLE",
                      "amount": 10.2,
                      "stock": 7
                  },
                  {
                      "id": "113479932",
                      "name": "LIFE OF BANANA",
                      "amount": 10.2,
                      "stock": 8
                  },
                  {
                      "id": "1026691997",
                      "name": "LIFE OF KIWI",
                      "amount": 10.2,
                      "stock": 6
                  }
              ],
              "bookCount": 0,
              "amount": 30.6
          }
      ]
  }
  ````

### 2. BookController
####2.1. Create Book

*  Success Request
      ``` http request
      POST http://localhost:8082/customer-ws/books
      ```
      
      ``` json
        {
             "name": "LIFE OF STRAWBERY",
             "amount": 10.2,
             "stock": 5
        }
      ```
* Success Response
  ``` http request
    Status 201 Created
  ```
  
   ``` json
    {
        "id": "3ad6461c-7ee7-4fcf-a231-ef18541c8cd3",
        "name": "LIFE OF STRAWBERY",
        "amount": 10.2,
        "stock": 5
    }
   ```
  
* Failed Request
    ``` http request
    POST http://localhost:8082/customer-ws/books
    ```
    
    ``` json
      {
           "name": "LIFE OF STRAWBERY",
           "amount": 10.2,
           "stock": -5
      }
    ```
* Failed Response
    ``` http request
      Status 400 Bad Request
    ```
    
    ``` json
    {
        "timeStamp": 1653236257141,
        "statusCode": 400,
        "message": "Validation failed for object='book'. Error count: 1",
        "url": "/books"
    }
     ```
  
  
####2.2. Update Book Stock

  Sample Request
  ``` http request
  POST http://localhost:8082/customer-ws/books/{BOOK_ID}/stock?stock={STOCK}
  ```

  Sample Response
  
    ``` http status
       Status 202 Accepted
    ```
  ```` json
  {
      "id": "200d14b4-8a92-49d2-9691-2cc88f35a46e",
      "name": "LIFE OF MANGO",
      "amount": 10.2,
      "stock": 6
  }
  
  ````

### 3. OrderController
####3.1. Create New order
  * Sample Request
  
   ``` http request
    POST http://localhost:8082/customer-ws/order
   ```
  
  ``` json
  {
      "customer": {
          "id": "3b6a2859-1101-4d7e-a094-391d2d64a8df"
      },
      "books": [
          {
              "id": "113479932"
          }
      ]
  }
  ```
  
  * Sample Response
   
      
   ``` http
   201 Created   
   ```
   
   ``` json
   {
       "message": "Order is created."
   }
   ```

####3.2. List Order By Id
  * Sample Request
  
   ``` http request
    GET http://192.168.1.102:8082/customer-ws/order/{CUSTOMER_ID}
   ```
  
  ``` json
  {
      "customer": {
          "id": "3b6a2859-1101-4d7e-a094-391d2d64a8df"
      },
      "books": [
          {
              "id": "113479932"
          }
      ]
  }
  ```
  
  * Sample Response
      
   ``` http
   200 OK  
   ```
   
   ``` json
  {
      "id": "fdac284b-7a0f-4da1-8a86-344c9e646da2",
      "date": "2022-04-21T16:39:15",
      "customer": {
          "id": "3b6a2859-1101-4d7e-a094-391d2d64a8df",
          "firstName": "Elif2",
          "lastName": "Akgün",
          "email": "elif2@code-elif.com",
          "password": "$2a$10$C7EB.as7VsHnQaXENkcPv.CbZ1ptQdyZ3NctYXPZWeTCgF7cHIDMO"
      },
      "books": [
          {
              "id": "1",
              "name": "LIFE OF APPLE",
              "amount": 10.2,
              "stock": 7
          },
          {
              "id": "1026691997",
              "name": "LIFE OF KIWI",
              "amount": 10.2,
              "stock": 6
          },
          {
              "id": "113479932",
              "name": "LIFE OF BANANA",
              "amount": 10.2,
              "stock": 9
          }
      ]
  }
   ```
####3.3 List Orders Between StartDate and EndDate

  * Sample Request
  
   ``` http request
    GET http://localhost:8082/customer-ws/order/findByDate?startDate={START_DATE}&endDate={END_DATE}&page={PAGE}&size={SIZE}
   ```
  
  * Sample Response
      
   ``` http
   200 OK  
   ```
   
  ```` json
  {
      "numberOfElements": 1,
      "totalElements": 4,
      "totalPages": 4,
      "number": 0,
      "size": 1,
      "first": true,
      "last": false,
      "previous": false,
      "next": true,
      "content": [
          {
              "id": "-255379694",
              "date": "2022-05-21T15:40:34",
              "customer": {
                  "id": "580761146",
                  "firstName": "Marta",
                  "lastName": "Jonathan",
                  "email": "Marta1@gmail.com",
                  "password": "$2a$10$xNpHXMdlF4562ItSQsTg7.Xo/1gGJ2di9rbJ33K0QLwSc.YFkBeJi"
              },
              "books": [
                  {
                      "id": "1",
                      "name": "LIFE OF APPLE",
                      "amount": 10.2,
                      "stock": 7
                  },
                  {
                      "id": "113479932",
                      "name": "LIFE OF BANANA",
                      "amount": 10.2,
                      "stock": 7
                  },
                  {
                      "id": "1026691997",
                      "name": "LIFE OF KIWI",
                      "amount": 10.2,
                      "stock": 6
                  }
              ],
              "bookCount": 0,
              "amount": 30.6
          }
      ]
  }
  ````
####4.1. Get Customer's Monthly Order Info

  * Sample Request
  
   ``` http request
    GET http://localhost:8082/customer-ws/statistics?customerId={CUSTOMER_ID}
   ```

  * Sample Response
      
   ``` http
   200 OK  
   ```
  
  ``` json
  [
      {
          "orderYear": 2022,
          "orderMonth": 5,
          "totalOrderCount": 2,
          "totalPurchasedAmount": 61.2
      },
      {
          "orderYear": 2022,
          "orderMonth": 4,
          "totalOrderCount": 1,
          "totalPurchasedAmount": 30.6
      },
      {
          "orderYear": 2021,
          "orderMonth": 5,
          "totalOrderCount": 1,
          "totalPurchasedAmount": 30.6
      }
  ]
  ```

## Used Technologies and Libraries
* Java 15
* SpringBoot 2.6.7
* Spring Data JPA
* Spring Eureka Discovery Server
* Spring Api Gateway
* Spring Security
* Mysql
* Apache Maven
* ModelMapper
* JWT IO
* Lombok
* Mockito
* JUnit5

Api Gateway address : http://localhost:8082/customer-ws/v3/api-docs

Swagger Documentation Address : http://192.168.1.102:58440/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
### Code Coverage
<table>
<tr>
<th>
Element
</th>
<th>
Coverage
</th>
<th>
Covered Instructions
</th>
<th>
Missed
</th>
<th>
Total Instructions
</th>
</tr>
<tr>
<th>
readingIsGoodService
</th>
<th>
53.3 %
</th>
<th>
 2,880
</th>
<th>
 2,528
</th>
<th>
5,408
</th>
</tr>
</table>

##Run commands
````shell script
    docker container run --network cambly-network --name readingisgoodservice -e RDS_USERNAME=devuser -e RDS_PASSWORD=devuser -e RDS_HOSTNAME=camblydb -e RDS_PORT=3306 -e RDS_DB_NAME=ReadingIsGood -e NETWORK_NAME=172.18.0.3 -d  readingisgoodservice:readingisgood --link=cambly-network
    
    docker container run --network cambly-network --name apigateway -e NETWORK_NAME=172.18.0.3 -p 8082:8082 -d apigateway:readingisgood
    
    docker container run --network cambly-network --name discoveryserver -p 8012:8012 -d discoveryserver:readingisgood --link=cambly-network
````