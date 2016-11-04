import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class StageMenu extends JFrame
{private int width = 700;
   private int height = 600;
   private JButton lvl21, lvl25, lvl29 ,lvl33, lvl37;
   private JButton lvl41, lvl45, lvl49 ,lvl53, lvl57;
   private JButton lvl61, lvl65, lvl69 ,lvl73, lvl77;
   private JButton lvl81, lvl85, lvl89 ,lvl93, lvl97;
   private JButton shop, mainMenu, save, stats;
   private JButton lowest, low, mid, high, highest;
   private JPanel area1, area2, area3, area4, area5, menuPanel, shopPanel, areaPanel, totalPanel, characterPanel;
   private Character character;
   private int cha;
   private Enemy enemy;
   private JLabel characterImage;
   private ShopList shopList;
   public StageMenu(Character ch, int c, Enemy e)
   {  
      try{
         shopList = new ShopList();
      }
      catch(IOException a){}
      enemy = e;
      character = ch;
      cha = c;
      setTitle("SwordRPG");
      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());
      buildPanel();
      buildArea1();
      buildArea2();
      buildArea3();
      buildArea4();
      buildArea5();
      buildShop();
      buildTotalPanel();
      add(menuPanel,BorderLayout.NORTH);
      add(totalPanel, BorderLayout.CENTER);
      add(shopPanel, BorderLayout.SOUTH);
      setVisible(true);
   }
   public void buildTotalPanel()
   {totalPanel = new JPanel(new GridLayout(1,2));
      buildAreaPanel();
      buildCharacterPanel();
      totalPanel.add(characterPanel);
      totalPanel.add(areaPanel);
   }
   public void buildCharacterPanel()
   {characterPanel = new JPanel(new BorderLayout());
      characterPanel.setBorder(BorderFactory.createTitledBorder("Hero"));
      characterImage = new JLabel(character.getHero());
      characterPanel.add(characterImage, BorderLayout.CENTER);
   }
   public void buildAreaPanel()
   {areaPanel = new JPanel(new GridLayout(5,1));
      areaPanel.add(area1);
      areaPanel.add(area2);
      areaPanel.add(area3);
      areaPanel.add(area4);
      areaPanel.add(area5);
   }
   
   public void buildPanel()
   {menuPanel = new JPanel(new GridLayout(1,3));
      stats = new JButton("Character Stats");
      mainMenu = new JButton("Back to MainMenu");
      save = new JButton("Save Game");
      stats.addActionListener(new HeaderListener());
      mainMenu.addActionListener(new HeaderListener());
      save.addActionListener(new HeaderListener());
      menuPanel.add(stats);
      menuPanel.add(mainMenu);
      menuPanel.add(save);
   }
   public void buildArea1()
   {area1 = new JPanel();
      area1.setBorder(BorderFactory.createTitledBorder("Area 1"));
      lowest = new JButton("Grassy Plains (LVL1 ~ LVL20)");
      lowest.addActionListener(new AreaListener());
      area1.add(lowest);
   }
   public void buildArea2()
   {area2 = new JPanel();
      area2.setBorder(BorderFactory.createTitledBorder("Area 2"));
      if (character.getLevel() < 21)
         low = new JButton("??? (Required level 21)");
      else
      {low = new JButton("Steep Mountains (LVL21 ~ LVL40)");
         low.addActionListener(new AreaListener());}
      area2.add(low);
   }
   public void buildArea3()
   {area3 = new JPanel();
      area3.setBorder(BorderFactory.createTitledBorder("Area 3"));
      if (character.getLevel() < 41)
         mid = new JButton("??? (Required level 41)");
      else
      {mid = new JButton("Icy Fields (LVL41 ~ LVL60)");
         mid.addActionListener(new AreaListener());}
      area3.add(mid);
   }
   public void buildArea4()
   {area4 = new JPanel();
      area4.setBorder(BorderFactory.createTitledBorder("Area 4"));
      if (character.getLevel() < 61)
         high = new JButton("??? (Required level 61)");
      else
      {high = new JButton("Burning Lava (LVL61 ~ LVL80)");
         high.addActionListener(new AreaListener());}
      area4.add(high);
   }
   public void buildArea5()
   {area5 = new JPanel();
      area5.setBorder(BorderFactory.createTitledBorder("Area 5"));
      if (character.getLevel() < 81)
         highest = new JButton("??? (Required level 81)");
      else
      {highest = new JButton("Hell (LVL81 ~ LVL100)");
         highest.addActionListener(new AreaListener());}
      area5.add(highest);
   }
   public void buildShop()
   {shopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      shop = new JButton("Shop");
      shop.addActionListener(new HeaderListener());
      shopPanel.add(shop);
   }
   private class HeaderListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == stats)
         {new CharacterStats();}
         else if(e.getSource() == mainMenu)
         {dispose();
            new MainMenu();}
         else if(e.getSource() == save)
         { 
            try{
               character.writeSave(cha);
               enemy.writeMet();
            }
            catch(IOException ex){}
            JOptionPane.showMessageDialog(null, "The data has been saved");}
         else if(e.getSource() == shop)
         {dispose();
            new Shop();}
      }
   }
   private class AreaListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == lowest)
         {dispose();
            new Area1();}
         else if(e.getSource() == low)
         {dispose();
            new Area2();}
         else if(e.getSource() == mid)
         {dispose();
            new Area3();}
         else if(e.getSource() == high)
         {dispose();
            new Area4();}
         else if(e.getSource() == highest)
         {dispose();
            new Area5();}
      }   
   }
   public class CharacterStats extends JFrame
   {private int width = 400;
      private int height = 500;
      private JButton exit;
      private JLabel name, job, xp, hp, atk, def, money, weaponE, armorE, icon;
      private JPanel panel1,panel2,panel3, panel4, panel5, panel6,panel7, bigPanel, characterIcon, equipment;
      public CharacterStats()
      {setTitle("Status");
         setSize(width, height);
         setLayout(new GridLayout(1,2));
         buildName();
         buildJob();
         buildXp();
         buildHp();
         buildAttack();
         buildDefense();
         buildMoney();
         buildEquipment();
         buildBigPanel();
         characterIcon = new JPanel();
         icon = new JLabel(character.getHero());
         characterIcon.add(icon);
         add(characterIcon);
         add(bigPanel);
         setVisible(true);
      }
      public void buildEquipment()
      {  equipment = new JPanel(new GridLayout(2,1));
         weaponE = new JLabel("E. " + character.getWeaponName() + " + " + character.getWeapon() + "Atk");
         armorE = new JLabel("E. " + character.getArmorName() + " + " + character.getArmor() + "Def");
         equipment.add(weaponE);
         equipment.add(armorE);
         equipment.setBorder(BorderFactory.createTitledBorder("Equipped"));
      }
      public void buildBigPanel()
      {  bigPanel = new JPanel(new GridLayout(8,1));
         bigPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
         bigPanel.add(panel1);
         bigPanel.add(panel2);
         bigPanel.add(panel3);
         bigPanel.add(panel4);
         bigPanel.add(panel5);
         bigPanel.add(panel6);
         bigPanel.add(panel7);
         bigPanel.add(equipment);
      }
      public void buildName()
      {panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         name = new JLabel(character.getName());
         panel1.add(name);
      }
      public void buildJob()
      {panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         job =  new JLabel(character.getJob() + "     Level " + character.getLevel());
         panel2.add(job);
      }
      public void buildXp()
      {panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         xp = new JLabel("XP:          " + character.getXp()+ "/" + (character.getLevel()*100));
         panel3.add(xp);
      }
      public void buildHp()
      {panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         hp = new JLabel("Hp:          " + character.getHp());
         panel4.add(hp);
      }
      public void buildAttack()
      {panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         atk = new JLabel("Attack:      "+character.getAtk());
         panel5.add(atk);
      }
      public void buildDefense()
      {panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         def = new JLabel("Defense:    "+character.getDef());
         panel6.add(def);
      }
      public void buildMoney()
      {panel7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         money = new JLabel(character.getMoney() + "G");
         panel7.add(money);
      }
   }
   public class Area1 extends JFrame
   {private int width = 300;
      private int height = 400;
      private JButton lvl1, lvl5, lvl9, lvl13, lvl17, back;
      private JPanel lvl1P, lvl5P, lvl9P, lvl13P, lvl17P;
      public Area1()
      {setTitle("SwordRPG");
         setSize(width, height);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(6,1));
         buildLvl1();
         buildLvl5();
         buildLvl9();
         buildLvl13();
         buildLvl17();
         back = new JButton("Back");
         back.addActionListener(new StageListener());
         add(lvl1P);
         add(lvl5P);
         add(lvl9P);
         add(lvl13P);
         add(lvl17P);
         add(back);
         setVisible(true);
      }
      public void buildLvl1()
      {lvl1P = new JPanel();
         lvl1 = new JButton("Level 1 ~ 4");
         lvl1.addActionListener(new StageListener());
         lvl1P.add(lvl1);
      }
      public void buildLvl5()
      {lvl5P = new JPanel();
         if (character.getLevel() > 4)
         {lvl5 = new JButton("Level 5 ~ 8");
            lvl5.addActionListener(new StageListener());}
         else
            lvl5 = new JButton("???");
         lvl5P.add(lvl5);
      }
      public void buildLvl9()
      {lvl9P = new JPanel();
         if (character.getLevel() > 8)
         {lvl9 = new JButton("Level 9 ~ 12");
            lvl9.addActionListener(new StageListener());}
         else
            lvl9 = new JButton("???");
         lvl9P.add(lvl9);
      }
      public void buildLvl13()
      {lvl13P = new JPanel();
         if (character.getLevel() > 12)
         {lvl13 = new JButton("Level 13 ~ 16");
            lvl13.addActionListener(new StageListener());}
         else
            lvl13 = new JButton("???");
         lvl13P.add(lvl13);
      }
      public void buildLvl17()
      {lvl17P = new JPanel();
         if (character.getLevel() > 16)
         {lvl17 = new JButton("Level 17 ~ 20");
            lvl17.addActionListener(new StageListener());}
         else
            lvl17 = new JButton("???");
         lvl17P.add(lvl17);
      }
      private class StageListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == lvl1)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)));
            }
            else if(e.getSource() == lvl5)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+4));
            }
            else if(e.getSource() == lvl9)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+8));}
            else if(e.getSource() == lvl13)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+12));}
            else if(e.getSource() == lvl17)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+16));}
            else if(e.getSource() == back)
            {dispose();
               new StageMenu(character, cha, enemy);
            }
         }
      }
   }
   public class Area2 extends JFrame
   {private int width = 300;
      private int height = 400;
      private JButton lvl21, lvl25, lvl29, lvl33, lvl37, back;
      private JPanel lvl21P, lvl25P, lvl29P, lvl33P, lvl37P;
      public Area2()
      {setTitle("SwordRPG");
         setSize(width, height);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(6,1));
         buildLvl21();
         buildLvl25();
         buildLvl29();
         buildLvl33();
         buildLvl37();
         back = new JButton("Back");
         back.addActionListener(new StageListener());
         add(lvl21P);
         add(lvl25P);
         add(lvl29P);
         add(lvl33P);
         add(lvl37P);
         add(back);
         setVisible(true);
      }
      public void buildLvl21()
      {lvl21P = new JPanel();
         lvl21 = new JButton("Level 21 ~ 24");
         lvl21.addActionListener(new StageListener());
         lvl21P.add(lvl21);
      }
      public void buildLvl25()
      {lvl25P = new JPanel();
         if (character.getLevel() > 24)
         {lvl25 = new JButton("Level 25 ~ 28");
            lvl25.addActionListener(new StageListener());}
         else
            lvl25 = new JButton("???");
         lvl25P.add(lvl25);
      }
      public void buildLvl29()
      {lvl29P = new JPanel();
         if (character.getLevel() > 28)
         {lvl29 = new JButton("Level 29 ~ 32");
            lvl29.addActionListener(new StageListener());}
         else
            lvl29 = new JButton("???");
         lvl29P.add(lvl29);
      }
      public void buildLvl33()
      {lvl33P = new JPanel();
         if (character.getLevel() > 32)
         {lvl33 = new JButton("Level 33 ~ 36");
            lvl33.addActionListener(new StageListener());}
         else
            lvl33 = new JButton("???");
         lvl33P.add(lvl33);
      }
      public void buildLvl37()
      {lvl37P = new JPanel();
         if (character.getLevel() > 36)
         {lvl37 = new JButton("Level 37 ~ 40");
            lvl37.addActionListener(new StageListener());}
         else
            lvl37 = new JButton("???");
         lvl37P.add(lvl37);
      }
      private class StageListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == lvl21)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+20));}
            else if(e.getSource() == lvl25)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+24));}
            else if(e.getSource() == lvl29)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+28));}
            else if(e.getSource() == lvl33)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+32));}
            else if(e.getSource() == lvl37)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+36));}
            else if(e.getSource() == back)
            {dispose();
               new StageMenu(character, cha, enemy);}
         }
      }
   }
   public class Area3 extends JFrame
   {private int width = 300;
      private int height = 400;
      private JButton lvl41, lvl45, lvl49, lvl53, lvl57, back;
      private JPanel lvl41P, lvl45P, lvl49P, lvl53P, lvl57P;
      public Area3()
      {setTitle("SwordRPG");
         setSize(width, height);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(6,1));
         buildLvl41();
         buildLvl45();
         buildLvl49();
         buildLvl53();
         buildLvl57();
         back = new JButton("Back");
         back.addActionListener(new StageListener());
         add(lvl41P);
         add(lvl45P);
         add(lvl49P);
         add(lvl53P);
         add(lvl57P);
         add(back);
         setVisible(true);
      }
      public void buildLvl41()
      {lvl41P = new JPanel();
         lvl41 = new JButton("Level 41 ~ 44");
         lvl41.addActionListener(new StageListener());
         lvl41P.add(lvl41);
      }
      public void buildLvl45()
      {lvl45P = new JPanel();
         if (character.getLevel() > 44)
         {lvl45 = new JButton("Level 45 ~ 48");
            lvl45.addActionListener(new StageListener());}
         else
            lvl45 = new JButton("???");
         lvl45P.add(lvl45);
      }
      public void buildLvl49()
      {lvl49P = new JPanel();
         if (character.getLevel() > 48)
         {lvl49 = new JButton("Level 49 ~ 52");
            lvl49.addActionListener(new StageListener());}
         else
            lvl49 = new JButton("???");
         lvl49P.add(lvl49);
      }
      public void buildLvl53()
      {lvl53P = new JPanel();
         if (character.getLevel() > 52)
         {lvl53 = new JButton("Level 53 ~ 56");
            lvl53.addActionListener(new StageListener());}
         else
            lvl53 = new JButton("???");
         lvl53P.add(lvl53);
      }
      public void buildLvl57()
      {lvl57P = new JPanel();
         if (character.getLevel() > 56)
         {lvl57 = new JButton("Level 57 ~ 60");
            lvl57.addActionListener(new StageListener());}
         else
            lvl57 = new JButton("???");
         lvl57P.add(lvl57);
      }
      private class StageListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == lvl41)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+40));}
            else if(e.getSource() == lvl45)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+44));}
            else if(e.getSource() == lvl49)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+48));}
            else if(e.getSource() == lvl53)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+52));}
            else if(e.getSource() == lvl57)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+56));}
            else if(e.getSource() == back)
            {dispose();
               new StageMenu(character, cha, enemy);}
         }
      }
   }
   public class Area4 extends JFrame
   {private int width = 300;
      private int height = 400;
      private JButton lvl61, lvl65, lvl69, lvl73, lvl77, back;
      private JPanel lvl61P, lvl65P, lvl69P, lvl73P, lvl77P;
      public Area4()
      {setTitle("SwordRPG");
         setSize(width, height);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(6,1));
         buildLvl61();
         buildLvl65();
         buildLvl69();
         buildLvl73();
         buildLvl77();
         back = new JButton("Back");
         back.addActionListener(new StageListener());
         add(lvl61P);
         add(lvl65P);
         add(lvl69P);
         add(lvl73P);
         add(lvl77P);
         add(back);
         setVisible(true);
      }
      public void buildLvl61()
      {lvl61P = new JPanel();
         lvl61 = new JButton("Level 61 ~ 64");
         lvl61.addActionListener(new StageListener());
         lvl61P.add(lvl61);
      }
      public void buildLvl65()
      {lvl65P = new JPanel();
         if (character.getLevel() > 64)
         {lvl65 = new JButton("Level 65 ~ 68");
            lvl65.addActionListener(new StageListener());}
         else
            lvl65 = new JButton("???");
         lvl65P.add(lvl65);
      }
      public void buildLvl69()
      {lvl69P = new JPanel();
         if (character.getLevel() > 68)
         {lvl69 = new JButton("Level 69 ~ 72");
            lvl69.addActionListener(new StageListener());}
         else
            lvl69 = new JButton("???");
         lvl69P.add(lvl69);
      }
      public void buildLvl73()
      {lvl73P = new JPanel();
         if (character.getLevel() > 72)
         {lvl73 = new JButton("Level 73 ~ 76");
            lvl73.addActionListener(new StageListener());}
         else
            lvl73 = new JButton("???");
         lvl73P.add(lvl73);
      }
      public void buildLvl77()
      {lvl77P = new JPanel();
         if (character.getLevel() > 76)
         {lvl77 = new JButton("Level 77 ~ 80");
            lvl77.addActionListener(new StageListener());}
         else
            lvl77 = new JButton("???");
         lvl77P.add(lvl77);
      }
      private class StageListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == lvl61)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+60));}
            else if(e.getSource() == lvl65)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+64));}
            else if(e.getSource() == lvl69)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+68));}
            else if(e.getSource() == lvl73)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+72));}
            else if(e.getSource() == lvl77)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+76));}
            else if(e.getSource() == back)
            {dispose();
               new StageMenu(character, cha, enemy);}
         }
      }
   }
   public class Area5 extends JFrame
   {private int width = 300;
      private int height = 400;
      private JButton lvl81, lvl85, lvl89, lvl93, lvl97, back;
      private JPanel lvl81P, lvl85P, lvl89P, lvl93P, lvl97P;
      public Area5()
      {setTitle("SwordRPG");
         setSize(width, height);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(6,1));
         buildLvl81();
         buildLvl85();
         buildLvl89();
         buildLvl93();
         buildLvl97();
         back = new JButton("Back");
         back.addActionListener(new StageListener());
         add(lvl81P);
         add(lvl85P);
         add(lvl89P);
         add(lvl93P);
         add(lvl97P);
         add(back);
         setVisible(true);
      }
      public void buildLvl81()
      {lvl81P = new JPanel();
         lvl81 = new JButton("Level 81 ~ 84");
         lvl81.addActionListener(new StageListener());
         lvl81P.add(lvl81);
      }
      public void buildLvl85()
      {lvl85P = new JPanel();
         if (character.getLevel() > 84)
         {lvl85 = new JButton("Level 85 ~ 88");
            lvl85.addActionListener(new StageListener());}
         else
            lvl85 = new JButton("???");
         lvl85P.add(lvl85);
      }
      public void buildLvl89()
      {lvl89P = new JPanel();
         if (character.getLevel() > 88)
         {lvl89 = new JButton("Level 89 ~ 92");
            lvl89.addActionListener(new StageListener());}
         else
            lvl89 = new JButton("???");
         lvl89P.add(lvl89);
      }
      public void buildLvl93()
      {lvl93P = new JPanel();
         if (character.getLevel() > 92)
         {lvl93 = new JButton("Level 93 ~ 96");
            lvl93.addActionListener(new StageListener());}
         else
            lvl93 = new JButton("???");
         lvl93P.add(lvl93);
      }
      public void buildLvl97()
      {lvl97P = new JPanel();
         if (character.getLevel() > 96)
         {lvl97 = new JButton("Level 97 ~ 100");
            lvl97.addActionListener(new StageListener());}
         else
            lvl97 = new JButton("???");
         lvl97P.add(lvl97);
      }
      private class StageListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == lvl81)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+80));}
            else if(e.getSource() == lvl85)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+84));}
            else if(e.getSource() == lvl89)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+88));}
            else if(e.getSource() == lvl93)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+92));}
            else if(e.getSource() == lvl97)
            {dispose();
               new ActionMenu(character, cha, enemy, ((int)(Math.random()*4)+96));}
            else if(e.getSource() == back)
            {dispose();
               new StageMenu(character, cha, enemy);}
         }
      }
   }
   public class Shop extends JFrame
   {private int width = 600;
      private int height = 500;
      private JButton buy, back;
      private JList <String> weapon = new JList <String>(shopList.getWeaponName());
      private JList <String> armor = new JList <String>(shopList.getArmorName());
      private JList <String> item = new JList <String>(shopList.getItemName());
      private JList <String> shop;
      private JScrollPane scroll, scroll2;
      private JPanel pic, backP, shopP, buyP, pic2, shop1, shop2, down, mid, blackSmith, image, bigPic, upper;
      private String selected, selected2 = "none";
      private JLabel money, name, stats, price, gaius;
      public Shop()
      {
         setSize(width, height);
         setTitle("SwordRPG");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new BorderLayout());
         money = new JLabel("Money:  " + character.getMoney() + "G");
         buildShop();
         buildPic();
         buildBack();
         buildBuy();
         buildDown();
         buildMid();
         add(mid, BorderLayout.CENTER);
         add(down, BorderLayout.SOUTH);
         setVisible(true);
      }
      public void buildDown()
      {down = new JPanel(new GridLayout(1,2));
         down.add(backP);
         down.add(buyP);
      }
      public void buildMid()
      {mid = new JPanel(new GridLayout(1,2));
         mid.add(bigPic);
         mid.add(shopP);
      }
      public void buildPic()
      {  bigPic = new JPanel(new GridLayout(2,1));
         ImageIcon icon = new ImageIcon("Image/BlackSmith.jpg", "BlackSmith.jpg");
         gaius = new JLabel(icon);
         pic = new JPanel(new GridLayout(1,2));
         blackSmith = new JPanel();
         blackSmith.add(gaius);
         image = new JPanel();
         pic2 = new JPanel(new GridLayout(3,1));
         name = new JLabel("Name: ");
         stats = new JLabel("Stats: ");
         price = new JLabel("Price: ");
         pic2.add(name);
         pic2.add(stats);
         pic2.add(price);
         pic.add(image);
         pic.add(pic2);
         bigPic.add(blackSmith);
         bigPic.add(pic);
      }
      public void buildShop()
      {shopP = new JPanel(new GridLayout(1,2));
         shop1 = new JPanel();
         shop2 = new JPanel();
         weapon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         weapon.setBorder(BorderFactory.createLineBorder(Color.black, 1));
         weapon.setVisibleRowCount(10);
         weapon.addListSelectionListener(new WeaponListener());
         scroll2 = new JScrollPane(weapon);
         shop2.setBorder(BorderFactory.createTitledBorder("Weapons"));
         shop2.add(scroll2);
         String[] sh = {"Weapons", "Armors", "Items"};
         shop = new JList <>(sh);
         shop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         shop.setBorder(BorderFactory.createLineBorder(Color.black,1));
         shop.setVisibleRowCount(5);
         shop.addListSelectionListener(new ShopListener());
         scroll = new JScrollPane(shop);
         shop1.add(scroll);
         shop1.setBorder(BorderFactory.createTitledBorder("Category"));
         shopP.add(shop1);
         shopP.add(shop2);
      }
      public void buildWeapon()
      {
         weapon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         weapon.setBorder(BorderFactory.createLineBorder(Color.black, 1));
         weapon.setVisibleRowCount(10);
         weapon.addListSelectionListener(new WeaponListener());
         scroll2 = new JScrollPane(weapon);
         shop2.setBorder(BorderFactory.createTitledBorder("Weapons"));
         shop2.add(scroll2);
      }
      public void buildArmor()
      {
         armor.setVisibleRowCount(10);
         armor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         armor.setBorder(BorderFactory.createLineBorder(Color.black, 1));
         armor.addListSelectionListener(new ArmorListener());
         scroll2 = new JScrollPane(armor);
         shop2.setBorder(BorderFactory.createTitledBorder("Armors"));
         shop2.add(scroll2);
      }
      public void buildItem()
      {scroll2 = new JScrollPane(item);
         item.setVisibleRowCount(10);
         item.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         item.setBorder(BorderFactory.createLineBorder(Color.black, 1));
         item.addListSelectionListener(new ItemListener());
         shop2.setBorder(BorderFactory.createTitledBorder("Items"));
         shop2.add(scroll2);
      }
      
      public void buildBack()
      {backP = new JPanel(new BorderLayout());
         back = new JButton("Back");
         back.addActionListener(new ButtonListener());
         backP.add(back, BorderLayout.SOUTH);
      }
      
      public void buildBuy()
      {buyP = new JPanel(new BorderLayout());
         buy = new JButton("Buy");
         buy.addActionListener(new ButtonListener());
         buyP.add(buy, BorderLayout.SOUTH);
         buyP.add(money, BorderLayout.NORTH);
      }
      
      private class ShopListener implements ListSelectionListener
      {
         public void valueChanged(ListSelectionEvent e)
         {selected = (String) shop.getSelectedValue();
            if(selected.compareTo("Weapons") == 0)
            {  shop2.remove(scroll2);
               buildWeapon();
               shop2.repaint();
               shop2.revalidate();}
            else if(selected.compareTo("Armors") == 0)
            {  shop2.remove(scroll2);
               buildArmor();   
               shop2.revalidate();
               shop2.repaint();}
            else if(selected.compareTo("Items") == 0)
            {  shop2.remove(scroll2);
               buildItem();
               shop2.revalidate();
               shop2.repaint();}
         }
      }
      
      private class WeaponListener implements ListSelectionListener
      {
         public void valueChanged(ListSelectionEvent e)
         {selected = (String) weapon.getSelectedValue();
            for(int i = 0; i < shopList.getWeaponName().length; i++)
            {
               if (selected.compareTo(shopList.getWeaponName(i))== 0)
               {pic2.remove(name);
                  pic2.remove(stats);
                  pic2.remove(price);
                  name = new JLabel("Name:  " +shopList.getWeaponName(i));
                  stats = new JLabel("DEF:  +" +Integer.toString(shopList.getWeaponAtk(i)) + "ATK");
                  price = new JLabel("Cost:  " +Integer.toString(shopList.getWeaponPrice(i)) + "G");
                  pic2.add(name);
                  pic2.add(stats);
                  pic2.add(price);
                  pic2.revalidate();
                  pic2.repaint();
               }
            }
         }
      }
      private class ArmorListener implements ListSelectionListener
      {
         public void valueChanged(ListSelectionEvent e)
         {selected = (String) armor.getSelectedValue();
            for(int i = 0; i < shopList.getArmorName().length; i++)
            {
               if (selected.compareTo(shopList.getArmorName(i))== 0)
               {pic2.remove(name);
                  pic2.remove(stats);
                  pic2.remove(price);
                  name = new JLabel("Name:  " + shopList.getArmorName(i));
                  stats = new JLabel("DEF:  +" +Integer.toString(shopList.getArmorDef(i)) + "DEF");
                  price = new JLabel("Cost:  " +Integer.toString(shopList.getArmorPrice(i)) + "G");
                  pic2.add(name);
                  pic2.add(stats);
                  pic2.add(price);
                  pic2.revalidate();
                  pic2.repaint();
               }
            }
         }
      }
      private class ItemListener implements ListSelectionListener
      {
         public void valueChanged(ListSelectionEvent e)
         {selected = (String) item.getSelectedValue();
            for(int i = 0; i < shopList.getItemName().length; i++)
            {
               if (selected.compareTo(shopList.getItemName(i))== 0)
               {pic2.remove(name);
                  pic2.remove(stats);
                  pic2.remove(price);
                  name = new JLabel("Name:  " + shopList.getItemName(i));
                  stats = new JLabel("Effects:    Heals " + Integer.toString(shopList.getItemEff(i)) + "HP");
                  price = new JLabel("Cost:  " + Integer.toString(shopList.getItemPrice(i))+ "G");
                  pic2.add(name);
                  pic2.add(stats);
                  pic2.add(price);
                  pic2.revalidate();
                  pic2.repaint();
               }
            }
         }
      }
      private class ButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == back)
            {dispose();
               new StageMenu(character, cha, enemy);}
            else if (e.getSource() == buy)
            {
               for(int i = 0; i < shopList.getWeaponName().length; i++)
               {
                  if (selected.compareTo(shopList.getWeaponName(i))== 0)
                  {
                     if (character.getMoney() > shopList.getWeaponPrice(i))
                     {  character.spend(shopList.getWeaponPrice(i));
                        character.setWeaponName(shopList.getWeaponName(i));
                        character.setWeapon(shopList.getWeaponAtk(i));
                        JOptionPane.showMessageDialog(null,"You bought and equipped a " + shopList.getWeaponName(i));
                     }
                     else
                     {JOptionPane.showMessageDialog(null, "Not enough money!");
                     }
                  }
               }
               for(int i = 0; i < shopList.getArmorName().length; i++)
               {
                  if (selected.compareTo(shopList.getArmorName(i))== 0)
                  {
                     if (character.getMoney() > shopList.getArmorPrice(i))
                     {character.spend(shopList.getArmorPrice(i));
                        character.setArmorName(shopList.getArmorName(i));
                        character.setArmor(shopList.getArmorDef(i));
                        JOptionPane.showMessageDialog(null,"You bought and equipped a " + shopList.getArmorName(i));
                     }
                     else
                     {JOptionPane.showMessageDialog(null, "Not enough money!");
                     }
                  }
               }
               for(int i = 0; i < shopList.getItemName().length; i++)
               {
                  if (selected.compareTo(shopList.getItemName(i))== 0)
                  {
                     if (character.getMoney() > shopList.getItemPrice(i))
                     { character.spend(shopList.getItemPrice(i));
                        character.buyItem(i);
                        JOptionPane.showMessageDialog(null,"You bought a " + shopList.getItemName(i));
                     }
                     else
                     {JOptionPane.showMessageDialog(null, "Not enough money!");
                     }
                  }
               }
               buyP.remove(money);
               money = new JLabel("Money:  " + character.getMoney() + "G");
               buyP.add(money, BorderLayout.NORTH);
               buyP.repaint();
               buyP.revalidate();
            }
         }
      }
   }
}