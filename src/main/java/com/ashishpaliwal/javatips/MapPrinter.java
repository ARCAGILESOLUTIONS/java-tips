package com.ashishpaliwal.javatips;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashishpaliwal on 19/01/15.
 */
public class MapPrinter {

  public static void printMapOnConcole(Map map) {
    Preconditions.checkNotNull(map);
    Joiner.MapJoiner mapJoiner = Joiner.on("\n").withKeyValueSeparator("=");
    System.out.println(mapJoiner.join(map));
  }

  public static void printMapOnConcole(Map map, String recordSeparator, String keyValSeparator) {
    Preconditions.checkNotNull(map);
    Preconditions.checkArgument(!Strings.isNullOrEmpty(recordSeparator));
    Preconditions.checkArgument(!Strings.isNullOrEmpty(keyValSeparator));

    Joiner.MapJoiner mapJoiner = Joiner.on(recordSeparator).withKeyValueSeparator(keyValSeparator);
    System.out.println(mapJoiner.join(map));
  }

  public static void main(String[] args) {
    Map<String, String> maps = new HashMap<String, String>();
    maps.put("key1", "value1");
    maps.put("key2", "value2");
    maps.put("key3", "value3");
    printMapOnConcole(maps);
    printMapOnConcole(maps, "\n", "\t");
    printMapOnConcole(maps, "", "");
  }

}
