import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;

public class Tile {
    private final JLabel label;

    private void configure() {
        label.setFont(new Font("serif", Font.PLAIN, 40));
        label.setBorder(new LineBorder(Color.black));
        label.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int componentWidth = label.getWidth();
                label.setFont(new Font("serif", Font.PLAIN, componentWidth / 2));
            }
        });
    }

    public Tile() {
        label = new JLabel("", SwingConstants.CENTER);
        configure();
    }

    public Tile(int num) {
        label = new JLabel("", SwingConstants.CENTER);
        configure();
        label.setText(Integer.toString(num));
    }

    JLabel getLabel() {
        return label;
    }

    boolean isEmpty() {
        return label.getText().isEmpty();
    }

    int getNumber() {
        return Integer.parseInt(label.getText());
    }

    void setNumber(int num) {
        label.setText(Integer.toString(num));
    }

    void setEmpty() {
        label.setText("");
    }

    void addMouseListener(MouseListener listener) {
        label.addMouseListener(listener);
    }
}
