# KalahGame

What all things are done ?

Architecture
It was chosen to use architectural as type - Multi-Layered Architecture with an in memory persistence layer.

Structure
The project is wrapped up with Maven. It consists of domain , service (business logic), web (rest apis). 
The main game flow is implemented through Spring-like Chain of Responsibility pattern using Ordered interface.
Every turn goes through pipeline of RulesApplier classes. Each of them is applying the rule, which is responsible for.

Tech Stack
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

JavaDocs
Code is documented with as much as possible details. 

How to build?
mvn clean install (mvn clean install -DskipTests to skip test cases)

How to run the application?
mvn spring-boot:run

Swagger url ?
http://localhost:8080/swagger-ui.html






