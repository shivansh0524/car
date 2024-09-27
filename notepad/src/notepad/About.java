
package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class About extends JFrame implements ActionListener
{
    About()
    {   
        setBounds(400,120,600,600);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windows.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon  = new JLabel(i3);
        headerIcon.setBounds(80,5,400,250);
        add(headerIcon);


        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
        Image i22 = i11.getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel Icon  = new JLabel(i33);
        Icon.setBounds(130,240,70,70);
        add(Icon);       
        
        JLabel text = new JLabel("<html> Code for notepad <br>  Version 0.1.0 (OS Build JAVA) <br> Notepad Clone . All Rights Reserved <br> </html>");
        text.setBounds(240,120,500,300);
        text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN, 16));
        
        
        add(text);
        JButton b1 = new JButton("OK");
        b1.setBounds(300,390 ,100,25);
        b1.addActionListener(this);
        add(b1);
        
        
        setVisible(true);
        
    }
    
    
//    @Override
    public void actionPerformed(ActionEvent ae)
    {
        this.setVisible(false);
    }
    
    public static void main(String[] args)
    {
        new About();
    }
       
    
}
