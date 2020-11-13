package no.frodo.dd.config;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager("getCustomer2");
    }

    //execute once every 10 second (fixedDelay=10000)
    @CacheEvict(allEntries = true, cacheNames = { "getCustomer2" })
    @Scheduled(fixedDelay = 10000)
    public void cacheEvict() {
        System.out.println("** evict cache");
    }

}
