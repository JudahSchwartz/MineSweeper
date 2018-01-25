import javafx.scene.effect.Light;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CodeBehind {
    MSWindow msWindow;
    final int NUM_BOMBS = 40;
    Plot plots[][] = new Plot[MSWindow.BUTTON_ROW][MSWindow.BUTTON_COL];
    public void setWindow(MSWindow window)
    {
        msWindow = window;
        createPlots();
        ArrayList<Point> bombs = determineBombs();
        setBombs(bombs);
    }
    public void buttonClicked(int x, int y) {
        plots[x][y].clicked = true;
        System.out.println("HELL FROM CB");
        if (msWindow == null) {
            throw new IllegalStateException("Code behind doesn't have a linked window");
        } else {
            if(plots[x][y].isBomb)
            {
                msWindow.setButtonDisplay(x, y, '*');
                JOptionPane.showMessageDialog(msWindow,"You Lose");

            }
            else if(plots[x][y].neighboringBombs == 0)
            {

                msWindow.setButtonDisplay(x, y, '0');
                for (int row = -1; row < 2; row++) {
                    for (int col = -1; col < 2; col++)
                    {
                        if(!(row==0&&col==0))
                        {


                            if(withinRowRange(x + row)&&withinColRange(y + col))
                            {
                                if(!plots[row+x][col+y].clicked) {
                                    buttonClicked(row + x, col + y);
                                }
                            }
                        }
                    }

                }
            }
            else
            {
                char c = (char)(plots[x][y].neighboringBombs + '0');
                System.out.println("" + x + y);
                msWindow.setButtonDisplay(x, y, c);
            }


        }


    }
    public void createPlots(){
        for(int i = 0; i < MSWindow.BUTTON_ROW;i++)
        {
            for(int j = 0; j< MSWindow.BUTTON_COL;j++)
            {
                plots[i][j] = new Plot();
            }
        }
    }
    public void setBombs(ArrayList<Point> bombs)
    {
        for (Point p: bombs) {
            for (int row = -1; row < 2; row++) {
                for (int col = -1; col < 2; col++)
                {
                    if(row==0&&col==0)
                    {
                        plots[row+p.x][col+p.y].isBomb = true;
                    }
                    else
                    {
                        if(withinRowRange(p.x + row)&&withinColRange(p.y + col))
                        {
                            plots[row+p.x][col+p.y].neighboringBombs++;
                        }
                    }
                }

            }
        }
    }
    public boolean withinColRange(int n)
    {
        return n >=0 && n < MSWindow.BUTTON_COL;
    }
    public boolean withinRowRange(int n)
    {
        return n >=0 && n < MSWindow.BUTTON_ROW;
    }
    public ArrayList<Point> determineBombs()
    {
        ArrayList<Point> bombs = new ArrayList<>();
        Random rand = new Random();
        int i = 0;
        do
        {
            Point bomb = new Point(rand.nextInt(MSWindow.BUTTON_ROW),rand.nextInt(MSWindow.BUTTON_COL));
            if(!bombs.contains(bomb))
            {
                bombs.add(bomb);
                i++;
            }

        }while(i < NUM_BOMBS);
        return bombs;
    }
    class Plot{

        boolean isBomb = false,clicked = false;
        int neighboringBombs = 0;


    }
}

