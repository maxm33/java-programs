package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Game implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public Game(Socket socket) throws IOException {
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    private void displayStats(Player player, Monster monster) {
        out.printf(
                "\n********************\nYour HP : %s/%s\nYour potions : %s\nMonster's HP : %s/%s\n********************\n\nYou can:\n- attack\n- use potion (if available)\n- exit\n\n",
                player.HP,
                player.HPCap,
                player.potions,
                monster.HP,
                monster.HPCap);
    }

    @Override
    public void run() {
        try {
            String cmd = new String();
            boolean exit = false, won = false, lost = false, tied = false;

            while (!exit) {
                Player currentPlayer = new Player(out);
                Monster currentMonster = new Monster(out);
                displayStats(currentPlayer, currentMonster);

                while (!won && !lost && !tied && !exit) {
                    out.printf("endReading\n");
                    cmd = in.nextLine();

                    switch (cmd) {
                        case "attack":
                            currentPlayer.Attack(currentMonster);
                            currentMonster.Attack(currentPlayer);
                            if (currentMonster.HP <= 0) {
                                won = true;
                                currentMonster.HP = 0; // this is just to not show negative HP
                            }
                            if (currentPlayer.HP <= 0) {
                                lost = true;
                                currentPlayer.HP = 0;
                            }
                            if (won && lost) {
                                won = false;
                                lost = false;
                                tied = true;
                            }
                            displayStats(currentPlayer, currentMonster);
                            break;
                        case "use potion":
                            if (currentPlayer.potions > 0) {
                                currentPlayer.HP += 6; // potions heal you for 6 HP
                                currentPlayer.potions--;
                                if (currentPlayer.HP > currentPlayer.HPCap) {
                                    currentPlayer.HP = currentPlayer.HPCap;
                                }
                                out.printf("\nYou heal 6 HP.\n");
                            } else {
                                out.printf("\nNo potions left!\n");
                            }
                            break;
                        case "exit":
                            exit = true;
                            out.printf("\nYour session is closing.\n");
                            out.printf("endSession\n");
                            break;
                        default:
                            out.printf("\nInvalid command!\n");
                            break;
                    }
                }

                if (exit)
                    continue;

                if (lost) {
                    exit = true;
                    out.printf("\nYou have lost... Your session is closing.\n");
                    out.printf("endSession\n");
                    continue;
                }

                if (won)
                    out.printf("\nYou have won! Monster has been defeated. Do you want to play again? y/n\n");
                else if (tied)
                    out.printf("\nYou have tied... Do you want to play again? y/n\n");

                out.printf("endReading\n");
                cmd = in.nextLine();

                if (cmd.contentEquals("n")) {
                    exit = true;
                    out.printf("\nYour session is closing.\n");
                    out.printf("endSession\n");
                } else {
                    won = false;
                    lost = false;
                    tied = false;
                    out.printf("\nGenerating new game...\n");
                }
            }
            socket.close();
        } catch (IOException ioe) {
            System.out.println("Client forced to disconnect.");
        }
    }
}