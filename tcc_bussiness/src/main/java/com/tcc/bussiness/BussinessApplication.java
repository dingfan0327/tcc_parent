package com.tcc.bussiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.tcc.bussiness.feign"})
@EnableTransactionManagement
@SpringBootApplication
public class BussinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BussinessApplication.class, args);
    }

}
