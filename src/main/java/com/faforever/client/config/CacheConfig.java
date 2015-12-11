package com.faforever.client.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static com.faforever.client.config.CacheNames.ACHIEVEMENTS;
import static com.faforever.client.config.CacheNames.AVATARS;
import static com.faforever.client.config.CacheNames.COUNTRY_FLAGS;
import static com.faforever.client.config.CacheNames.GRAVATAR;
import static com.faforever.client.config.CacheNames.LARGE_MAP_PREVIEW;
import static com.faforever.client.config.CacheNames.LEADERBOARD;
import static com.faforever.client.config.CacheNames.MODS;
import static com.faforever.client.config.CacheNames.SMALL_MAP_PREVIEW;
import static com.faforever.client.config.CacheNames.STATISTICS;
import static com.faforever.client.config.CacheNames.URL_PREVIEW;
import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

  @Bean
  @Override
  public CacheManager cacheManager() {
    SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
    simpleCacheManager.setCaches(Arrays.asList(
        new GuavaCache(LARGE_MAP_PREVIEW, newBuilder().maximumSize(30).build()),
        new GuavaCache(SMALL_MAP_PREVIEW, newBuilder().maximumSize(30).build()),
        new GuavaCache(COUNTRY_FLAGS, newBuilder().maximumSize(100).build()),
        new GuavaCache(AVATARS, newBuilder().maximumSize(30).build()),
        new GuavaCache(URL_PREVIEW, newBuilder().maximumSize(10).expireAfterAccess(30, MINUTES).build()),
        new GuavaCache(STATISTICS, newBuilder().maximumSize(10).expireAfterAccess(20, MINUTES).build()),
        new GuavaCache(GRAVATAR, newBuilder().maximumSize(10).expireAfterAccess(120, MINUTES).build()),
        new GuavaCache(ACHIEVEMENTS, newBuilder().maximumSize(1).expireAfterAccess(120, MINUTES).build()),
        new GuavaCache(MODS, newBuilder().maximumSize(10).expireAfterAccess(120, MINUTES).build()),
        new GuavaCache(LEADERBOARD, newBuilder().maximumSize(1).expireAfterAccess(30, SECONDS).build())
    ));
    return simpleCacheManager;
  }

  @Override
  public CacheResolver cacheResolver() {
    return null;
  }

  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    return new SimpleKeyGenerator();
  }

  @Override
  public CacheErrorHandler errorHandler() {
    return null;
  }
}
