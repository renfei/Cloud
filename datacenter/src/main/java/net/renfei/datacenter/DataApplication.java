package net.renfei.datacenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 数据中心
 *
 * @author RenFei
 */
@EnableAsync
@SpringCloudApplication
@MapperScan("net.renfei.datacenter.database.*")
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}
