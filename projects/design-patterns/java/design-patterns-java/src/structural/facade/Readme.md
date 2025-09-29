# Explanation: 

The Facade pattern provides a simplified interface to a complex subsystem of classes, providing a higher-level interface that makes the subsystem easier to use.

Explanation:

* **CPU, Memory, HardDrive:** 
Represent the complex subsystem classes that have specific responsibilities.

* **ComputerFacade:** 
Provides a simplified interface (start() method) to interact with the complex subsystem. It coordinates interactions between subsystem components to perform a higher-level operation (starting the computer in this case).

* **FacadePatternDemo:** 
Demonstrates how the ComputerFacade is used to start the computer, hiding the complexities of subsystem interactions from the client.

### Usage Guidelines:

#### Facade Pattern Usage:

* Use when you want to provide a simple interface to a complex subsystem.
* Use when you need to decouple clients from the complexities of subsystem components. 


## When to Use and When Not to Use:

* **Use:** When you need to provide a simple interface to a complex subsystem.
* **Use:** When you want to decouple clients from the intricate details of subsystem components.
* **Avoid:** When the subsystem is simple and does not require abstraction.
* **Avoid:** When clients need direct access to individual components of the subsystem.