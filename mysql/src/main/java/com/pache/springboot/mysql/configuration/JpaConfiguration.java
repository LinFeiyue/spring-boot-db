package com.pache.springboot.mysql.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置类，这个类适用于正常的启动方式，测试类使用JPA配置类与该类略有不同
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass = true) //启用JPA的事务管理
@EnableJpaRepositories(basePackages = "com.**.repository")  //启用了JPA资源库并指定上面自定义的接口资源库的位置
@EntityScan(basePackages = "com.**.entities") //指定自定义实体的位置
public class JpaConfiguration {

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
