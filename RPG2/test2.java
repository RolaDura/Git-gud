import java.io.*;
import java.util.*;
public class test2{
public static void main (String[] args) throws IOException{
  ShopList shop = new ShopList();
  BufferedReader load = new BufferedReader (new FileReader ("Shop/Weapon.txt"));
  String hello = load.readLine();
  load.close();
  String[] a = new String [10];
  a = shop.getWeaponName();
  for (int i = 0; i < a.length; i++){
    System.out.println (a[i]);
  }
}
}
  