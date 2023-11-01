package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Task implements Runnable {
    private String filename;
    private Map<String, Integer> map;
    private int number;

    public Task(String filename, Map<String, Integer> map, int number) {
        this.filename = filename;
        this.map = map;
        this.number = number;
    }

    @Override
    public void run() {
        int c = 0;
        File file = new File(filename);
        System.out.printf("Thread %s working on %s\n", number, filename);
        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            while ((c = br.read()) != -1) {
                if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                    String character = String.valueOf(Character.toLowerCase((char) c));
                    synchronized (map) {
                        map.computeIfPresent(character, (key, value) -> value + 1);
                        map.putIfAbsent(character, 1);
                    }
                }
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException exc) {
            System.out.printf("Error: File %s was not found.\n", filename);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}