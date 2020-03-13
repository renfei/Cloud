package net.renfei.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 账户中心
 *
 * @author RenFei
 */
@EnableAsync
@EnableFeignClients
@SpringCloudApplication
@MapperScan("net.renfei.datacenter.database.*")
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
