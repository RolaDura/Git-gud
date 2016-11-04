import java.io.*;
import java.awt.*;
import javax.swing.*;
public class Enemy 
{private String name[] = new String[100];
   private int[]level = new int[100];
   private int[]atk = new int[100];
   private int[]def = new int[100];
   private int[]hp = new int[100];
   private int[]xp = new int[100];
   private int[]money = new int[100];
   private int[]met = new int[100];
   private ImageIcon[] enemyImage = new ImageIcon[100];
   public Enemy() throws IOException
   {String lvl, h, at, df, x, mn, mt, str;
      BufferedReader read = new BufferedReader(new FileReader("Enemy.txt"));
      str = read.readLine();
      for(int i = 0; i < name.length; i++)
      {name[i] = read.readLine();
         lvl = read.readLine();
         h = read.readLine();
         at = read.readLine();
         df = read.readLine();
         x = read.readLine();
         mn = read.readLine();
         mt = read.readLine();
         str = read.readLine();
         level[i] = Integer.parseInt(lvl);
         hp[i] = Integer.parseInt(h);
         atk[i] = Integer.parseInt(at);
         def[i] = Integer.parseInt(df);
         xp[i] = Integer.parseInt(x);
         money[i] = Integer.parseInt(mn);
         if(level[i]%10 == 1)
            enemyImage[i] = new ImageIcon("Image/Goblin.jpg", "Goblin.jpg");
         else if(level[i]%10 == 2)
            enemyImage[i] = new ImageIcon("Image/Slime.jpg", "Slime.jpg");
         else if(level[i]%10 == 3)
            enemyImage[i] = new ImageIcon("Image/Thief.jpg", "Thief.jpg");
         else if(level[i]%10 == 4)
            enemyImage[i] = new ImageIcon("Image/Kobald.jpg", "Kobald.jpg");
         else if(level[i]%10 == 5)
            enemyImage[i] = new ImageIcon("Image/Kobra.jpg", "Kobra.jpg");
         else if(level[i]%10 == 6)
            enemyImage[i] = new ImageIcon("Image/Rat.jpg", "Rat.jpg");
         else if(level[i]%10 == 7)
            enemyImage[i] = new ImageIcon("Image/Knight.jpg", "Knight.jpg");
         else if(level[i]%10 == 8)
            enemyImage[i] = new ImageIcon("Image/Cyclops.jpg", "Cyclops.jpg");
         else if(level[i]%10 == 9)
            enemyImage[i] = new ImageIcon("Image/Golem.jpg", "Golem.jpg");
         else if(level[i]%10 == 0)
            enemyImage[i] = new ImageIcon("Image/Dragon.jpg", "Dragon.jpg");
      }
      try{
         readMet();
      }
      catch(IOException e){}
      read.close();
   }
   public void readMet()throws IOException
   {BufferedReader read = new BufferedReader(new FileReader("Met.txt"));
      String mt;
      for(int i = 0; i < met.length; i++)
      {mt = read.readLine();
         met[i] = Integer.parseInt(mt);}
      read.close();}

   public void writeMet()throws IOException
   {PrintWriter write = new PrintWriter(new FileWriter("Met.txt"));
      for(int i = 0; i < met.length; i++)
         write.println(met[i]);
      write.close();}
   
   public void met(int i)
   {met[i] = 1;
   }
   
   public String getName(int i)
   {
      return name[i];}
      
   public int getLevel(int i)
   {
      return level[i];}
      
   public int getHp(int i)
   {
      return hp[i];}
      
   public int getAtk(int i)
   {
      return atk[i];}
      
   public int getDef(int i)
   {
      return def[i];}
      
   public int getMoney(int i)
   {
      return money[i] ;}
      
   public int getMet(int i)
   {
      return met[i];}
      
   public int getXp(int i)
   {
      return xp[i];}
      
   public ImageIcon getMonster(int i)
   {
      return enemyImage[i];
   }
}