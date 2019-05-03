# Simple Connect Four
A simple digitisation of Connect Four. This project serves as practice rather than a meaningful product.

## Basic structure of the project
The project will be split into three sub-projects:

* The game software - as a server.
* The user interface - as a client.
* The computer player - also as a client.

In this case, the user interface and the computer player will both interface with the server in the same way, so the server will not have to make the distinction.

### The game software - as a server.
The game software will be broken up into three parts: The game logic, the application logic and the networking logic.

The game logic consists of the implementation of the game's rules, the game's objects, and the structuring of said elements. Note that "game" refers to the game itself, not the software I will produce. This part of the software will not concern itself with how the players will interact with the game instance but that they somehow will, nor will it concern itself with how the instance will be handled.

The application logic focuses on instantiating the game instance and the networking instances, as well as handling the threads created for the player interaction. Each player interaction will be handled with a new thread that exists only for the extend of the interaction.

The networking logic deals with the opening and closing of connections, handling of data received/to send and the handling commands sent by the players. The players will have a client thread each, which will handle all incoming messages.

## Progress so far
As of now, the game logic for the software is being worked on. The focus is to ensure that the game logic functions as it should without having to worry about the network, multi-threading and security challenges which I will not be completely versed in. It would also mean that I would have to gear networking and multi-threading around the game logic, and with experience of my game logic implementation, would be able to make more informed decisions to achieve this goal.

In the future, I aim to work on the application logic next then the networking logic. The application logic will also include work on some middle-code between the game logic and the networking logic as well as setting up the application.