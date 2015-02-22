package com.ashishpaliwal.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ashishpaliwal on 22/02/15.
 */
public class FileUtil {

  public static void generateFileWithCount(int count, String fileName, String prefix) {
    File file = new File(fileName);
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(file));
      for (int i = 0; i < count; i++) {
        writer.write(String.format("%s %d", prefix, i));
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
        // noop
      }
    }

  }

  public static void main(String[] args) {
    FileUtil.generateFileWithCount(10000, "Sample10k.txt", "This is line number");
  }

}
