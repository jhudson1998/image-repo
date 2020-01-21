import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class PhotoUI extends JPanel implements ActionListener
{
    Repo repo;
    JPanel photoPanel;
    JPanel top,center,left,right,bottom;
    JButton backBtn,uploadBtn;


    public PhotoUI(Repo repoObj)
    {
        //instantiate objects
        this.repo = repoObj;
        this.photoPanel = new JPanel(new BorderLayout());
        this.top = new JPanel(new GridBagLayout());
        this.center = new JPanel();
        this.left = new JPanel();
        this.right = new JPanel();
        this.bottom = new JPanel();
        this.backBtn = new JButton("Back");
        this.uploadBtn = new JButton("Upload");

        center.setBackground(Color.WHITE);

        photoPanel.add(top,BorderLayout.NORTH);
        photoPanel.add(center,BorderLayout.CENTER);
        photoPanel.add(left,BorderLayout.WEST);
        photoPanel.add(right,BorderLayout.EAST);
        photoPanel.add(bottom,BorderLayout.SOUTH);

        top.add(new JLabel(new ImageIcon("logo.png")));
    }

    public JPanel getPanel()
    {
        return this.photoPanel;
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}
