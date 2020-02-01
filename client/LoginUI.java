import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LoginUI extends JPanel implements ActionListener
{
    //initialize objects
    private Repo repo;
    private JPanel loginPanel;
    private JPanel center,top,left,right,bottom;
    private JTextField jUser;
    private JPasswordField jPass;
    private TextPrompt user, pass;
    private JButton login,register;

    public LoginUI(Repo repoObj)
    {
        //instantiate objects
        this.repo = repoObj;
        this.loginPanel = new JPanel(new BorderLayout());
        this.top = new JPanel(new GridBagLayout());
        this.left = new JPanel();
        this.right = new JPanel();
        this.bottom = new JPanel();
        this.center = new JPanel(new GridBagLayout());
        this.jUser = new JTextField(16);
        this.jPass = new JPasswordField(16);
        this.user = new TextPrompt("Username",jUser);
        this.pass = new TextPrompt("Password",jPass);
        this.login = new JButton("  Login  ");
        this.register = new JButton("Register");

        //add action listeners to buttons
        login.addActionListener(this);
        register.addActionListener(this);

        //setup frame
        center.setBackground(Color.WHITE);
        loginPanel.add(center,BorderLayout.CENTER);
        loginPanel.add(top,BorderLayout.NORTH);
        loginPanel.add(left,BorderLayout.WEST);
        loginPanel.add(right,BorderLayout.EAST);
        loginPanel.add(bottom,BorderLayout.SOUTH);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,0,10,0);
        top.add(new JLabel(new ImageIcon(getClass().getResource("images/logo.png"))),c);
        jUser.setFont(jUser.getFont().deriveFont(Font.PLAIN, 20f));
        jPass.setFont(jPass.getFont().deriveFont(Font.PLAIN, 20f));
        c.insets = new Insets(10,0,0,0);
        c.gridwidth = 3;
        c.gridy = 0;
        center.add(jUser,c);
        c.gridy = 1;
        center.add(jPass,c);
        c.gridwidth = 1;
        c.gridy = 2;
        c.gridx = 2;
        center.add(login,c);
        c.insets = new Insets(5,0,0,0);
        c.gridy = 3;
        center.add(register,c);
    }

    public JPanel getPanel()
    {
        return this.loginPanel;
    }

    public void clearEntries()
    {
        jUser.setText("");
        jPass.setText("");
    }

    public JButton getDefaultButton()
    {
        return this.login;
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();

        if(b.equals(login))
        {
            repo.checkLogin(jUser.getText(),new String(jPass.getPassword()));
        }
        else if(b.equals(register))
        {
            clearEntries();
            repo.register();
        }
    }
}
