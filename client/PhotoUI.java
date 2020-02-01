import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

public class PhotoUI extends JPanel implements ActionListener
{
    Repo repo;
    JPanel photoPanel;
    JPanel top,center,left,right,bottom,half1,half2;
    JButton backBtn,uploadBtn,chooseBtn;
    JLabel choice,chosen;
    final JFileChooser fc;
    FileNameExtensionFilter filter;
    JCheckBox priv,pub;
    boolean pubBool;

    public PhotoUI(Repo repoObj)
    {
        //instantiate objects
        this.repo = repoObj;
        this.photoPanel = new JPanel(new BorderLayout());
        this.top = new JPanel(new GridBagLayout());
        this.center = new JPanel(new GridLayout(1,2));
        this.left = new JPanel();
        this.right = new JPanel();
        this.bottom = new JPanel(new GridBagLayout());
        this.half1 = new JPanel(new GridBagLayout());
        this.half2 = new JPanel(new GridBagLayout());
        this.backBtn = new JButton("Back");
        this.uploadBtn = new JButton("Upload");
        this.chooseBtn = new JButton("Choose a photo");
        this.choice = new JLabel("                                      ");
        this.chosen = new JLabel();
        this.fc = new JFileChooser();
        this.filter = new FileNameExtensionFilter("PNG & JPEG", "jpg", "jpeg", "png");
        this.priv = new JCheckBox("Private");
        this.pub = new JCheckBox("Public");
        this.pubBool = true;

        //add listeners
        backBtn.addActionListener(this);
        uploadBtn.addActionListener(this);
        chooseBtn.addActionListener(this);
        priv.addActionListener(this);
        pub.addActionListener(this);

        //styling
        center.setBackground(Color.WHITE);
        half1.setBackground(Color.WHITE);
        half2.setBackground(Color.WHITE);

        //add components
        photoPanel.add(top,BorderLayout.NORTH);
        photoPanel.add(center,BorderLayout.CENTER);
        photoPanel.add(left,BorderLayout.WEST);
        photoPanel.add(right,BorderLayout.EAST);
        photoPanel.add(bottom,BorderLayout.SOUTH);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,0,10,5);
        bottom.add(backBtn,c);
        c.insets = new Insets(10,5,10,0);
        bottom.add(uploadBtn,c);

        center.add(half1);
        center.add(half2);

        c.insets = new Insets(10,0,10,0);
        top.add(new JLabel(new ImageIcon(getClass().getResource("images/logo.png"))),c);

        choice.setBackground(new Color(238,238,238));

        c.insets = new Insets(0,0,0,0);
        c.gridy = 0;
        half1.add(chooseBtn,c);
        c.insets = new Insets(10,0,0,0);
        c.gridy = 1;
        half1.add(choice,c);
        c.insets = new Insets(20,0,0,0);
        c.gridy = 2;
        half1.add(pub,c);
        c.insets = new Insets(10,0,0,0);
        c.gridy = 3;
        half1.add(priv,c);

        pub.setSelected(true);
        pub.setBackground(Color.WHITE);
        priv.setSelected(false);
        priv.setBackground(Color.WHITE);

        fc.setFileFilter(filter);
    }

    public JPanel getPanel()
    {
        return this.photoPanel;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof JButton)
        {
            JButton b = (JButton)e.getSource();

            if(b.equals(backBtn))
            {
                pub.setSelected(true);
                priv.setSelected(false);
                this.pubBool = true;
                this.choice.setText("                                      ");
                this.chosen.setIcon(new ImageIcon());
                repo.home();
            }
            else if(b.equals(chooseBtn))
            {


                int returnVal = fc.showOpenDialog(repo.getFrame());

                if(returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    repo.handleFile(file);
                    choice.setText(file.getPath());
                    ImageIcon photo = new ImageIcon(file.getPath());
                    photo = repo.imageScaleWidth(photo,480);
                    chosen.setIcon(photo);
                    half2.add(chosen);
                    if(photo.getIconHeight() > 270)
                    {
                        photo = repo.imageScaleHeight(photo,270);
                        chosen.setIcon(photo);
                        half2.add(chosen);
                    }
                }
            }
        }
        else if(e.getSource() instanceof JCheckBox)
        {
            JCheckBox b = (JCheckBox)e.getSource();

            if(b.equals(priv))
            {
                priv.setSelected(true);
                pub.setSelected(false);
                this.pubBool = false;
            }
            else if(b.equals(pub))
            {
                pub.setSelected(true);
                priv.setSelected(false);
                this.pubBool = true;
            }
        }
    }
}
