import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class RegisterUI extends JPanel implements ActionListener
{

    private Repo repo;
    private JPanel registerPanel;
    private JPanel center,top,left,right,bottom;
    private JTextField jFirst,jLast,jUser;
    private JPasswordField jPass;
    private TextPrompt first,last,user,pass;
    private JButton register,back;

    public RegisterUI(Repo repoObj)
    {
        this.repo = repoObj;
        this.registerPanel = new JPanel();
        this.center = new JPanel(new GridBagLayout());
        this.top = new JPanel(new GridBagLayout());
        this.left = new JPanel();
        this.right = new JPanel();
        this.bottom = new JPanel();
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
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,0,10,0);
        top.add(new JLabel(new ImageIcon(getClass().getResource("images/logo.png"))),c);
        registerPanel.add(top,BorderLayout.NORTH);
        registerPanel.add(center,BorderLayout.CENTER);
        registerPanel.add(left,BorderLayout.WEST);
        registerPanel.add(right,BorderLayout.EAST);
        registerPanel.add(bottom,BorderLayout.SOUTH);

        center.setBackground(Color.WHITE);
        c.insets = new Insets(10,0,0,10);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        center.add(jFirst,c);
        c.gridy = 1;
        center.add(jLast,c);
        c.insets = new Insets(10,10,0,0);
        c.gridy = 0;
        c.gridx = 4;
        center.add(jUser,c);
        c.gridy = 1;
        center.add(jPass,c);

        c.insets = new Insets(40,0,0,0);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        center.add(back,c);
        c.gridx = 6;
        center.add(register,c);
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
