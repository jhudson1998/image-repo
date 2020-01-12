import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;
import java.sql.*;

public class RepoServer
{
    private Socket soc;
    private ServerSocket ss;
    private HashMap<String,String> users;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private InputStream inputStream;
    private DataInputStream dataInputStream;


    public RepoServer()
    {
        users = new HashMap<String,String>();
        users.put("username","password");
        try
        {
            System.out.println("Waiting for clients...");
            ss = new ServerSocket(9807);
            while(true)
            {
                soc = ss.accept();
                System.out.println("Connection established");
                outputStream = soc.getOutputStream();
                dataOutputStream = new DataOutputStream(outputStream);
                inputStream = soc.getInputStream();
                dataInputStream = new DataInputStream(inputStream);
                try
                {
                    String control = dataInputStream.readUTF();
                    switch(control)
                    {
                        case "login":
                            login();
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void login()
    {
        try
        {
            String userin = dataInputStream.readUTF();
            String passin = dataInputStream.readUTF();
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/imagerepo?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "Josiah;33");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT password FROM user WHERE username = " + "'" + userin + "'");
            String passdb = "";
            while(rset.next())
            {
                passdb = rset.getString("password");
            }
            if (passin.equals(passdb))
            {
                dataOutputStream.writeUTF("success");
            }
            else
            {
                dataOutputStream.writeUTF("failure");
            }
            soc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
