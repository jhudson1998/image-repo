import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Repo
{
    private LoginUI loginUI;
    private RegisterUI registerUI;
    private HomeUI homeUI;
    private PhotoUI photoUI;
    private User currUser;
    private Socket soc;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private InputStream inputStream;
    private DataInputStream dataInputStream;

    public Repo()
    {
        loginUI = new LoginUI(this);
        registerUI = new RegisterUI(this);
        homeUI = new HomeUI(this);
        photoUI = new PhotoUI(this);
        currUser = null;
    }

    public void login()
    {
        loginUI.setup();
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
                    JOptionPane.showMessageDialog(null, "Login successful");
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

    public void register()
    {
        registerUI.setup();
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
