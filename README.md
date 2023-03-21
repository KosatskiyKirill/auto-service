# auto-service
![auto-service](https://carway.info/sites/default/files/magazine/service_220619_1.jpg)
## :clipboard: Description :clipboard:
It is a RESTful application built using Spring Boot, in this application the car owner can process orders and service. The application has functionality to create and store new objects such as car owners, employees, cars, goods and service. In addition, users can update any data and change statuses, calculate the cost of orders, take into account discounts and determine the salary of employees.

## :star2: Features :star2:
* Creating, storing and updating new entities - car owners, cars, employees, service, goods, etc...
* Updating any data and changing statuses.
* Calculation of the cost of orders.
* Consideration of clients discounts.
* Calculation of salary to employees.

## :chains: Structure :chains:
* Controllers - accept requests from the clients and send responses
* Service - сontains all the business logic of our application
* DAO - Contains all communication with the database

## :man_technologist: Technologies :man_technologist:
* JDK 17
* Spring Boot
* Swagger
* PostgreSQL
* Maven

## :bulb: How it works? :bulb:
* Step 1 - Fork this repository
* Step 2 - Open IntelliJ IDEA and write git clone <SSH link> in your console.
* Step 3 - Configure resources --> "application_properties" file
```
db.driver=YOUR_DRIVER
db.url=YOUR_URL
db.user=YOUR_USERNAME
db.password=YOUR_PASSWORD
```
* Start the application
