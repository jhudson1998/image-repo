import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LoginUI extends JFrame implements ActionListener
{
    //initialize objects
    private Repo repo;
    private JPanel loginPanel;
    private JPanel center,fields,buttons;
    private JTextField jUser;
    private JPasswordField jPass;
    private TextPrompt user, pass;
    private JButton login,register;

    public LoginUI(Repo repoObj)
    {
        //instantiate objects
        this.repo = repoObj;
        this.loginPanel = new JPanel();
        this.center = new JPanel(new BorderLayout());
        this.fields = new JPanel(new FlowLayout());
        this.buttons = new JPanel(new FlowLayout());
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
        loginPanel.setLayout(new BorderLayout());
        loginPanel.add(center,BorderLayout.CENTER);
        loginPanel.add(new JLabel(new ImageIcon("logo.png")),BorderLayout.NORTH);
        jUser.setFont(jUser.getFont().deriveFont(Font.PLAIN, 20f));
        jPass.setFont(jPass.getFont().deriveFont(Font.PLAIN, 20f));
        fields.add(jUser);
        fields.add(jPass);
        buttons.add(login);
        buttons.add(register);
        center.add(fields,BorderLayout.NORTH);
        center.add(buttons,BorderLayout.CENTER);
    }

    public JPanel getPanel()
    {
        return this.loginPanel;
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
            repo.register();
        }
    }
}
