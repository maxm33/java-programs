# Dungeon Adventures

This one is a simple game with a Command Line Interface.

It is based on a socket connection between each client and a server.
It is a round game where each client plays in his own match and, as a warrior, his goal is to defeat the monster he is facing.

The server randomly generates the parameters at the start of each game session (HP of monster and warrior, number of potions the warrior has),
whereas the damage of both player and monster and their chance to fail are calculated each round.

If the player wins or ties, the server asks if he wants to play another game, whereas if he loses, he must quit the game and reconnect
if he wants to try again.

## Usage

- Compile

```
javac Client.java ./src/*.java
```

- Run the server

```
java src.Server
```

- Open another (or multiple) shell(s) and run the client(s)

```
java Client
```
