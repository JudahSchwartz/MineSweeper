import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MSWindow extends JFrame
{
    CodeBehind cb;
    final static int WIDTH =700, LENGTH = 700,BUTTON_COL = 16, BUTTON_ROW = 16;
    JButton[][] buttons;
    public void setButtonDisplay(int row, int col, char c)
    {
        buttons[row][col].setText(Character.toString(c));

    }

    MSWindow(CodeBehind cb)
    {
        this.cb = cb;


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH,LENGTH);
        setLayout(new GridLayout(BUTTON_ROW,BUTTON_COL));

        addButtons();


        setVisible(true);
    }



    public void addButtons()
    {
        buttons = new JButton[BUTTON_ROW][BUTTON_COL];
        for(int i = 0; i < BUTTON_ROW;i++)
        {
            for(int j = 0; j<BUTTON_COL;j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new MyButtonListener(i,j));
                add(  buttons[i][j] );

            }
        }
    }
    class MyButtonListener implements ActionListener{

        int i,j;
        MyButtonListener(int i, int j){
        this.i = i;
        this.j = j;
    }
        @Override
        public void actionPerformed(ActionEvent e) {
            cb.buttonClicked(i,j);

        }
    }
}
