import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class HomeUI extends JPanel implements ActionListener
{

    private Repo repo;
    private JPanel homePanel,center,top,logoPanel,buttonPanel,searchPanel,left,right;
    private JTextField jSearch;
    private TextPrompt search;
    private JButton searchBtn,homeBtn;

    public HomeUI(Repo repoObj)
    {
        this.repo = repoObj;
        this.homePanel = new JPanel(new BorderLayout());
        this.center = new JPanel();
        this.top = new JPanel(new GridLayout(1,3));
        this.logoPanel = new JPanel(new FlowLayout());
        this.buttonPanel = new JPanel(new GridBagLayout());
        this.searchPanel = new JPanel(new GridBagLayout());
        this.left = new JPanel();
        this.right = new JPanel();
        this.jSearch = new JTextField(16);
        this.search = new TextPrompt("Search for user",jSearch);
        this.searchBtn = new JButton();
        this.homeBtn = new JButton();

        searchBtn.addActionListener(this);
        homeBtn.addActionListener(this);

        searchBtn.setIcon(new ImageIcon("search.png"));
        homeBtn.setIcon(new ImageIcon("home.png"));
        homeBtn.setOpaque(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setBorderPainted(true);
        center.setBackground(Color.WHITE);
        homePanel.add(center,BorderLayout.CENTER);
        homePanel.add(top,BorderLayout.NORTH);
        homePanel.add(left,BorderLayout.WEST);
        homePanel.add(right,BorderLayout.EAST);
        top.add(buttonPanel);
        top.add(logoPanel);
        top.add(searchPanel);
        buttonPanel.add(homeBtn);
        logoPanel.add(new JLabel(new ImageIcon("logo.png")));
        searchPanel.add(jSearch);
        jSearch.setFont(jSearch.getFont().deriveFont(Font.PLAIN, 20f));
    }

    public JPanel getPanel()
    {
        return this.homePanel;
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}
