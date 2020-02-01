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
    private ImageIcon test1,test2,test3,test4;

    private final int IMG_WIDTH = 960;

    public HomeUI(Repo repoObj)
    {
        //instantiate objects
        this.repo = repoObj;
        this.homePanel = new JPanel(new BorderLayout());
        this.center = new JPanel(new GridBagLayout());
        this.top = new JPanel(new GridLayout(1,3));
        this.logoPanel = new JPanel(new GridBagLayout());
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
        this.test1 = new ImageIcon(getClass().getResource("images/test1.jpeg"));
        this.test2 = new ImageIcon(getClass().getResource("images/test2.jpeg"));
        this.test3 = new ImageIcon(getClass().getResource("images/test3.jpg"));
        this.test4 = new ImageIcon(getClass().getResource("images/test4.jpg"));

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
        searchBtn.setIcon(new ImageIcon(getClass().getResource("images/search.png")));
        searchBtn.setOpaque(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setBorderPainted(false);
        homeBtn.setIcon(new ImageIcon(getClass().getResource("images/home.png")));
        homeBtn.setOpaque(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setBorderPainted(false);
        uploadBtn.setIcon(new ImageIcon(getClass().getResource("images/upload.png")));
        uploadBtn.setOpaque(false);
        uploadBtn.setContentAreaFilled(false);
        uploadBtn.setBorderPainted(false);
        settingsBtn.setIcon(new ImageIcon(getClass().getResource("images/settings.png")));
        settingsBtn.setOpaque(false);
        settingsBtn.setContentAreaFilled(false);
        settingsBtn.setBorderPainted(false);
        logoutBtn.setIcon(new ImageIcon(getClass().getResource("images/logout.png")));
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
        c.insets = new Insets(10,0,10,0);
        logoPanel.add(new JLabel(new ImageIcon(getClass().getResource("images/logo.png"))),c);

        if(test1.getIconWidth() > IMG_WIDTH)
        {
            test1 = repo.imageScaleWidth(test1, IMG_WIDTH);
        }
        if(test2.getIconWidth() > IMG_WIDTH)
        {
            test2 = repo.imageScaleWidth(test2, IMG_WIDTH);
        }
        if(test3.getIconWidth() > IMG_WIDTH)
        {
            test3 = repo.imageScaleWidth(test3, IMG_WIDTH);
        }
        if(test4.getIconWidth() > IMG_WIDTH)
        {
            test4 = repo.imageScaleWidth(test4, IMG_WIDTH);
        }

        c.insets = new Insets(40,0,0,0);
        c.gridy = 0;
        center.add(new JLabel(test1),c);
        c.gridy = 1;
        center.add(new JLabel(test2),c);
        c.gridy = 2;
        center.add(new JLabel(test3),c);
        c.gridy = 3;
        c.insets = new Insets(40,0,40,0);
        center.add(new JLabel(test4),c);
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
