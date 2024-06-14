package GameData;

import java.util.Random;

public class PacmanUpgrades {
    public enum UpgradeType {
        LIFE1, IMMUNITY, DUMBER_GHOSTS, SPEEDx10, POINTS50
    }

    private int x;
    private int y;

    public UpgradeType currentUpgradeType;

    public PacmanUpgrades(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public UpgradeType getRandomUpgrade() {
        UpgradeType[] upgradeTypes = UpgradeType.values();
        Random random = new Random();
        int index = random.nextInt(upgradeTypes.length);

        currentUpgradeType = upgradeTypes[index];
        return upgradeTypes[index];
    }
}
