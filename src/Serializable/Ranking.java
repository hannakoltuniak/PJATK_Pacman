package Serializable;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<RankingEntry> rankingEntries;

    public Ranking() {
        rankingEntries = new ArrayList<>();
    }

    public void addEntry(RankingEntry entry) {
        rankingEntries.add(entry);
        Collections.sort(rankingEntries);
    }

    public List<RankingEntry> getRankingEntries() {
        return rankingEntries;
    }

    public static Ranking loadRanking() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ranking.ser"))) {
            return (Ranking) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Ranking();
        }
    }

    public void saveRanking() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ranking.ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class RankingEntry implements Serializable, Comparable<RankingEntry> {
        private static final long serialVersionUID = 1L;
        private String name;
        private int points;

        public RankingEntry(String name, int points) {
            this.name = name;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public int getPoints() {
            return points;
        }

        @Override
        public int compareTo(RankingEntry o) {
            return Integer.compare(o.points, this.points);
        }

        @Override
        public String toString() {
            return name + " - " + points;
        }
    }

    public static void showRanking() {
        Ranking ranking = Ranking.loadRanking();
        JFrame frame = new JFrame("Ranking");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        JList<RankingEntry> rankingList = new JList<>(ranking.getRankingEntries().toArray(new RankingEntry[0]));
        rankingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(rankingList);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}