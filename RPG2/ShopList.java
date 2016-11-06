import java.util.*;
import java.awt.*;
import java.io.*;
// Class for accessing weapon, armor and item list
public class ShopList
{  private String[] weaponName = new String[10];
   private String[] armorName = new String[10];
   private String[] itemName = new String[4];
   private int[] weaponAtk = new int[10];
   private int[] armorDef = new int[10];
   private int[] itemEff = new int[4];
   private int[] weaponPrice = new int[10];
   private int[] armorPrice = new int[10];
   private int[] itemPrice = new int[4];
   public ShopList() throws IOException
   {
      String atk, atkP, def, defP, it, itP, str;
      BufferedReader we = new BufferedReader(new FileReader("Shop/Weapon.txt"));
      BufferedReader ar = new BufferedReader(new FileReader("Shop/Armor.txt"));
      BufferedReader item = new BufferedReader(new FileReader("Shop/Item.txt"));
      for(int i = 0; i < weaponName.length; i++)
      {  
         weaponName[i] = we.readLine();
         atk = we.readLine();
         atkP = we.readLine();
         str = we.readLine();
         weaponAtk[i] = Integer.parseInt(atk);
         weaponPrice[i] = Integer.parseInt(atkP);
      }
      we.close();
      for(int i = 0; i < armorName.length; i++)
      {  
         armorName[i] = ar.readLine();
         def = ar.readLine();
         defP = ar.readLine();
         str = ar.readLine();
         armorDef[i] = Integer.parseInt(def);
         armorPrice[i] = Integer.parseInt(defP);
      }
      ar.close();
      for(int i = 0; i < itemName.length; i++)
      {
         itemName[i] = item.readLine();
         it = item.readLine();
         itP = item.readLine();
         str = item.readLine();
         itemEff[i] = Integer.parseInt(it);
         itemPrice[i] = Integer.parseInt(itP);
      }
      item.close();
   }
   
   public String[] getWeaponName()
   {
      return weaponName;
   }
   
   public String getWeaponName(int i)
   {
      return weaponName[i];
   }
   
   public int getWeaponAtk(int i)
   {
      return weaponAtk[i];
   }
   
   public int getWeaponPrice(int i)
   {
      return weaponPrice[i];
   }
   
   public String[] getArmorName()
   {
      return armorName;
   }
   
   public String getArmorName(int i)
   {
      return armorName[i];
   }
   
   public int getArmorDef(int i)
   {
      return armorDef[i];
   }
   
   public int getArmorPrice(int i)
   {
      return armorPrice[i];
   }
   
   public String[] getItemName()
   {
      return itemName;
   }
   
   public String getItemName(int i)
   {
      return itemName[i];
   }
   
   public int getItemEff(int i)
   {
      return itemEff[i];
   }
   
   public int getItemPrice(int i)
   {
      return itemPrice[i];
   }
   
}
   
