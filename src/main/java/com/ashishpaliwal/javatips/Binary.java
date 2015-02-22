package com.ashishpaliwal.javatips;

/**
 * Created by ashishpaliwal on 27/01/15.
 */
public class Binary {

  public static int MASK = 0x01;

  public static int reverseBinary(int value) {
    int retVal = value >>> 31;
//    System.out.println(retVal);
    return retVal;
  }

  public static void toBinary(int value) {

    byte[] bytes = new byte[32];

    if(value == 0) {
      System.out.println("0");
      return;
    }

    int counter = 0;
    int val = 0;
    while(value > 0) {
      int remainder = value % 2;
      val = val | (MASK & remainder) << counter;

      System.out.print(remainder);
      value /= 2;
      counter++;
    }

    int finalValue = bytes[3] << 24 | (bytes[2] & 0xFF) << 16 | (bytes[1] & 0xFF) << 8 | (bytes[0] & 0xFF);
    System.out.println();
    System.out.println("Final Value = "+finalValue);
    System.out.println();
    System.out.println("val = "+val);
  }

  public static void main(String[] args) {
//    toBinary(13);
//    System.out.println("");
    System.out.println(Integer.toBinaryString(13));
    System.out.println(Integer.toBinaryString(reverseBinary(13)));
//    System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
//    System.out.println(Integer.MAX_VALUE);
  }

}
