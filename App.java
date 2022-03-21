import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class App {
    private static Random random = new Random();

    private static void randomFill(Tile[][] tiles) {
        final int[] ints = new int[]
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        for (int i = 0; i < 100; i++) {
            int ind1 = random.nextInt(ints.length);
            int ind2 = random.nextInt(ints.length);

            int t = ints[ind1];
            ints[ind1] = ints[ind2];
            ints[ind2] = t;
        }

        for (int i = 0; i < 15; i++) {
            tiles[i % 4][i / 4].setNumber(ints[i]);
        }
     tiles[3][3].setEmpty();
    }
    public static void main(String[] args) {
        GridLayout layout = new GridLayout(0, 4);

        JFrame window = new JFrame("Application");

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Tile[][] tiles = new Tile[4][4];
        final Position empty = new Position(3, 3);
        final TilesCurator curator = new TilesCurator(tiles, window);
        final ArrowsListener listener = new ArrowsListener(tiles, empty, curator);

        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem fileNew = new JMenuItem("New", KeyEvent.VK_N);
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_DOWN_MASK));
        fileNew.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomFill(tiles);
                empty.set(new Position(3, 3));
            }
        });

        JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_Q);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        file.add(fileNew);
        file.add(exit);

        JMenu about = new JMenu("About");

        JMenuItem aboutAbout = new JMenuItem("About", KeyEvent.VK_O);
        aboutAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_DOWN_MASK));

        about.add(aboutAbout);

        aboutAbout.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("2002 год \nГруппа Р3167 \nСтудент Варламова София");
                label.setFont(new Font("serif", Font.PLAIN, 20));
                JOptionPane.showMessageDialog(window, label, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        bar.add(file);
        bar.add(about);

        window.setJMenuBar(bar);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].addMouseListener(
                        new ClickTileListener(tiles, empty, new Position(i, j), curator)
                );
            }
        }

        randomFill(tiles);

        window.setMinimumSize(new Dimension(500, 500));
        window.setMaximumSize(new Dimension(500, 500));
        window.addKeyListener(listener);

        window.setLayout(layout);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                window.add(tiles[j][i].getLabel());
            }
        }

        window.setVisible(true);
    }
}
