package net.renfei.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 *
 * @author RenFei
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisterCenterApplication.class, args);
    }
}
