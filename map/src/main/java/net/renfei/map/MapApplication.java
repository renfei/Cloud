package net.renfei.map;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 地图空间服务
 *
 * @author RenFei
 */
@EnableAsync
@EnableFeignClients
@SpringCloudApplication
public class MapApplication {
    public static void main(String[] args) {
        SpringApplication.run(MapApplication.class, args);
    }
}
