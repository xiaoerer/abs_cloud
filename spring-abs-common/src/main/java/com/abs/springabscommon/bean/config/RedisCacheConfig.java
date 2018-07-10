package com.abs.springabscommon.bean.config;

import com.abs.springabscommon.bean.cache.CacheItemConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 9:09 2018/7/4
 * 增强redis
 **/
@Configurable
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Value("${redis.cache.expiration:3600")
    private Long expiration;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    public class Receiver {


        private CountDownLatch latch;

        @Autowired
        public Receiver(CountDownLatch latch) {
            this.latch = latch;
        }

        public void receiveMessage(String message) {
            latch.countDown();
        }
    }


    //------------------------
    /*@Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration cacheConfiguration =RedisCacheConfiguration.defaultCacheConfig()    //缓存默认设置
                .entryTtl(Duration.ofHours(1))//缓存1小时
                .disableCachingNullValues().computePrefixWith(cacheName -> "miaosha1".concat(":").concat(cacheName).concat(":"))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new
                        GenericJackson2JsonRedisSerializer()));
        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames =  new HashSet<>();
        cacheNames.add("my-redis-cache1");
        cacheNames.add("user");

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("my-redis-cache1", cacheConfiguration);
        configMap.put("user", cacheConfiguration.entryTtl(Duration.ofSeconds(120)));
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     // 使用自定义的缓存配置初始化一个cacheManager
                .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }*/

    //------------//------------------//-------------
    /*@Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置默认的过期时间(以秒为单位)
        rcm.setDefaultExpiration(600);
        //rcm.setExpires();设置缓存区域(按key)的过期时间(以秒为单位)
        return rcm;
    }*/

   /* @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory,RedisTemplate<Object, Object> redisTemplate) {

        CacheItemConfig productCacheItemConfig=new CacheItemConfig();
        productCacheItemConfig.setName("Product");
        productCacheItemConfig.setExpiryTimeSecond(10);
        productCacheItemConfig.setPreLoadTimeSecond(5);

        List<CacheItemConfig> cacheItemConfigs= Lists.newArrayList(productCacheItemConfig);

        CustomizedRedisCacheManager cacheManager = new CustomizedRedisCacheManager(connectionFactory,redisTemplate,cacheItemConfigs);

        return cacheManager;
    }*/



}
