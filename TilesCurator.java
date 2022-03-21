
import javax.swing.*;
import java.awt.*;

public class TilesCurator {
    private final Tile[][] tiles;
    private final JFrame mainWindow;

    public TilesCurator(Tile[][] tiles, JFrame mainWindow) {
        this.tiles = tiles;
        this.mainWindow = mainWindow;
    }

    public void endGame() {
        JLabel label = new JLabel("You win!");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(mainWindow, label, "", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean gameOver() {
        for (int i = 0; i < 15; i++) {
            int x = i % 4;
            int y = i / 4;
            if (tiles[x][y].isEmpty() || tiles[x][y].getNumber() != i + 1) {
                return false;
            }
        }

        return true;
    }
}

