# KalahGame

What all things are done ?

Architecture -
It was chosen to use architectural as type - Multi-Layered Architecture with an in memory persistence layer.

Structure -
The project is wrapped up with Maven. It consists of 5 modules domain (holds domain objects and exceptions), service (business logic), web (web API and representation), utilities (holds common required code such as constants), security (this is disabled for now).
The main game flow is implemented through Spring-like Chain of Responsibility pattern using Ordered interface.
Every turn goes through pipeline of RulesApplier classes. Each of them is applying the rule, which is responsible for.

Tech Stack -
* Java 1.8
* Spring Boot
* Lombok
* Database H2 (In-Memory)
* Spring cache (2nd level)
* JPA with Hibernate
* Transaction management
* Oauth 2.0 (Temporarily disabled to support endpoint design specification. Otherwise works perfect)
* Swagger 2
* Controller advice for global exception handling
* Slf4j logging
* Design Patters (Factory, Chain of responsibilities, Builder)
* TDD (as far as possible)
* Maven
* Followed clean code approach as far as possible

JavaDocs -
Code is documented with as much as possible details. 

How to build?
mvn clean install (mvn clean install -DskipTests to skip test cases)

How to run the application?
mvn spring-boot:run

Swagger url ?
http://localhost:8080/swagger-ui.html

Junit Test Cases -
Junit test coverage is 90% for web and service modules.

Performance Test Cases -
Performance test cases are JMeter test cases and both apis can cater to more than 200 requests
per second




