import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ArrowsListener extends KeyAdapter {
    private final Tile[][] tiles;
    private final Position emptyPosition;
    private final TilesCurator curator;

    public ArrowsListener(Tile[][] tiles, Position emptyPosition, TilesCurator curator) {
        this.tiles = tiles;
        this.emptyPosition = emptyPosition;
        this.curator = curator;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: {
                if (emptyPosition.getY() <= 0) {
                    return;
                }

                int upperNum = tiles[emptyPosition.getX()][emptyPosition.getY() - 1].getNumber();
                tiles[emptyPosition.getX()][emptyPosition.getY()].setNumber(upperNum);
                tiles[emptyPosition.getX()][emptyPosition.getY() - 1].setEmpty();
                emptyPosition.setY(emptyPosition.getY() - 1);

                break;
            }
            case KeyEvent.VK_UP: {
                if (emptyPosition.getY() >= 3) {
                    return;
                }

                int upperNum = tiles[emptyPosition.getX()][emptyPosition.getY() + 1].getNumber();
                tiles[emptyPosition.getX()][emptyPosition.getY()].setNumber(upperNum);
                tiles[emptyPosition.getX()][emptyPosition.getY() + 1].setEmpty();
                emptyPosition.setY(emptyPosition.getY() + 1);

                break;
            }
            case KeyEvent.VK_RIGHT: {
                if (emptyPosition.getX() <= 0) {
                    return;
                }

                int upperNum = tiles[emptyPosition.getX() - 1][emptyPosition.getY()].getNumber();
                tiles[emptyPosition.getX()][emptyPosition.getY()].setNumber(upperNum);
                tiles[emptyPosition.getX() - 1][emptyPosition.getY()].setEmpty();
                emptyPosition.setX(emptyPosition.getX() - 1);

                break;
            }
            case KeyEvent.VK_LEFT: {
                if (emptyPosition.getX() >= 3) {
                    return;
                }

                int upperNum = tiles[emptyPosition.getX() + 1][emptyPosition.getY()].getNumber();
                tiles[emptyPosition.getX()][emptyPosition.getY()].setNumber(upperNum);
                tiles[emptyPosition.getX() + 1][emptyPosition.getY()].setEmpty();
                emptyPosition.setX(emptyPosition.getX() + 1);

                break;
            }
        }

        if (curator.gameOver()) {
            curator.endGame();
        }
    }
}
