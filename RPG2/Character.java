import java.io.*;
import javax.swing.*;
import java.awt.*;
public class Character
{
   private int atk, def, level, stage, xp, hp, money,weapon,armor;
   private String name, gender, job, weaponName, armorName;
   private ImageIcon characterImage;
   private int item[] = new int[4];
   public Character ()
   {
      atk = 0;
      def = 0;
      level = 1;
      stage = 0;
      xp = 0;
      hp = 0;
      money = 0;
      name = "";
      job = "Assassin";
   }

  
   public Character (String n, String c, String g)
   {
      name = n;
      job = c;
      hp = 200;
      switch (job)
      {
         case "Assassin":
            atk = 20;
            def = 5;
            break;
         case "Warrior":
            atk = 10;
            def = 10;
            break;
         case "Paladin":
            atk = 5;
            def = 20;
            break;
      }
      level = 1;
      stage = 1;
      money = 1000;
      gender = g;
      xp = 0;
      for(int i = 0; i < item.length; i++) item[i] = 1;
      characterImage = new ImageIcon("Image/" + gender + job + ".jpg", gender + job + ".jpg");
      weapon = 10;
      armor = 10;
      weaponName = "Dagger";
      armorName = "Cloth";    
   }
   public ImageIcon getHero()
   {
      return characterImage;
   }

   public String getName ()
   {
      return name;
   }


   public String getGender ()
   {
      return gender;
   }


   public int getMoney ()
   {
      return money;
   }


   public int getHp ()
   {
      return hp;
   }

   public int getAtk ()
   {
      int totalAtk = 0;
      switch (job)
      {
         case "Assassin":
            totalAtk = (int)((atk + weapon)*1.5);
            break;
         case "Warrior":
            totalAtk = (int)((atk + weapon)*1.2);
            break;
         case "Paladin":
            totalAtk = atk + weapon;
      }
      return totalAtk;
   }


   public int getDef ()
   {
      int totalDef = 0;
      switch (job)
      {  
         case "Assassin":
            totalDef = def + armor;
            break;
         case "Warrior":
            totalDef = (int)((def + armor)* 1.2);
            break;
         case "Paladin":
            totalDef = (int)((def + armor) * 1.5);
      }
      return totalDef;
   }
   
   public int getWeapon()
   {
      return weapon;
   }
   
   public int getArmor()
   {
      return armor;
   }
   
   public String getWeaponName()
   {
      return weaponName;
   }
   
   public String getArmorName()
   {
      return armorName;
   }

   public int getLevel ()
   {
      return level;
   }


   public int getStage ()
   {
      return stage;
   }


   public int getXp ()
   {
      return xp;
   }


   public String getJob ()
   {
      return job;
   }


   public void setName (String n)
   {
      name = n;
   }


   public void setHp (int h)
   {
      hp += h;
   }


   public void setAtk (int a)
   {
      atk += a;
   }


   public void setDef (int d)
   {
      def += d;
   }


   public void levelUp (int x, int m)
   {
      xp += x;
      money += m;
      while(xp > level * 100)
      {  
         xp = xp - level*100;
         level++;
         hp += 100;
         if (job.compareTo("Assassin")==0)
         {
            atk += 20;
            def += 5;
         }
         else if (job.compareTo("Warrior")==0)
         {
            atk += 10;
            def += 10;
         }
         else if (job.compareTo("Paladin")==0)
         {
            atk += 5;
            def += 20;
         }
      }
   }


   public void writeSave (int cha) throws IOException
   {
      PrintWriter save = new PrintWriter (new FileWriter ("Character" + cha + ".txt"));
      save.println (name);
      save.println (gender);
      save.println (job);
      save.println (level);
      save.println (xp);
      save.println (money);
      save.println (stage);
      save.println (hp);
      save.println (atk);
      save.println (def);
      save.println (weaponName);
      save.println (weapon);
      save.println (armorName);
      save.println (armor);
      for (int i = 0; i < item.length; i++)
         save.println (item[i]);
      save.close ();
   }


   public void loadSave (int cha) throws IOException
   {
      String lvl, at, df, stg, it[], x, h, mon,we,ar;
      it = new String[4];
      BufferedReader load = new BufferedReader (new FileReader ("Character" + cha + ".txt"));
      name = load.readLine ();
      gender = load.readLine ();
      job = load.readLine ();
      lvl = load.readLine ();
      x = load.readLine ();
      mon = load.readLine ();
      stg = load.readLine ();
      h = load.readLine ();
      at = load.readLine ();
      df = load.readLine ();
      weaponName = load.readLine ();
      we = load.readLine ();   
      armorName = load.readLine ();
      ar = load.readLine (); 
      for (int i = 0; i < it.length; i++)
      { 
         it[i] = load.readLine ();
      }
      level = Integer.parseInt (lvl);
      xp = Integer.parseInt (x);
      money = Integer.parseInt (mon);
      stage = Integer.parseInt (stg);
      hp = Integer.parseInt (h);
      atk = Integer.parseInt (at);
      def = Integer.parseInt (df);
      weapon = Integer.parseInt(we);
      armor = Integer.parseInt(ar);
      for (int i = 0; i < item.length; i++)
         item[i] =  Integer.parseInt(it[i]);
      characterImage = new ImageIcon("Image/" + gender + job + ".jpg", gender + job + ".jpg");
      load.close ();
   }
   public void setWeapon(int i)
   {
      weapon = i;
   }
   
   public void setArmor(int i)
   {
      armor = i;
   }
   
   public void setWeaponName(String n)
   {
      weaponName = n;
   }
   
   public void setArmorName(String n)
   {
      armorName = n;
   }

   public void spend(int m)
   {
      money -= m;
   }
   
   public void buyItem(int i)
   {
      item[i] += 1;
   }
   
   public void useItem(int i)
   {
      item[i] -= 1;
   }
   
   public int getItem(int i)
   {
      return item[i];
   }
}
