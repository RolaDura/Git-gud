import java.io.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
public class Test extends JFrame
{private JPanel panel;
   private Character character;
   private ImageIcon image;
   public Test()
   {setSize(400,400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("FDF");
      setLayout(new BorderLayout());
      character = new Character();
      try{
         character.loadSave(0);
      }
      catch(IOException e){}
      image = new ImageIcon("F:/RPG/Image/FemaleAssassin.jpg", "FemaleAssassin.jpg");
      JLabel label = new JLabel(character.getHero());
      add(label, BorderLayout.WEST);
      setVisible(true);
   }
   public static void main(String[] args) throws IOException
   {new Test();
   }
}