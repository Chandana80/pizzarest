# Pizza Ordering System

Maven project for REST applications based on Spring MVC framework.

This Project is build on top of ``spring-mvc-jpa-archetype`` with following features:

 * Modular architecture
 * H2 database instead of HSQLDB
 * Logback logging framework instead of Log4J
 * TestNG instead of JUnit
 * Environment for functional tests
 * Deployment via Cargo plugin

## Installation

Get the project from github using git clone. Navigate to root directory and execute following command:

    $ mvn install

Navigate to webapp directory and execute the following command
    $ mvn install

## Project Structure

The project is divided into following modules:

* Core module
* Webapp module
* Functional Tests module

The core module is a place where all java code should go (business logic, controllers, entities etc.). As of now the business logic is implemented in controller itself, if required the business logic part can be moved to service package.

The webapp module is a place where all application configuration files are stored (``web.xml``, Spring, DB and
logger configuration). Angularjs is used for UI design.

The functional tests module is a place where, as a name says,
functional tests go. All the functional tests are run in real container. Containers can be added as required.

### Building the Project

To build a project navigate to parent module directory and type:

    $ mvn install

Your project will be compiled, packaged and installed into your local repository. If you rather do not want to install
application on your local repository execute:

    $ mvn package

The target WAR archive is in ``~/webapp/target/`` (the ``target`` directory of webapp module).

By default command will build the core and webapp modules omitting the
functional tests module.

### Running the Project

You have two options to quickly run the project on embedded Jetty or Tomcat containers. It is not required to
manually configure container, however if you want the IDE tu give you some support (like debugger) you will need
to manually configure the IDE.

To run the project navigate to webapp module directory and execute:

    $ mvn jetty:run

If you rather preffer to run the application on Tomcat execute:

    $ mvn tomcat:run

### Functional Testing

The functional tests module allows you to run tests against the real application deployed on real container instead of
simulating container in your unit tests.

While the functional testing is time consuming process
functional tests are not run by default (during the normal application
build). 

You have two options two run functional tests. You can run functional tests while building the project by executing following command from parent
module directory:

    $ mvn install -Pfunctional-tests

Or you can build normally the application, navigate to functional tests module directory and execute:

    $ mvn verify

By default functional tests are executed on Jetty 6 container. If you
rather want to use Tomcat execute one of the following:

    $ mvn install -Pfunctional-tests,tomcat6x

or

    $ mvn verify -Ptomcat6x 

Note that in order to run functional tests you need to have your
application installed in local repository.
