
package notepad;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
//import javax.swing.JFrame;
//import java.awt.Image;
//import java.awt.event.KeyEvent;
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.ImageIcon;
//import javax.swing.JMenuItem;
//import javax.swing.KeyStroke;



public class Notepad extends JFrame implements ActionListener
{
    JTextArea area = new JTextArea();
    String text;
    Notepad()
    {
        setTitle("Notepad");// setting the title
        
        
        
        area.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,25));
        add(area);
        area.setLineWrap(true);
        area.setBorder(BorderFactory.createEmptyBorder());
        area.setWrapStyleWord(true);
        
        
        
        JScrollPane bar = new JScrollPane(area);
        add(bar);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// setting size of window
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));// adding icon of notepad
        Image icon = notepadIcon.getImage();// creating an object of image class
        setIconImage(icon);// setting the icon 
        
        JMenuBar menubar = new JMenuBar();// to create a menubar
        menubar.setBackground(Color.white);
        JMenu file = new JMenu("File");// to create a menu option file
        file.setFont(new Font("AERIAL",Font.PLAIN,14));
        menubar.add(file);//adding menu to menu bar
       
        
        setJMenuBar(menubar);// adding menubar to screen

        JMenuItem newDoc  = new JMenuItem("New");
        newDoc.addActionListener(this);
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));// TO ADD A ctrl+N shortcut to create a new file

        JMenuItem open  = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        
        JMenuItem save  = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        
        JMenuItem print  = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        
        JMenuItem  exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit    .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
        
        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);   
        
        
        JMenu edit = new JMenu("Edit");// to create a menu option edit
        edit.setFont(new Font("AERIAL",Font.PLAIN,14));
        
        JMenuItem copy  = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        
        JMenuItem cut  = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        
        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        
        menubar.add(edit);//adding menu to menu bar
         
         
         
         
         
        JMenu helpMenu = new JMenu("Help");// to create a menu option view
        helpMenu.setFont(new Font("AERIAL",Font.PLAIN,14));
        
        JMenuItem help = new JMenuItem("About");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        
        helpMenu.add(help);
        
        menubar.add(helpMenu);//adding menu to menu bar
        
        setVisible(true);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("New"))
        {
            area.setText("");
        }
        else if (ae.getActionCommand().equals("Open"))
        {
            JFileChooser choose = new JFileChooser();
            choose.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("only .txt Files", "txt");
            FileNameExtensionFilter htmlFilter = new FileNameExtensionFilter("HTML Files (*.html)", "html");
            FileNameExtensionFilter cssFilter = new FileNameExtensionFilter("CSS Files (*.css)", "css");
    
            choose.addChoosableFileFilter(restrict);
            choose.addChoosableFileFilter(htmlFilter);
            choose.addChoosableFileFilter(cssFilter);
            
            int action =choose.showOpenDialog(this);
            
            if(action != choose.APPROVE_OPTION)
            {
                return;
            }
            
            File file = choose.getSelectedFile();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null );
            }
            catch(Exception E)
            {
            E.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Save"))
        {
            JFileChooser saveAs = new JFileChooser();
            saveAs.setApproveButtonText("Save");
            
            int action =saveAs.showOpenDialog(this);
            
            if(action != saveAs.APPROVE_OPTION)
            {
                return;
            }
            
            File filename = new File(saveAs.getSelectedFile()+ ".txt");
            BufferedWriter outfile = null;
            
            try
            {
                outfile = new BufferedWriter(new FileWriter(filename));
                area.write(outfile);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        else if (ae.getActionCommand().equals("Print"))
        {
            try
            {
                area.print();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Exit"))
        {
               System.exit(0); 
        }
        else if(ae.getActionCommand().equals("Copy"))
        {
            text =area.getSelectedText();   
        }
        else if (ae.getActionCommand().equals("Paste"))
        {
            area.insert(text, area.getCaretPosition());
            
        }
        else if(ae.getActionCommand().equals("Cut"))
        {
            text =area.getSelectedText();
            area.replaceRange("",area.getSelectionStart(),area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Select All"))
        {
            area.selectAll();
        }
        else if(ae.getActionCommand().equals("About"))
        {
            new About().setVisible(true);
        }
        
    }

    public static void main(String[] args) 
    {
        Notepad notepad = new Notepad();
        
    }
    
}
