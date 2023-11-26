## LDTS_project-l01gr03 - <PROJECT NAME>

**Our project**:

In this exciting game you arrive at an isolated island after a ship wreck and you receive unsettling news, what are you going to do?

This project was developed by *Bruno Aguiar* (*up202205619*@fe.up.pt), *Francisco Fernandes* (*up202208485*@fe.up.pt) and *Lara Coelho* (*up202208689*@fe.up.pt) for LDTS 2023⁄24.

### IMPLEMENTED FEATURES

- 
- Images

### PLANNED FEATURES

- Explore the island;
- Talk with characters;
- Interact with objects;
- Progress in the story;
- Uncover new endings.

### UML

### DESIGN

#### ARCHITECTURAL PATTERN

**Problem in Context**

How to organize our code into functional segments and achieve reusability.

**The Pattern**

We have applied the **MVC** (Model-View-Controller) compound pattern. This pattern allows us to factore our code into funcional segments. It combines different patterns into an effective solution for our problem.

- The **Model** is responsible for mantaining all the data, state and any application logic. It's oblivious to the view and controller but it can provide methods to manipulate and retrieve its state and send notifications of state changes to observers.

- The **View** is responsible for presenting the model, usually gets the state and data it need directly from the model. It's an user interface.

- The **Controller** is responsible for taking user input and figuring out what it meand to de model.

**Implementation**

The following UML diagram shows how we implemented this compound pattern.

![img](./docs/MVC_diagram.png) 

These classes can be found in the following files:

- Files with classes.

**Consequences**

The use of the MVC compound pattern allowed us to organize and structure our code. 

#### USE OF CONDICIONALS WHEN INSTATIATING CONCRETE CLASSES

**Problem in Context**

We had to use conditionals to decide which concrete class we wanted to instantiate. However this becomes a problem when we want to add and remove concrete classes.

**The Pattern**

We have used the **Factory Method Pattern**.This Design Pattern defines an "interface" for creating an object, but lets subclasses decide which class to instantiate.

**Implementation**


**Consequences**

The use of the Factory design pattern allows us to encapsulate object creation and achieve a more decoupled and flexible design.

#### KNOWN CODE SMELLS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation.

### TESTING

- Screenshot of coverage report.
- tLink to mutation testing report.

### SELF-EVALUATION

**Division of work**:

- Bruno: 33,3333%
- Francisco: 33,3333%
- Lara: 33,3333%
