---
# Bowling Service

This is a two and half day challenge that implements a bowling service.

It receives a game in a file, process it, and show in console the resume of the game.

It have been developed in Java, with the following considerations:

+ Use Java 8 streams and lambdas.
+ Object Oriented Programming.
+ SRP: Single Responsibility Principle.
+ Liskov’s Substitution Principle.
+ Dependency Inversion Principle.
+ Include well-known libraries.
+ Build mechanism.
+ Packaging complete. 
+ Maven structure.
+ Versioned code.

---


## How to run
##### JRE and Maven must be installed.

* Run file 

```
install.sh
```
* This will generate the `.jar` file

```
target/bowling-1.0.jar
```

* To process the game that is in file

```
fileToBeProcessed.txt
```

* You should run 

```
java -jar target/bowling-1.0.jar fileToBeProcessed.txt
```

* Sample files where located in folder

```
/games
```
