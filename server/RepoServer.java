import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;
import java.sql.*;

public class RepoServer
{
    private Socket soc;
    private ServerSocket ss;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private InputStream inputStream;
    private DataInputStream dataInputStream;


    public RepoServer()
    {
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

                        case "register":
                            register();
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
            System.out.println(passin);
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
                rset = stmt.executeQuery("SELECT first, last FROM user WHERE username = " + "'" + userin + "'");
                String first = null;
                String last = null;
                while(rset.next())
                {
                    first = rset.getString("first");
                    last = rset.getString("last");
                }
                dataOutputStream.writeUTF(first);
                dataOutputStream.writeUTF(last);
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

    private void register()
    {
        try
        {
            String firstin = dataInputStream.readUTF();
            String lastin = dataInputStream.readUTF();
            String userin = dataInputStream.readUTF();
            String passin = dataInputStream.readUTF();
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/imagerepo?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "Josiah;33");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT first FROM user WHERE username = " + "'" + userin + "'");
            if(rset.next())
            {
                dataOutputStream.writeUTF("failure");
            }
            else
            {
                stmt.executeUpdate("INSERT INTO user VALUES(" + "'" + userin + "'" + "," + "'" + passin + "'" + "," + "'" + firstin + "'" + "," + "'" + lastin + "');");
                new File("C:/Users/James/Desktop/Database/users/" + userin).mkdirs();
                dataOutputStream.writeUTF("success");
            }
            soc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
