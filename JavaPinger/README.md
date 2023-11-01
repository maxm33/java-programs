# Java Pinger

The UNIX ping command but implemented in Java.

## Usage

- Compile

```
javac PingServer.java
javac PingClient.java
```

- Run the server (seed argument is optional)

```
java PingServer <port> [seed]
```

- Open another shell and run the client

```
java PingClient localhost <port>
```
