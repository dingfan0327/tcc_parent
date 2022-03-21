//package com.tcc.common_db;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import io.seata.rm.datasource.DataSourceProxy;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfiguration {
//    /**
//     * 使⽤druid连接池
//     *
//     * @return
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//
//    /**
//     * 设置数据源代理-,完成分⽀事务注册/事务提交与回滚等操作
//     * 启动扫描配置类,分别加载每个⼯程的启动类中
//     * 6. 添加注解@GlobalTransactional
//     * 3.Seata-TCC模式
//     * 3.1 TCC模式介绍
//     * Seata 开源了 TCC 模式，该模式由蚂蚁⾦服贡献。TCC 模式需要⽤户根据⾃⼰的业务场景实现
//     * Try、Confirm 和 Cancel 三个操作；事务发起⽅在⼀阶段 执⾏ Try ⽅式，在⼆阶段提交执⾏ Confirm
//     * ⽅法，⼆阶段回滚执⾏ Cancel ⽅法。
//     *
//     * @param druidDataSource
//     * @return
//     */
//    @Primary //设置⾸选数据源对象
//    @Bean("dataSource")
//    public DataSourceProxy dataSource(DataSource druidDataSource) {
//        return new DataSourceProxy(druidDataSource);
//    }
//}