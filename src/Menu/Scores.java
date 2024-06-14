package Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<ScoresEntry> scoreEntries;

    public Scores() {
        scoreEntries = new ArrayList<>();
    }

    public void addEntry(ScoresEntry entry) {
        scoreEntries.add(entry);
        Collections.sort(scoreEntries);
    }

    public List<ScoresEntry> getScoreEntries() {
        return scoreEntries;
    }

    public static Scores loadScore() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.ser"))) {
            return (Scores) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Scores();
        }
    }

    public void saveScore() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scores.ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ScoresEntry implements Serializable, Comparable<ScoresEntry> {
        private static final long serialVersionUID = 1L;
        private String name;
        private int points;

        public ScoresEntry(String name, int points) {
            this.name = name;
            this.points = points;
        }

        @Override
        public int compareTo(ScoresEntry o) {
            return Integer.compare(o.points, this.points);
        }

        @Override
        public String toString() {
            return name + " - " + points;
        }
    }
}