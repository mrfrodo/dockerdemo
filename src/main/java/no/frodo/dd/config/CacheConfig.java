package no.frodo.dd.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager("getCustomer2");
    }

    //execute once every 20 second (fixedDelay=10000)
    @CacheEvict(allEntries = true, cacheNames = { "getCustomer2" })
    @Scheduled(fixedDelay = 20000)
    public void cacheEvict() {
        logger.info("__ Evic cache {} {}", "getCustomr2", "from the system");
    }

}
