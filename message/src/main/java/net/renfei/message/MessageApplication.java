package net.renfei.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 消息中心
 *
 * @author RenFei
 */
@EnableAsync
@EnableFeignClients
@SpringCloudApplication
@MapperScan("net.renfei.datacenter.database.*")
public class MessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
