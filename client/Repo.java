import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Repo extends JFrame
{
    private JFrame frame;
    private LoginUI loginUI;
    private RegisterUI registerUI;
    private HomeUI homeUI;
    private PhotoUI photoUI;
    private String first, last;
    private User currUser;
    private Socket soc;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private InputStream inputStream;
    private DataInputStream dataInputStream;

    public Repo()
    {
        frame = new JFrame("Image Repo");
        loginUI = new LoginUI(this);
        registerUI = new RegisterUI(this);
        homeUI = new HomeUI(this);
        photoUI = new PhotoUI(this);
        currUser = null;
        first = null;
        last = null;
    }

    public void setup()
    {
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280,720);
        frame.add(loginUI.getPanel());
        frame.getRootPane().setDefaultButton(loginUI.getDefaultButton());
        frame.setVisible(true);
    }

    public void login()
    {
        frame.setContentPane(loginUI.getPanel());
        frame.getRootPane().setDefaultButton(loginUI.getDefaultButton());
        frame.repaint();
        frame.revalidate();
    }

    public void register()
    {
        frame.setContentPane(registerUI.getPanel());
        frame.repaint();
        frame.revalidate();
    }

    public void home()
    {
        frame.setContentPane(homeUI.getPanel());
        frame.getRootPane().setDefaultButton(homeUI.getDefaultButton());
        frame.repaint();
        frame.revalidate();
    }

    public void upload()
    {
        frame.setContentPane(photoUI.getPanel());
        frame.repaint();
        frame.revalidate();
    }

    public void logout()
    {
        frame.setContentPane(loginUI.getPanel());
        frame.getRootPane().setDefaultButton(loginUI.getDefaultButton());
        frame.repaint();
        frame.revalidate();
        currUser = null;
    }

    public void checkLogin(String user,String pass)
    {
        if(user.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter a username and password");
        }
        else
        {
            try
            {
                createSocket();
                dataOutputStream.writeUTF("login");
                dataOutputStream.writeUTF(user);
                dataOutputStream.writeUTF(pass);
                String result = dataInputStream.readUTF();
                if(result.equals("success"))
                {
                    first = dataInputStream.readUTF();
                    last = dataInputStream.readUTF();
                    currUser = new User(user, first, last);
                    loginUI.clearEntries();
                    home();
                }
                else if(result.equals("failure"))
                {
                    JOptionPane.showMessageDialog(null, "Incorrect username and/or password");
                }
                soc.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void checkRegister(String first, String last, String user, String pass)
    {
        if(first.equals("") || last.equals("") || user.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please fill out all of the fields");
        }
        else if(first.length() > 10 || !first.matches("[a-zA-Z]+"))
        {
            JOptionPane.showMessageDialog(null, "First name must only contain a-z and be a length of 10 or less");
        }
        else if(last.length() > 10 || !last.matches("[a-zA-Z]+"))
        {
            JOptionPane.showMessageDialog(null, "Last name must only contain a-z and be a length of 10 or less");
        }
        else if(user.length() > 16 || !user.matches("[a-zA-Z0-9]+"))
        {
            JOptionPane.showMessageDialog(null, "Username must only contain a-z, 0-9 and be 16 or less letters");
        }
        else if(pass.length() > 16 || !pass.matches("[a-zA-Z0-9]+"))
        {
            JOptionPane.showMessageDialog(null, "Password must only contain a-z, 0-9 and be 16 or less letters");
        }
        else
        {
            try
            {
                createSocket();
                dataOutputStream.writeUTF("register");
                dataOutputStream.writeUTF(first);
                dataOutputStream.writeUTF(last);
                dataOutputStream.writeUTF(user);
                dataOutputStream.writeUTF(pass);
                String result = dataInputStream.readUTF();
                if(result.equals("success"))
                {
                    JOptionPane.showMessageDialog(null, "Registration successful");
                    login();
                }
                else if(result.equals("failure"))
                {
                    JOptionPane.showMessageDialog(null, "Username already registered");
                }
                soc.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void createSocket()
    {
        try
        {
            soc = new Socket("192.168.2.114",9807);
            outputStream = soc.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            inputStream = soc.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
