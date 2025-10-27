import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * RoseDemo.java
 * Ein einfaches Swing-Programm, das eine stilisierte Rose mit Java2D zeichnet.
 * Kompilieren: javac RoseDemo.java
 * Starten:      java RoseDemo
 */
public class RoseDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Rose in Java2D");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new RosePanel());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}

class RosePanel extends JPanel {
    private static final int PREF_W = 800;
    private static final int PREF_H = 800;

    RosePanel() {
        setPreferredSize(new Dimension(PREF_W, PREF_H));
        setBackground(new Color(245, 245, 250));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRose((Graphics2D) g);
    }

    private void drawRose(Graphics2D g2) {
        // High quality rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Coordinates center
        double cx = getWidth() / 2.0;
        double cy = getHeight() / 2.0 - 40;

        // Draw stem
        drawStem(g2, cx, cy + 120);

        // Draw leaves
        drawLeaf(g2, cx - 130, cy + 140, -35);
        drawLeaf(g2, cx + 110, cy + 160, 25);

        // Draw petals (layered)
        int layers = 9;
        for (int i = 0; i < layers; i++) {
            double scale = 1.0 - i * 0.08;
            double alpha = 1.0f - i * 0.08f;
            drawPetalRing(g2, cx, cy, layers - i, scale, (float) alpha);
        }

        // Draw center bud
        drawCenterBud(g2, cx, cy);
    }

    private void drawStem(Graphics2D g2, double x, double topY) {
        // Stem as a curved path
        Path2D stem = new Path2D.Double();
        stem.moveTo(x + 6, topY + 20);
        stem.curveTo(x + 6, topY + 120, x - 40, topY + 320, x - 20, topY + 400);
        stem.lineTo(x + 12, topY + 400);
        stem.curveTo(x + 30, topY + 320, x + 46, topY + 120, x + 12, topY + 20);
        stem.closePath();

        GradientPaint gp = new GradientPaint((float) x, (float) topY, new Color(40, 100, 30),
                (float) x, (float) (topY + 400), new Color(12, 60, 20));
        g2.setPaint(gp);
        g2.fill(stem);

        // Thin darker center line
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(new Color(8, 45, 15));
        g2.draw(new CubicCurve2D.Double(x + 6, topY + 20, x + 10, topY + 140, x - 20, topY + 300, x - 6, topY + 400));
    }

    private void drawLeaf(Graphics2D g2, double cx, double cy, double angleDeg) {
        AffineTransform old = g2.getTransform();
        g2.translate(cx, cy);
        g2.rotate(Math.toRadians(angleDeg));

        Path2D leaf = new Path2D.Double();
        leaf.moveTo(0, 0);
        leaf.curveTo(-70, -20, -120, -80, -40, -140);
        leaf.curveTo(10, -100, 40, -40, 0, 0);
        leaf.closePath();

        // Leaf gradient
        Rectangle2D bounds = leaf.getBounds2D();
        GradientPaint gp = new GradientPaint((float) bounds.getMinX(), (float) bounds.getMinY(),
                new Color(120, 200, 120),
                (float) bounds.getMaxX(), (float) bounds.getMaxY(),
                new Color(30, 110, 40), true);
        g2.setPaint(gp);
        g2.fill(leaf);

        // Veins
        g2.setStroke(new BasicStroke(1.4f));
        g2.setColor(new Color(20, 70, 25));
        g2.draw(leaf);

        g2.setTransform(old);
    }

    private void drawPetalRing(Graphics2D g2, double cx, double cy, int petalCount, double scale, float alpha) {
        // base ellipse petal
        Ellipse2D petal = new Ellipse2D.Double(-60, -20, 140, 80); // oval shape

        for (int i = 0; i < petalCount; i++) {
            double angle = i * (360.0 / petalCount);

            AffineTransform at = new AffineTransform();
            at.translate(cx, cy);
            at.rotate(Math.toRadians(angle));
            at.scale(0.8 * scale, 0.8 * scale);
            at.translate(0, -30 * scale); // push outward a bit

            Shape s = at.createTransformedShape(petal);

            // Petal color gradient (from lighter center to darker tip)
            Rectangle2D b = s.getBounds2D();
            Point2D p1 = new Point2D.Double(b.getCenterX(), b.getCenterY() - b.getHeight() * 0.4);
            Point2D p2 = new Point2D.Double(b.getCenterX(), b.getCenterY() + b.getHeight() * 0.6);

            Color inner = new Color(255, 120, 150, Math.round(255*alpha));
            Color outer = new Color(160, 10, 50, Math.round(220*alpha));
            LinearGradientPaint lgp = new LinearGradientPaint(p1, p2,
                    new float[]{0f, 1f},
                    new Color[]{inner, outer});

            g2.setPaint(lgp);
            g2.fill(s);

            // subtle outline
            g2.setColor(new Color(80, 10, 30, Math.round(180*alpha)));
            g2.setStroke(new BasicStroke((float) (1.0 * scale)));
            g2.draw(s);
        }
    }

    private void drawCenterBud(Graphics2D g2, double cx, double cy) {
        // small spirals/ovals in the center to simulate bud
        for (int i = 0; i < 6; i++) {
            double r = 8 + i * 6;
            Ellipse2D e = new Ellipse2D.Double(cx - r/2.0, cy - r/2.0 + i*2, r, r*0.7);
            float alpha = 1.0f - i * 0.12f;
            Color fill = new Color(200, 40, 80, Math.round(255*alpha));
            g2.setPaint(fill);
            g2.fill(e);
            g2.setColor(new Color(100, 10, 35, Math.round(160*alpha)));
            g2.setStroke(new BasicStroke(1f));
            g2.draw(e);
        }

        // tiny highlight
        Ellipse2D highlight = new Ellipse2D.Double(cx - 6, cy - 18, 12, 6);
        g2.setColor(new Color(255, 220, 225, 180));
        g2.fill(highlight);
    }
}
