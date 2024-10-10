package ChapterFourteen.Tutorial14;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JAction extends JFrame implements ActionListener
{
    JLabel label = new JLabel("Name?");
    JTextField field = new JTextField(12);
    JButton button = new JButton("OK");
    public JAction()
    {
        super("Action");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(label);
        add(field);
        add(button);
        button.addActionListener(this);
        field.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        label.setText("Thank you so much!");
        button.setText("Application done!");
    }
    public static void main(String[] args)
    {
        JAction aFrame = new JAction();
        final int WIDTH = 250;
        final int HEIGHT = 150;
        aFrame.setSize(WIDTH, HEIGHT);
        aFrame.setVisible(true);
    }
}
