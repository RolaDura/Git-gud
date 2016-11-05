import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;
public class MainMenu extends JFrame
{
   private int width = 300;
   private int height = 250;
   private JPanel panel, panel1, panel2, panel3, panel4;
   private JButton newGame, cont, list, exit;
   private Character[] character = new Character [3];
   private int cha;
   private String name;
   private Enemy enemy;
   public MainMenu ()
   {  
      try{
         enemy = new Enemy();
      }
      catch(IOException a){}
      setTitle ("Sword RPG");
      setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      setSize (width, height);
      setLayout (new FlowLayout (FlowLayout.CENTER));
      buildPanel ();
      buildPanel1 ();
      buildPanel2 ();
      buildPanel3 ();
      buildPanel4 ();
      add (panel4);
      setVisible (true);
   }

   public void buildPanel ()
   {
      panel = new JPanel ();
      newGame = new JButton ("New Game");
      newGame.addActionListener (new MenuListener ());
      panel.add (newGame);
   }


   public void buildPanel1 ()
   {
      panel1 = new JPanel ();
      cont = new JButton ("Continue");
      cont.addActionListener (new MenuListener ());
      cont.setSize (200, 200);
      panel1.add (cont);
   }


   public void buildPanel2 ()
   {
      panel2 = new JPanel ();
      list = new JButton ("Monster List");
      list.addActionListener (new MenuListener ());
      panel2.add (list);
   }


   public void buildPanel3 ()
   {
      panel3 = new JPanel ();
      exit = new JButton ("Exit");
      exit.addActionListener (new MenuListener ());
      panel3.add (exit);
   }


   public void buildPanel4 ()
   {
      panel4 = new JPanel (new GridLayout (4, 1));
      panel4.setBorder (BorderFactory.createTitledBorder ("SWORD RPG"));
      panel4.add (panel);
      panel4.add (panel1);
      panel4.add (panel2);
      panel4.add (panel3);
   }


   private class MenuListener implements ActionListener
   {
      public void actionPerformed (ActionEvent e)
      { 
         if (e.getSource () == newGame)
         {  cha = 0;
            int  i = 1;
            while (i == 1 && cha < 3)
            {
               File file = new File("Character" + cha + ".txt");
               if (file.exists())
               {cha++;}
               else
               {i = 0;}
            }
            if(cha < 3)
            {
               new Intro ();
               dispose();
            }
            else
               JOptionPane.showMessageDialog(null, "Save Slots are full! Please delete a save file");
         }
         else if (e.getSource() == cont)
         {
            dispose();
            new Save();
         }
         else if(e.getSource() == list)
         {
            dispose();
            new MonsterList();
         }
         else if (e.getSource() == exit)
         {
            dispose();
         }
      }
   }


   public class Intro extends JFrame
   {
      private JPanel panel;
      private int width = 400;
      private int height = 100;
      private JLabel text;
      private JTextField textField;
      private JButton enter;
      private String name;
   
      public Intro ()
      {
         setTitle ("Name");
         setSize (width, height);
         setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
         buildPanel ();
         add (panel);
         setVisible (true);
      }
   
      public void buildPanel ()
      {
         text = new JLabel ("Enter your name: ");
         textField = new JTextField (12);
         enter = new JButton ("Enter");
         enter.addActionListener (new EnterListener ());
         panel = new JPanel ();
         panel.add (text);
         panel.add (textField);
         panel.add (enter);
      }
      private class EnterListener implements ActionListener
      {
         public void actionPerformed (ActionEvent e)
         {
            name = textField.getText ();
            setVisible (false);
            new Selection ();
         }
      }
   
