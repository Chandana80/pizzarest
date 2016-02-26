# Pizza Ordering System

Maven project to design REST end points for Pizza Delivery System

This Project is build on top of ``spring-mvc-jpa-archetype`` and has following components:

 * HSQLDB
 * Logback logging
 * TestNG
 * Environment for functional tests
 * Deployment via Cargo plugin
 * Angular JS as rest client

## Building and Running the project

Get the project from github using git clone. Navigate to root directory and execute following command:

    $ mvn install

Navigate to webapp directory and execute the following command:

    $ mvn jetty:run


## UI

    *Navigate to http://localhost:8080/pizzarest/pages/index.html in your browser
    *Click on Manager link to create Toppings and Base
    *After creating toppings and base click on order link to place orders

## Design

    Entities
        *CustomerOrder
        *CustomerOrderDetail
        *Base
        *Topping

    Rest End Points
        *OrderController
        *ToppingsController
        *BasesController

## TODO
    Add Payment System workflow
    Orders with multiple Pizzas and other options
    Ability to Add Coupons
    Manager Authentication
    Data Validations

