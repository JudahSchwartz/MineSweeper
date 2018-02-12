import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

enum Shape {
    LINE, OVAL, RECTANGLE
}

public class MyPaintFrame extends JFrame {
    public MyPaintFrame(PaintModel p) {
        CanvasPanel cp = new CanvasPanel(p);
        add(cp);
        ButtonPanel bp = new ButtonPanel(p);
        add(BorderLayout.NORTH, bp);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800, 800);
    }
}

class Main {
    public static void main(String[] args) {
        PaintModel paintModel = new PaintModel();
        MyPaintFrame myPaintFrame = new MyPaintFrame(paintModel);
    }
}

class ButtonPanel extends JPanel {

    PaintModel p;

    ButtonPanel(PaintModel p) {
        this.p = p;
        JButton b = new JButton();

        b.setBackground(new Color(255, 0, 0));
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setColor(new Color(255, 0, 0));
            }
        });
        JButton b2 = new JButton();
        b2.setBackground(new Color(0, 255, 0));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setColor(new Color(0, 255, 0));
            }
        });
        JButton b3 = new JButton();
        b3.setBackground(new Color(0, 0, 255));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setColor(new Color(0, 0, 255));
            }
        });
        JButton b4 = new JButton();
        b4.setBackground(new Color(0, 0, 0));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setColor(new Color(0, 0, 0));
            }
        });

        JButton b5 = new JButton("Oval");
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setShape(Shape.OVAL);
            }
        });
        JButton b6 = new JButton("Rectangle");
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setShape(Shape.RECTANGLE);
            }
        });
        JButton b7 = new JButton("Line");
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setShape(Shape.LINE);
            }
        });
        setSize(600, 100);
        add(b);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
    }
}


class CanvasPanel extends JPanel {
    PaintModel p;
    LinkedList<DrawnItem> drawnItems = new LinkedList<>();
    CanvasPanel(PaintModel pm) {
    p = pm;
        setSize(600, 500);
        addMouseListener(new MouseAdapter() {
            int x1,y1,x2,y2;
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                x1 = e.getX();
                y1 = e.getY();


            }



            @Override
            public void mouseReleased(MouseEvent e) {

                super.mouseReleased(e);
                x2 = e.getX();
                y2 = e.getY();
                DrawnItem d = new DrawnItem(x1,x2,y1,y2,p.shape,p.color);
                drawnItems.add(d);
                drawShape(d,(Graphics2D) getGraphics());
            }

        });

    }
    public void drawShape(DrawnItem d, Graphics2D g)
    {

        g.setStroke(new BasicStroke(3));
        g.setColor(d.color);

        switch (d.shape)
        {
            case LINE:
                g.drawLine(d.x1,d.y1,d.x2,d.y2);
                break;
            case OVAL:
                g.drawOval(Math.min(d.x1,d.x2),Math.min(d.y1,d.y2),Math.abs(d.x2-d.x1),Math.abs(d.y2-d.y1));
                break;
            case RECTANGLE:
                g.drawRect(Math.min(d.x1,d.x2),Math.min(d.y1,d.y2),Math.abs(d.x2-d.x1),Math.abs(d.y2-d.y1));
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(DrawnItem di : drawnItems)
        {
            drawShape(di,(Graphics2D) g);
        }
    }
}
class DrawnItem
{
    final int x1,x2,y1,y2;
    Shape shape;
    Color color;
    public DrawnItem(int x1,int x2,int y1,int y2, Shape s, Color c)
    {
        this. x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.shape = s;
        this.color = c;
    }

}

class PaintModel {
    Color color = new Color(0,0,0);
    Shape shape = Shape.LINE;
    void setColor(Color c)
    {
        color = c;
    }
    void setShape(Shape s)
    {
        shape = s;
    }

}