      public class Selection extends JFrame
      {
         private JPanel gender, job, enter, blank;
         private int width = 400;
         private int height = 300;
         private JButton confirm;
         private JRadioButton male, female, assassin, warrior, paladin;
         private String genderChoice, jobChoice;
         public Selection ()
         {
            setTitle ("Create your character");
            setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            setSize (width, height);
            createPanel1 ();
            createPanel2 ();
            createPanel3 ();
            setLayout (new GridLayout (2, 2));
            blank = new JPanel ();
            add (gender);
            add (job);
            add (blank);
            add (enter);
            setVisible (true);
         }
         public void createPanel1 ()
         {
            gender = new JPanel (new GridLayout (2, 1));
            male = new JRadioButton ("Male");
            female = new JRadioButton ("Female");
            male.addActionListener (new GenderListener ());
            female.addActionListener (new GenderListener ());
            male.doClick ();
            ButtonGroup group = new ButtonGroup ();
            group.add (male);
            group.add (female);
            gender.setBorder (BorderFactory.createTitledBorder ("Gender"));
            gender.add (male);
            gender.add (female);
         
         }
         public void createPanel2 ()
         {
            job = new JPanel (new GridLayout (3, 1));
            assassin = new JRadioButton ("Assassin");
            warrior = new JRadioButton ("Warrior");
            paladin = new JRadioButton ("Paladin");
            assassin.addActionListener (new JobListener ());
            warrior.addActionListener (new JobListener ());
            paladin.addActionListener (new JobListener ());
            ButtonGroup group1 = new ButtonGroup ();
            assassin.doClick ();
            group1.add (assassin);
            group1.add (warrior);
            group1.add (paladin);
            job.setBorder (BorderFactory.createTitledBorder ("Class"));
            job.add (assassin);
            job.add (warrior);
            job.add (paladin);
         }
         public void createPanel3 ()
         {
            confirm = new JButton ("Confirm");
            confirm.addActionListener (new CharacterListener ());
            enter = new JPanel ();
            enter.add (confirm);
         }
         private class GenderListener implements ActionListener
         {
            public void actionPerformed (ActionEvent e)
            {
               genderChoice = e.getActionCommand ();
            }
         }
         private class JobListener implements ActionListener
         {
            public void actionPerformed (ActionEvent e)
            {
               jobChoice = e.getActionCommand ();
            }
         }
         private class CharacterListener implements ActionListener
         {
            public void actionPerformed (ActionEvent e)
            {
               setVisible (false);
               character[cha] = new Character(name, jobChoice, genderChoice);
               JOptionPane.showMessageDialog(null, character[cha].getName()+", welcome to the game");
               JOptionPane.showMessageDialog(null, "You will be a " + character[cha].getGender() + " " + character[cha].getJob());
               new StageMenu(character[cha], cha, enemy);
            }
         }
      }
   }
   public class Save extends JFrame
   {
      private JPanel save1, save2, save3, exit, delete ;
      private int width = 300;
      private int height = 400;
      private JButton load1, load2, load3, exitb, deleteb;
      public Save()
      {
         setTitle("Save Files");
         setSize(width, height);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(5,1));
         buildSave1();
         buildSave2();
         buildSave3();
         buildExit();
         buildDelete();
         add(save1);
         add(save2);
         add(save3);
         add(delete);
         add(exit);
         setVisible(true);
      }
      public void buildSave1()
      {
         save1 = new JPanel();
         save1.setBorder(BorderFactory.createTitledBorder("Save 1"));
         File file = new File("Character0.txt");
         if(file.exists())
         {
            character[0] = new Character();
            try{
               character[0].loadSave(0);
            }
            catch(IOException ex){}
            load1 = new JButton(character[0].getName()+ " LVL" + character[0].getLevel() + " " + character[0].getJob());
            load1.addActionListener(new LoadListener());
         }
         else
         {
            load1 = new JButton("None");
         }
         save1.add(load1);
      }
      public void buildSave2()
      {
         save2 = new JPanel();
         save2.setBorder(BorderFactory.createTitledBorder("Save 2"));
         File file = new File("Character1.txt");
         if(file.exists())
         {
            character[1] = new Character();
            try{
               character[1].loadSave(1);
            }
            catch(IOException ex){}
            load2 = new JButton(character[1].getName()+ " LVL" + character[1].getLevel() + " " + character[1].getJob());
            load2.addActionListener(new LoadListener());
         }
         else
         {
            load2 = new JButton("None");
         }
         save2.add(load2);
      }
      public void buildSave3()
      {
         save3 = new JPanel();
         save3.setBorder(BorderFactory.createTitledBorder("Save 3"));
         File file = new File("Character2.txt");
         if(file.exists())
         {
            character[2] = new Character();
            try{
               character[2].loadSave(2);
            }
            catch(IOException ex){}
            load3 = new JButton(character[2].getName()+ " LVL" + character[2].getLevel() + " " + character[2].getJob());
            load3.addActionListener(new LoadListener());
         }
         else
         {
            load3 = new JButton("None");
         }
         save3.add(load3);
      }
      public void buildExit()
      {
         exit = new JPanel();
         exitb = new JButton("Back to Main Menu");
         exitb.addActionListener(new LoadListener());
         exit.add(exitb);
      }
      public void buildDelete()
      {
         delete = new JPanel();
         deleteb = new JButton("Delete Save File");
         deleteb.addActionListener(new LoadListener());
         delete.add(deleteb);
      }
      private class LoadListener implements ActionListener 
      {
         public void actionPerformed(ActionEvent e) 
         {  
            if(e.getSource() == load1)
            {  cha = 0;
               try{
                  character[cha].loadSave(cha);
               }
               catch(IOException ex){}
               dispose();
               new StageMenu(character[cha], cha, enemy); 
            }
            else if(e.getSource() == load2)
            {
               cha = 1;
               try{
                  character[cha].loadSave(cha);
               }
               catch(IOException ex){}
               dispose();
               new StageMenu(character[cha], cha, enemy);
            }
            else if(e.getSource() == load3)
            {
               cha = 2;
               try{
                  character[cha].loadSave(cha);
               }
               catch(IOException ex){}
               dispose();
               new StageMenu(character[cha], cha, enemy);
            }
            else if(e.getSource() == exitb)
            {
               dispose();
               new MainMenu();
            }
            else if(e.getSource() == deleteb)
            {
               new Delete();
               dispose();
            }
         }    
      }
   
   }
   public class Delete extends JFrame
   {
      private JPanel save1, save2, save3, exit;
      private int width = 300;
      private int height = 300;
      private JButton load1, load2, load3, exitb;
      public Delete()
      {
         setTitle("Save Files");
         setSize(width, height);
         setLayout(new GridLayout(4,1));
         buildSave1();
         buildSave2();
         buildSave3();
         buildExit();
         add(save1);
         add(save2);
         add(save3);
         add(exit);
         setVisible(true);
      }
      public void buildSave1()
      {
         save1 = new JPanel();
         save1.setBorder(BorderFactory.createTitledBorder("Save 1"));
         File file = new File("Character0.txt");
         if(file.exists())
         {
            character[0] = new Character();
            try{
               character[0].loadSave(0);
            }
            catch(IOException ex){}
            load1 = new JButton(character[0].getName()+ " LVL" + character[0].getLevel() + " " + character[0].getJob());
            load1.addActionListener(new DeleteListener());
         }
         else
         {
            load1 = new JButton("None");
         }
         save1.add(load1);
      }
      public void buildSave2()
      {
         save2 = new JPanel();
         save2.setBorder(BorderFactory.createTitledBorder("Save 2"));
         File file = new File("Character1.txt");
         if(file.exists())
         {
            character[1] = new Character();
            try{
               character[1].loadSave(1);
            }
            catch(IOException ex){}
            load2 = new JButton(character[1].getName()+ " LVL" + character[1].getLevel() + " " + character[1].getJob());
            load2.addActionListener(new DeleteListener());
         }
         else
         {
            load2 = new JButton("None");
         }
         save2.add(load2);
      }
      public void buildSave3()
      {
         save3 = new JPanel();
         save3.setBorder(BorderFactory.createTitledBorder("Save 3"));
         File file = new File("Character2.txt");
         if(file.exists())
         {
            character[2] = new Character();
            try{
               character[2].loadSave(2);
            }
            catch(IOException ex){}
            load3 = new JButton(character[2].getName()+ " LVL" + character[2].getLevel() + " " + character[2].getJob());
            load3.addActionListener(new DeleteListener());
         }
         else
         {
            load3 = new JButton("None");
         }
         save3.add(load3);
      }
      public void buildExit()
      {
         exit = new JPanel();
         exitb = new JButton("Back");
         exitb.addActionListener(new DeleteListener());
         exit.add(exitb);
      }
      private class DeleteListener implements ActionListener 
      {
         public void actionPerformed(ActionEvent e) 
         {  
            if(e.getSource() == load1)
            {
               File f = new File("Character0.txt");
               f.delete();
               JOptionPane.showMessageDialog(null, "Save file deleted");
               dispose();
               new Save();
            }
            else if(e.getSource() == load2)
            {
               File f = new File("Character1.txt");
               f.delete();
               JOptionPane.showMessageDialog(null, "Save file deleted");
               dispose();
               new Save();
            }   
            else if(e.getSource() == load3)
            {
               File f = new File("Character2.txt");
               f.delete();
               JOptionPane.showMessageDialog(null, "Save file deleted");
               dispose();
               new Save();}
            else if(e.getSource() == exitb)
            {
               dispose();
               new Save();
            }
         }    
      }
   }
   public class MonsterList extends JFrame
   {
      private int width = 370;
      private int height = 350;
      private JList <String> list;
      private JButton backB, okB;
      private String monsterName[], selected;
      private JPanel panel, sort, ok, back, okP, backP;
      private JRadioButton lowHigh, highLow, nameSort;
      private JScrollPane scroll;
      public MonsterList()
      {
         setSize(width, height);
         setTitle("SwordRPG");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(2,2));
         buildMonsterListPanel();
         buildBackPanel();
         buildOkPanel();
         buildSortPanel();
         add(sort);
         add(panel);
         add(back);
         add(ok);
         setVisible(true);
      }
      public void buildSortPanel()
      {
         sort = new JPanel(new GridLayout(3,1));
         lowHigh = new JRadioButton("Level: low --> high");
         highLow = new JRadioButton("Level: high --> low");
         nameSort = new JRadioButton("Name: alphabetically");
         sort.setBorder(BorderFactory.createTitledBorder("Sort By"));
         ButtonGroup group = new ButtonGroup();
         group.add(lowHigh);
         group.add(highLow);
         group.add(nameSort);
         lowHigh.addActionListener(new SortListener());
         highLow.addActionListener(new SortListener());
         nameSort.addActionListener(new SortListener());
         lowHigh.doClick();
         sort.add(lowHigh);
         sort.add(highLow);
         sort.add(nameSort);
      }
      public void buildMonsterListPanel()
      {
         panel = new JPanel();
         monsterName = new String [100];
         for (int i = 0; i < monsterName.length; i++)
         {monsterName[i] = enemy.getName(i);}
         list = new JList<> (monsterName);
         list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         list.setBorder(BorderFactory.createLineBorder(Color.black, 1));
         list.setVisibleRowCount(8);
         selected = monsterName[0];
         list.addListSelectionListener(new MonsterListener());
         scroll = new JScrollPane(list);
         panel.add(scroll);
      }
      public void buildBackPanel()
      {
         back = new JPanel(new BorderLayout());
         backP = new JPanel(new FlowLayout());
         backB = new JButton("Back to Main Menu");
         backB.addActionListener(new ButtonListener());
         backP.add(backB);
         back.add(backP, BorderLayout.SOUTH);
      }
      public void buildOkPanel()
      {
         ok = new JPanel(new BorderLayout());
         okP = new JPanel(new FlowLayout());
         okB = new JButton("View");
         okB.addActionListener(new ButtonListener());
         okP.add(okB);
         ok.add(okP, BorderLayout.SOUTH);
      }
      private class SortListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == lowHigh)
            {
               panel.remove(scroll);
               for (int i = 0; i < monsterName.length; i++) monsterName[i] = enemy.getName(i);
               list = new JList<> (monsterName);
               list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
               list.setBorder(BorderFactory.createLineBorder(Color.black, 1));
               list.setVisibleRowCount(8);
               selected = monsterName[0];
               list.addListSelectionListener(new MonsterListener());
               scroll = new JScrollPane(list);
               panel.add(scroll);
               panel.revalidate();
               panel.repaint();
            }
            else if(e.getSource() == highLow)
            {
               panel.remove(scroll);
               for(int i = 0; i < monsterName.length; i++) monsterName[99-i] = enemy.getName(i);
               list = new JList<> (monsterName);
               list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
               list.setBorder(BorderFactory.createLineBorder(Color.black, 1));
               list.setVisibleRowCount(8);
               selected = monsterName[0];
               list.addListSelectionListener(new MonsterListener());
               scroll = new JScrollPane(list);
               panel.add(scroll);
               panel.revalidate();
               panel.repaint();
            
            }
            else if(e.getSource() == nameSort)
            {
               panel.remove(scroll);
               String temp;
               for (int i = 0; i < monsterName.length; i++) monsterName[i] = enemy.getName(i);
               for(int i = 0; i < monsterName.length; i++)
               {
                  for(int j = i+1; j < monsterName.length; j++)
                  {
                     if(monsterName[i].compareTo(monsterName[j]) > 0)
                     {
                        temp = monsterName[i];
                        monsterName[i] = monsterName[j];
                        monsterName[j] = temp;
                     }
                  }
               }
               list = new JList<> (monsterName);
               list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
               list.setBorder(BorderFactory.createLineBorder(Color.black, 1));
               list.setVisibleRowCount(8);
               selected = monsterName[0];
               list.addListSelectionListener(new MonsterListener());
               scroll = new JScrollPane(list);
               panel.add(scroll);
               panel.revalidate();
               panel.repaint();}
         }
      }
      
      private class ButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == backB)
            {
               dispose();
               new MainMenu();
            }
            else if(e.getSource() == okB)
            {
               int index = 0;
               for(int i = 0; i < monsterName.length; i++)
               {
                  if(selected.compareTo(enemy.getName(i))== 0)
                     index = i;
               }
               new EnemyStats(index);
            }
         }
      }
      private class MonsterListener implements ListSelectionListener
      {
         public void valueChanged(ListSelectionEvent e)
         {
            selected = (String) list.getSelectedValue();
         }
      }
      
      public class EnemyStats extends JFrame
      {
         private int width = 400;
         private int height = 350;
         private int index;
         private JPanel stats;
         private JLabel enemyImage, name, hp,level, atk, def, xp, money;
         public EnemyStats(int i)
         {
            index = i;
            setSize(width, height);
            setTitle("SwordRPG");
            setLayout(new BorderLayout());
            enemyImage = new JLabel(enemy.getMonster(i));
            buildStats();
            add(enemyImage, BorderLayout.WEST);
            add(stats, BorderLayout.CENTER);
            setVisible(true);
         }
         public void buildStats()
         {
            stats = new JPanel(new GridLayout(7,1));
            stats.setBorder(BorderFactory.createTitledBorder("STATS"));
            name = new JLabel   ("Name:        " + enemy.getName(index));
            level = new JLabel  ("Level:       " + enemy.getLevel(index));
            if(enemy.getMet(index) == 1)
            {
               hp = new JLabel    ("HP:          " + enemy.getHp(index));
               atk = new JLabel    ("Attack:      " + enemy.getAtk(index));
               def = new JLabel    ("Defense:     " + enemy.getDef(index));
               xp = new JLabel     ("XP:          " + enemy.getXp(index) + "XP");
               money = new JLabel  ("Money:       " + enemy.getMoney(index) + "G");
            }
            else
            {
               hp = new JLabel    ("HP:          ???");
               atk = new JLabel    ("Attack:      ???");
               def = new JLabel    ("Defense:     ???");
               xp = new JLabel     ("XP:          ???XP");
               money = new JLabel  ("Money:       ???G");
            }
            stats.add(name);
            stats.add(level);
            stats.add(hp);
            stats.add(atk);
            stats.add(def);
            stats.add(xp);
            stats.add(money);
         }
      }
   }
}  