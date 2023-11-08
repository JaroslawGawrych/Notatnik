import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Kolo implements javax.swing.Icon{
    private int width, height;
    private Color color;

    public Kolo(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, height);
        g2.setColor(color);
        g2.fill(circle);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
