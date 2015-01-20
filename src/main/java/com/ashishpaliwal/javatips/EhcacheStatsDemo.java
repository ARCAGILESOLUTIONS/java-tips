package com.ashishpaliwal.javatips;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.management.sampled.SampledCache;

import javax.management.NotCompliantMBeanException;

/**
 * Sample to see EhCacheStats
 */
public class EhcacheStatsDemo {

  public static void ehCacheStatsDemo() throws NotCompliantMBeanException {
    CacheManager cacheManager = CacheManager.newInstance();
    Ehcache cache = cacheManager.addCacheIfAbsent("testCache");
    SampledCache sampledCache = new SampledCache(cache);

    for (int i = 0; i < 100; i++) {
      Element element = new Element("Key-"+i, "Value-"+i);
      cache.put(element);
    }

    for (int i = 0; i < 100; i++) {
      cache.get("Key-" + 1);
    }
    System.out.println(sampledCache.getPutCount());
    System.out.println(sampledCache.getCacheMissCount());
    System.out.println(sampledCache.getCacheHitCount());
    cacheManager.shutdown();
  }

  public static void main(String[] args) throws NotCompliantMBeanException {
    ehCacheStatsDemo();
  }

}
