package com.seata.debit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.seata.debit.mapper"}) // mybatis包扫描
public class DebitApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebitApplication.class, args);
    }

}
