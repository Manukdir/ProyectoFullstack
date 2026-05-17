package cl.duoc.fullstack.msenvios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsEnviosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsEnviosApplication.class, args);
    }
}
