import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LoginUI extends JFrame implements ActionListener
{
    //initialize objects
    private Repo repo;
    private JFrame loginFrame;
    private JPanel center,fields,buttons;
    private JTextField jUser;
    private JPasswordField jPass;
    private TextPrompt user, pass;
    private JButton login,register;

    public LoginUI(Repo repoObj)
    {
        //instantiate objects
        this.repo = repoObj;
        this.loginFrame = new JFrame("Image Repo Login");
        this.center = new JPanel(new BorderLayout());
        this.fields = new JPanel(new FlowLayout());
        this.buttons = new JPanel(new FlowLayout());
        this.jUser = new JTextField(16);
        this.jPass = new JPasswordField(16);
        this.user = new TextPrompt("Username",jUser);
        this.pass = new TextPrompt("Password",jPass);
        this.login = new JButton("  Login  ");
        this.register = new JButton("Register");

    }

    public void setup()
    {
        //add action listeners to buttons
        login.addActionListener(this);
        register.addActionListener(this);

        //setup frame
        loginFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loginFrame.setSize(1280,720);
        loginFrame.setResizable(false);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.add(center,BorderLayout.CENTER);
        loginFrame.add(new JLabel(new ImageIcon("logo.png")),BorderLayout.NORTH);
        jUser.setFont(jUser.getFont().deriveFont(Font.PLAIN, 20f));
        jPass.setFont(jPass.getFont().deriveFont(Font.PLAIN, 20f));
        fields.add(jUser);
        fields.add(jPass);
        buttons.add(login);
        buttons.add(register);
        center.add(fields,BorderLayout.NORTH);
        center.add(buttons,BorderLayout.CENTER);
        loginFrame.getRootPane().setDefaultButton(login);
        loginFrame.setVisible(true);
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
            loginFrame.dispose();
            repo.register();
        }
    }
}
