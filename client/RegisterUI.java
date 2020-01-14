import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class RegisterUI extends JPanel implements ActionListener
{

    private Repo repo;
    private JPanel registerPanel;
    private JPanel center,p1,p2,p3,p4;
    private JTextField jFirst,jLast,jUser;
    private JPasswordField jPass;
    private TextPrompt first,last,user,pass;
    private JButton register,back;

    public RegisterUI(Repo repoObj)
    {
        this.repo = repoObj;
        this.registerPanel = new JPanel();
        this.center = new JPanel(new GridLayout(4,1));
        this.p1 = new JPanel(new FlowLayout());
        this.p2 = new JPanel(new FlowLayout());
        this.p3 = new JPanel(new FlowLayout());
        this.p4 = new JPanel(new FlowLayout());
        this.jFirst = new JTextField(16);
        this.jLast = new JTextField(16);
        this.jUser = new JTextField(16);
        this.jPass = new JPasswordField(16);
        this.first = new TextPrompt("First Name",jFirst);
        this.last = new TextPrompt("Last Name",jLast);
        this.user = new TextPrompt("Username",jUser);
        this.pass = new TextPrompt("Password",jPass);
        this.register = new JButton("Register");
        this.back = new JButton("Back");

        register.addActionListener(this);
        back.addActionListener(this);

        registerPanel.setLayout(new BorderLayout());
        jFirst.setFont(jUser.getFont().deriveFont(Font.PLAIN, 20f));
        jLast.setFont(jUser.getFont().deriveFont(Font.PLAIN, 20f));
        jUser.setFont(jUser.getFont().deriveFont(Font.PLAIN, 20f));
        jPass.setFont(jUser.getFont().deriveFont(Font.PLAIN, 20f));
        registerPanel.add(new JLabel(new ImageIcon("logo.png")),BorderLayout.NORTH);
        registerPanel.add(center,BorderLayout.CENTER);
        center.add(p1);
        center.add(p2);
        center.add(p3);
        center.add(p4);
        p1.add(jFirst);
        p1.add(jLast);
        p2.add(jUser);
        p2.add(jPass);
        p3.add(back);
        p3.add(register);
    }

    public JPanel getPanel()
    {
        return this.registerPanel;
    }

    public void clearEntries()
    {
        jFirst.setText("");
        jLast.setText("");
        jUser.setText("");
        jPass.setText("");
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();

        if(b.equals(back))
        {
            clearEntries();
            repo.login();
        }
        else if(b.equals(register))
        {
            repo.checkRegister(jFirst.getText(),jLast.getText(),jUser.getText(),new String(jPass.getPassword()));
        }
    }
}
