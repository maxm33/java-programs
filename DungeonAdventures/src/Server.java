package src;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
  public static void main(String[] args) throws Exception {
    try (ServerSocket server = new ServerSocket(3000)) {
      System.out.println("Server is running...");
      ExecutorService threadpool = Executors.newFixedThreadPool(10);
      while (true) {
        threadpool.execute(new Game(server.accept()));
        System.out.println("Client connected!");
      }
    }
  }
}