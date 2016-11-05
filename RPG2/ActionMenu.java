import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class ActionMenu extends JFrame
{  
   private int width = 700;
   private int height = 500;
   private JButton attack, defend, item, run, mainMenu, save, menub;
   private JPanel panel, menu, action, hero, mons;
   private int cha, num;
   private Character character;
   private Enemy enemy;
   private int characterHp, enemyHp;
   private JLabel showHp, showHp2, showHp3, heroImage, enemyImage, sword;
   public ActionMenu(Character ch, int c, Enemy en, int e)
   {  
      try{
         enemy = new Enemy();
      }
      catch(IOException a){}
      cha = c;
      character = ch;
      enemy = en;
      num = e;
      characterHp = character.getHp();
      enemyHp = enemy.getHp(num);
      setTitle("Action Menu");
      setSize(width,height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      heroImage = new JLabel(character.getHero());
      enemyImage = new JLabel(enemy.getMonster(num));
      buildActionPanel();
      buildMenuPanel();
      buildCenterPanel();
      setLayout(new BorderLayout());
      add(panel, BorderLayout.SOUTH);
      add(action, BorderLayout.CENTER);
      add(menu, BorderLayout.NORTH); 
      add(enemyImage, BorderLayout.EAST);
      add(heroImage, BorderLayout.WEST);
      setVisible(true);
   }
   public void buildActionPanel()
   {
      attack = new JButton("Attack");
      defend = new JButton("Defend");
      item = new JButton("Item");
      run = new JButton("Run");
      attack.addActionListener(new ActListener());
      defend.addActionListener(new ActListener());
      item.addActionListener(new ActListener());
      run.addActionListener(new ActListener());
      panel = new JPanel(new GridLayout(2,2));
      panel.setBorder(BorderFactory.createTitledBorder("Actions"));
      panel.add(attack);
      panel.add(defend);
      panel.add(item);
      panel.add(run);
   }
   public void buildMenuPanel()
   {
      menu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      menub = new JButton("Options");
      menub.addActionListener(new MenuListener());
      mainMenu = new JButton("Back to Main Menu");
      mainMenu.addActionListener(new MenuListener());
      menu.add(mainMenu);
      menu.add(menub);
   }
   public void buildCenterPanel()
   {
      action = new JPanel(new BorderLayout());
      showHp = new JLabel("Level " + character.getLevel() + "     " + characterHp + "/" + character.getHp());
      showHp2 = new JLabel("Level " + enemy.getLevel(num) + "     " + enemy.getName(num));
      action.add(showHp, BorderLayout.NORTH);
      action.add(showHp2, BorderLayout.SOUTH);
      DrawRect1 a = new DrawRect1();
      ImageIcon swd = new ImageIcon("Image/Swords.jpg","Swords.jpg");
      sword = new JLabel(swd);
      action.add(a);
      action.add(sword, BorderLayout.CENTER);
   }
   
   private class MenuListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource()== menub)
         {new Menu();}
         else if(e.getSource() == mainMenu)
         {dispose();
            new MainMenu();}
      }
   }
   
   
   private class ActListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == attack)
         {
            if(character.getAtk() > enemy.getDef(num))
               enemyHp -= (character.getAtk() + enemyHp/10 - enemy.getDef(num));
            else
               enemyHp -= (enemyHp/15);
            if(enemyHp > 0)
            {
               if(enemy.getAtk(num) > character.getDef())
                  characterHp -= (enemy.getAtk(num) + characterHp/10 - character.getDef());
               else
                  characterHp -= (enemy.getAtk(num)/15);}
            else
            {dispose();
               enemy.met(num);
               new EndBattle(enemy.getXp(num), enemy.getMoney(num));      
            }
            if(characterHp > 0)
            {
               action.remove(showHp);
               action.remove(showHp2);
               showHp = new JLabel("Level " + character.getLevel() + "     " + characterHp + "/" + character.getHp());
               action.add(showHp, BorderLayout.NORTH);
               action.add(showHp2, BorderLayout.SOUTH);
               action.add(new DrawRect1());
               action.repaint();
               action.revalidate();
            }
            else
            {
               dispose();
               new EndBattle();
            }
         }
         else if(e.getSource() == defend)
         {
            if(enemy.getAtk(num) > character.getDef())
            {
               characterHp -= (enemy.getAtk(num) + characterHp/15 - character.getDef())/2;
            }
            else
            {
               characterHp -= (enemy.getAtk(num)/20)/2;
            }
            if(characterHp > 0)
            {
               action.remove(showHp);
               action.remove(showHp2);
               showHp = new JLabel("Level " + character.getLevel() + "     " + characterHp + "/" + character.getHp());
               action.add(showHp, BorderLayout.NORTH);
               action.add(showHp2, BorderLayout.SOUTH);
               action.add(new DrawRect1());
               action.repaint();
               action.revalidate();
            }
            else
            {
               dispose();
               new EndBattle();
            }
         
         }
         else if(e.getSource() == item)
         {
            new Item();
         }
         else if(e.getSource() == run)
         {
            int i = (int)(Math.random()*10);
            if(i < 4)
            {
               JOptionPane.showMessageDialog(null,"You escaped the battle");
               dispose();
               new StageMenu(character,cha, enemy);
            }
            else
            {
               JOptionPane.showMessageDialog(null,"You failed to escape");
               if(enemy.getAtk(num) > character.getDef())
               {
                  characterHp -= (enemy.getAtk(num) + characterHp/15 - character.getDef());
               }
               else
               {
                  characterHp -= (enemy.getAtk(num)/20);
               }
               if(characterHp > 0)
               {
                  action.remove(showHp);
                  action.remove(showHp2);
                  showHp = new JLabel("Level " + character.getLevel() + "     " + characterHp + "/" + character.getHp());
                  action.add(showHp, BorderLayout.NORTH);
                  action.add(showHp2, BorderLayout.SOUTH);
                  action.add(new DrawRect1());
                  action.repaint();
                  action.revalidate();
               }
               else
               {
                  dispose();
                  new EndBattle();
               }
            }
         }
      }
   }
   public class Menu extends JFrame
   {
      private int width = 100;
      private int height = 100;
      private JButton save, back;
      public Menu()
      {
         setTitle("Menu");
         setSize(width, height);
         setLayout(new GridLayout(2,1));
         save = new JButton("Save Game");
         back = new JButton("Back");
         save.addActionListener(new SubMenuListener());
         back.addActionListener(new SubMenuListener());
         add(save);
         add(back);
         setVisible(true);
      }
      private class SubMenuListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if (e.getSource() == save)
            {
               try{
                  character.writeSave(cha);
               }
               catch(IOException ex){}
               dispose();
               JOptionPane.showMessageDialog(null, "The data has been saved");
            }
            else if (e.getSource() == back)
            {
               dispose();
            }
         }
      
      }
   }
   public class EndBattle extends JFrame
   {
      private int width = 300;
      private int height = 400;
      private JButton ok;
      private JLabel battle, x, money, level, levelup;
      public EndBattle(int xp, int m)
      {
         setTitle("SwordRPG");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width, height);
         int lvl = character.getLevel();
         character.levelUp(xp,m);
         if(lvl < character.getLevel())
            setLayout(new GridLayout(6,1));
         else
            setLayout(new GridLayout(4,1));
         battle = new JLabel("VICTORY");
         add(battle);
         if(lvl < character.getLevel())
         {  level = new JLabel(" LEVEL UP ");
            levelup = new JLabel(" Level " + lvl + " ----> " + character.getLevel());
            add(level);
            add(levelup);
         } 
         x = new JLabel(" + " + xp + "XP");
         money = new JLabel(" + " + m + "G");     
         ok = new JButton("OK");
         ok.addActionListener(new OKListener());
         add(x);
         add(money);
         add(ok);
         setVisible(true);
      }
      public EndBattle()
      {
         setTitle("SwordRPG");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width, height);
         setLayout(new GridLayout(4,1));
         battle = new JLabel("Defeat");
         add(battle);
         x = new JLabel(" + " + 0 + "XP");
         money = new JLabel(" + " + 0 + "G");  
         ok = new JButton("OK");
         ok.addActionListener(new OKListener());
         add(x);
         add(money);
         add(ok);
         setVisible(true);
      }
      private class OKListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            dispose();
            new StageMenu(character, cha, enemy);}
      }
   }
   public class DrawRect1 extends JComponent
   {
      public void paint(Graphics g)
      {  
         g.drawRect(0,5,300,15);
         g.setColor(Color.green);
         g.fillRect(0,5,((characterHp*100/character.getHp())*3),15);
         g.setColor(Color.black);
         g.drawRect(45,300,300,15);
         g.setColor(Color.red);
         g.fillRect(45 + 300-((enemyHp*100/enemy.getHp(num))*3),300,((enemyHp*100/enemy.getHp(num))*3),15);
      }
   } 
   public class Item extends JFrame
   {
      private int width = 300;
      private int height = 600;
      private JButton item0, item1, item2, item3, back;
      private JLabel item0Ef, item1Ef, item2Ef, item3Ef;
      private JPanel it0, it1, it2, it3;
      public Item()
      {
         setSize(width, height);
         setTitle("Inventory");
         setLayout(new GridLayout(5,1));
         buildItem0();
         buildItem1();
         buildItem2();
         buildItem3();
         back = new JButton("Back");
         back.addActionListener(new ItemListener());
         add(it0);
         add(it1);
         add(it2);
         add(it3);
         add(back);
         setVisible(true);
      }
      public void buildItem0()
      {
         it0 = new JPanel(new GridLayout(2,1));
         item0 = new JButton("Potion    X " + character.getItem(0));
         item0.addActionListener(new ItemListener());
         item0Ef = new JLabel("Heals 100HP");
         it0.setBorder(BorderFactory.createTitledBorder("Slot 1"));
         it0.add(item0);
         it0.add(item0Ef);
      }
      public void buildItem1()
      {
         it1 = new JPanel(new GridLayout(2,1));
         item1 = new JButton("Potion*2   X " + character.getItem(1));
         item1.addActionListener(new ItemListener());
         item1Ef = new JLabel("Heals 500HP");
         it1.setBorder(BorderFactory.createTitledBorder("Slot 2"));
         it1.add(item1);
         it1.add(item1Ef);
      }
      public void buildItem2()
      {
         it2 = new JPanel(new GridLayout(2,1));
         item2 = new JButton("Potion*3   X " + character.getItem(2));
         item2.addActionListener(new ItemListener());
         item2Ef = new JLabel("Heals 2500HP");
         it2.setBorder(BorderFactory.createTitledBorder("Slot 3"));
         it2.add(item2);
         it2.add(item2Ef);
      }
      public void buildItem3()
      {
         it3 = new JPanel(new GridLayout(2,1));
         item3 = new JButton("Potion*4   X " + character.getItem(3));
         item3.addActionListener(new ItemListener());
         item3Ef = new JLabel("Heals 10000HP");
         it3.setBorder(BorderFactory.createTitledBorder("Slot 4"));
         it3.add(item3);
         it3.add(item3Ef);
      }
      private class ItemListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == item0)
            {  
               if(character.getItem(0) > 0)
               {
                  dispose();
                  int hp = characterHp;
                  character.useItem(0);
                  characterHp += 100;
                  if(characterHp > character.getHp())
                     characterHp = character.getHp();
                  JOptionPane.showMessageDialog(null, "Healed " + (characterHp - hp)+ "HP");
                  if(enemy.getAtk(num) > character.getDef())
                  {
                     characterHp -= (enemy.getAtk(num) + characterHp/15 - character.getDef());
                  }
                  else
                  {
                     characterHp -= (enemy.getAtk(num)/20);
                  }
                  if(characterHp > 0)
                  {
                     action.remove(showHp);
                     action.remove(showHp2);
                     showHp = new JLabel("Level " + character.getLevel() + "     " + characterHp + "/" + character.getHp());
                     action.add(showHp, BorderLayout.NORTH);
                     action.add(showHp2, BorderLayout.SOUTH);
                     action.add(new DrawRect1());
                     action.repaint();
                     action.revalidate();
                  }
                  else
                  {
                     dispose();
                     new EndBattle();
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Not enough potion");
               }
            }
            else if(e.getSource() == item1)
            {
               if(character.getItem(1) > 0)
               {
                  dispose();
                  int hp = characterHp;
                  character.useItem(1);
                  characterHp += 500;
                  if(characterHp > character.getHp())
                     characterHp = character.getHp();
                  JOptionPane.showMessageDialog(null, "Healed " + (characterHp - hp)+ "HP");
                  if(enemy.getAtk(num) > character.getDef())
                  {
                     characterHp -= (enemy.getAtk(num) + characterHp/15 - character.getDef());
                  }
                  else
                  {
                     characterHp -= (enemy.getAtk(num)/20);
                  }
                  if(characterHp > 0)
                  {
                     action.remove(showHp);
                     action.remove(showHp2);
                     showHp = new JLabel("Level " + character.getLevel() + "     " + characterHp + "/" + character.getHp());
                     action.add(showHp, BorderLayout.NORTH);
                     action.add(showHp2, BorderLayout.SOUTH);
                     action.add(new DrawRect1());
                     action.repaint();
                     action.revalidate();
                  }
                  else
                  {
                     dispose();
                     new EndBattle();
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Not enough potion*2");
               }
            }
            else if(e.getSource() == item2)
            {
               if(character.getItem(2) > 0)
               {
                  dispose();
                  int hp = characterHp;
                  character.useItem(2);
                  characterHp += 2500;
                  if(characterHp > character.getHp())
                     characterHp = character.getHp();
                  JOptionPane.showMessageDialog(null, "Healed " + (characterHp - hp)+ "HP");
                  if(enemy.getAtk(num) > character.getDef())
                  {
                     characterHp -= (enemy.getAtk(num) + characterHp/15 - character.getDef());
                  }
                  else
                  {
                     characterHp -= (enemy.getAtk(num)/20);
                  }
                  if(characterHp > 0)
                  {
                     action.remove(showHp);
                     action.remove(showHp2);
                     showHp = new JLabel("Level " + character.getLevel() + "     " + characterHp + "/" + character.getHp());
                     action.add(showHp, BorderLayout.NORTH);
                     action.add(showHp2, BorderLayout.SOUTH);
                     action.add(new DrawRect1());
                     action.repaint();
                     action.revalidate();
                  }
                  else
                  {
                     dispose();
                     new EndBattle();
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Not enough potion*3");
               }
            }
            else if(e.getSource() == item3)
            {
               if(character.getItem(3) > 0)
               {
                  dispose();
                  int hp = characterHp;
                  character.useItem(3);
                  characterHp += 10000;
                  if(characterHp > character.getHp())
                     characterHp = character.getHp();
                  JOptionPane.showMessageDialog(null, "Healed " + (characterHp - hp) + "HP");
                  if(enemy.getAtk(num) > character.getDef())
                  {
                     characterHp -= (enemy.getAtk(num) + characterHp/15 - character.getDef());
                  }
                  else
                  {
                     characterHp -= (enemy.getAtk(num)/20);
                  }
                  if(characterHp > 0)
                  {
                     action.remove(showHp);
                     action.remove(showHp2);
                     showHp = new JLabel("Level " + character.getLevel() + "     " + characterHp + "/" + character.getHp());
                     action.add(showHp, BorderLayout.NORTH);
                     action.add(showHp2, BorderLayout.SOUTH);
                     action.add(new DrawRect1());
                     action.repaint();
                     action.revalidate();
                  }
                  else
                  {
                     dispose();
                     new EndBattle();
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Not enough potion*4");
               }
            }
            else if(e.getSource() == back)
            {
               dispose();
            }
         }
      }
   }
}
