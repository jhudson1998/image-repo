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

    public void startRepo()
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
            soc = new Socket("142.134.217.255",9807);
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
