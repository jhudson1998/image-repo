import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class HomeUI extends JPanel implements ActionListener, MouseListener
{

    private Repo repo;
    private JPanel homePanel,center,top,logoPanel,buttonPanel,searchPanel,left,right,bottom;
    private JTextField jSearch;
    private TextPrompt search;
    private JButton searchBtn,homeBtn,uploadBtn,settingsBtn,logoutBtn;
    private JScrollPane feed;
    private JLabel test1,test2,test3,test4;

    public HomeUI(Repo repoObj)
    {
        //instantiate objects
        this.repo = repoObj;
        this.homePanel = new JPanel(new BorderLayout());
        this.center = new JPanel(new GridBagLayout());
        this.top = new JPanel(new GridLayout(1,3));
        this.logoPanel = new JPanel(new FlowLayout());
        this.buttonPanel = new JPanel(new GridBagLayout());
        this.searchPanel = new JPanel(new GridBagLayout());
        this.left = new JPanel();
        this.right = new JPanel();
        this.bottom = new JPanel();
        this.feed = new JScrollPane(center);
        this.jSearch = new JTextField(16);
        this.search = new TextPrompt("Search for user",jSearch);
        this.searchBtn = new JButton();
        this.homeBtn = new JButton();
        this.uploadBtn = new JButton();
        this.settingsBtn = new JButton();
        this.logoutBtn = new JButton();
        this.test1 = new JLabel(new ImageIcon("test1.jpeg"));
        this.test2 = new JLabel(new ImageIcon("test2.jpeg"));
        this.test3 = new JLabel(new ImageIcon("test3.jpg"));
        this.test4 = new JLabel(new ImageIcon("test4.jpg"));

        //add listeners
        searchBtn.addActionListener(this);
        homeBtn.addActionListener(this);
        uploadBtn.addActionListener(this);
        settingsBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
        searchBtn.addMouseListener(this);
        homeBtn.addMouseListener(this);
        uploadBtn.addMouseListener(this);
        settingsBtn.addMouseListener(this);
        logoutBtn.addMouseListener(this);

        //customize buttons
        searchBtn.setIcon(new ImageIcon("search.png"));
        searchBtn.setOpaque(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setBorderPainted(false);
        homeBtn.setIcon(new ImageIcon("home.png"));
        homeBtn.setOpaque(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setBorderPainted(false);
        uploadBtn.setIcon(new ImageIcon("upload.png"));
        uploadBtn.setOpaque(false);
        uploadBtn.setContentAreaFilled(false);
        uploadBtn.setBorderPainted(false);
        settingsBtn.setIcon(new ImageIcon("settings.png"));
        settingsBtn.setOpaque(false);
        settingsBtn.setContentAreaFilled(false);
        settingsBtn.setBorderPainted(false);
        logoutBtn.setIcon(new ImageIcon("logout.png"));
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);

        center.setBackground(Color.WHITE);
        homePanel.add(feed,BorderLayout.CENTER);
        homePanel.add(top,BorderLayout.NORTH);
        homePanel.add(left,BorderLayout.WEST);
        homePanel.add(right,BorderLayout.EAST);
        homePanel.add(bottom,BorderLayout.SOUTH);

        top.add(buttonPanel);
        top.add(logoPanel);
        top.add(searchPanel);

        searchPanel.add(jSearch);
        jSearch.setFont(jSearch.getFont().deriveFont(Font.PLAIN, 20f));

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.RELATIVE;
        searchPanel.add(searchBtn,c);
        searchPanel.add(logoutBtn,c);

        buttonPanel.add(homeBtn);
        buttonPanel.add(uploadBtn,c);
        buttonPanel.add(settingsBtn,c);
        logoPanel.add(new JLabel(new ImageIcon("logo.png")));

        test1.setPreferredSize(new Dimension(720,480));
        test2.setPreferredSize(new Dimension(720,480));
        test3.setPreferredSize(new Dimension(720,480));
        test4.setPreferredSize(new Dimension(720,480));

        c.gridwidth = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10,0,10,0);
        center.add(test1,c);
        center.add(test2,c);
        c.gridheight = GridBagConstraints.RELATIVE;
        center.add(test3,c);
        center.add(test4,c);
    }

    public JPanel getPanel()
    {
        return this.homePanel;
    }

    public JButton getDefaultButton()
    {
        return this.searchBtn;
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();

        if(b.equals(uploadBtn))
        {
            repo.upload();
        }
        if(b.equals(logoutBtn))
        {
            repo.logout();
        }
    }

    public void mouseEntered(MouseEvent e)
    {
        JButton b = (JButton)e.getSource();
        b.setBorderPainted(true);
    }

    public void mouseExited(MouseEvent e)
    {
        JButton b = (JButton)e.getSource();
        b.setBorderPainted(false);
    }

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e)
    {

    }
}
