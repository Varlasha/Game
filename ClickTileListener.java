import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickTileListener extends MouseAdapter {
    private final Tile[][] tiles;
    private final Position emptyPosition;
    private final Position tilePosition;
    private final TilesCurator curator;

    public ClickTileListener(Tile[][] tiles, Position emptyPosition,
                             Position tilePosition, TilesCurator curator) {
        this.tiles = tiles;
        this.emptyPosition = emptyPosition;
        this.tilePosition = tilePosition;
        this.curator = curator;
    }

    public void mousePressed(MouseEvent e) {
        int posDiff = Math.abs(emptyPosition.getX() - tilePosition.getX()) +
                Math.abs(emptyPosition.getY() - tilePosition.getY());

        if (e.getButton() != MouseEvent.BUTTON1 || posDiff != 1) {
            return;
        }

        Tile currentTile = tiles[tilePosition.getX()][tilePosition.getY()];
        tiles[emptyPosition.getX()][emptyPosition.getY()].setNumber(currentTile.getNumber());
        currentTile.setEmpty();

        emptyPosition.set(tilePosition);
        if (curator.gameOver()) {
            curator.endGame();
        }
    }
}
