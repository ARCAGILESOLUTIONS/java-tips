package com.ashishpaliwal.javatips;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

/**
 * A simple Getting Started with EhCache
 */
public class EhcacheDemo {

  public static void simpleCacheDemo() {

    CacheManager cacheManager = CacheManager.newInstance();
    Ehcache cache = cacheManager.addCacheIfAbsent("testCache");

    Element cacheElement1 = new Element("Key-1", "Value-1");
    Element cacheElement2 = new Element("Key-2", "Value-2");
    Element cacheElement3 = new Element("Key-3", "Value-3");
    cache.put(cacheElement1);
    cache.put(cacheElement2);
    cache.put(cacheElement3);

    System.out.println(cache.get("Key-1").getObjectValue());
    System.out.println(cache.get("Key-3").getObjectValue());
    System.out.println(cache.isKeyInCache("Key-4"));
    System.out.println(cache.isKeyInCache("Key-1"));

    cacheManager.shutdown();
  }

  public static void ehcacheProgramaticConfiguration() {
    CacheConfiguration cacheConfiguration = new CacheConfiguration();
    cacheConfiguration.setName("testCache");
    cacheConfiguration.setMaxEntriesLocalHeap(1000);
    cacheConfiguration.setTimeToLiveSeconds(1000);

    Configuration cacheManagerConfiguration = new Configuration();
    cacheManagerConfiguration.addCache(cacheConfiguration);
    CacheManager cacheManager = CacheManager.create(cacheManagerConfiguration);

    Ehcache cache = cacheManager.getEhcache("testCache");

    Element cacheElement1 = new Element("Key-1", "Value-1");
    Element cacheElement2 = new Element("Key-2", "Value-2");
    Element cacheElement3 = new Element("Key-3", "Value-3");
    cache.put(cacheElement1);
    cache.put(cacheElement2);
    cache.put(cacheElement3);

    System.out.println(cache.get("Key-1").getObjectValue());
    System.out.println(cache.get("Key-3").getObjectValue());
    System.out.println(cache.isKeyInCache("Key-4"));
    System.out.println(cache.isKeyInCache("Key-1"));

    cacheManager.shutdown();
  }

  public static void main(String[] args) {
    simpleCacheDemo();
    System.out.println("=================================");
    ehcacheProgramaticConfiguration();
  }

}
