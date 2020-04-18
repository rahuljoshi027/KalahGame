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

How to build - 
mvn clean install 
mvn clean install -DskipTests (to skip test cases and finish the build sooner)

How to run the application - 
mvn spring-boot:run -pl web

Swagger url - 
http://localhost:8080/swagger-ui.html

Junit Test Cases -
Junit test coverage is 90% for web and service modules.

Performance Test Cases -
Performance test cases are JMeter test cases and both apis can cater to more than 200 requests
per second

Game rules - 
The game provides a Kalah board and a number of seeds or counters. The board has 6 small pits, called houses, on each side; and a big pit, called an end zone, at each end. The object of the game is to capture more seeds than one's opponent.
1) At the beginning of the game, four seeds are placed in each house. This is the traditional method.
2) Each player controls the six houses and their seeds on the player's side of the board. The player's score is the number of seeds in the store to their right.
3) Players take turns sowing their seeds. On a turn, the player removes all seeds from one of the houses under their control. Moving counter-clockwise, the player drops one seed in each house in turn, including the player's own store but not their opponent's.
4) If the last sown seed lands in an empty house owned by the player, and the opposite house contains seeds, both the last seed and the opposite seeds are captured and placed into the player's store.
5) If the last sown seed lands in the player's store, the player gets an additional move. There is no limit on the number of moves a player can make in their turn.
6) When one player no longer has any seeds in any of their houses, the game ends. The other player moves all remaining seeds to their store, and the player with the most seeds in their store wins.

For more detailed expaination about the game, one can refer below links :-
https://en.wikipedia.org/wiki/Kalah
https://www.youtube.com/watch?v=k4drX3HOnMg


