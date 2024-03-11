package my.project.msa.my_msa_order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyMsaOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMsaOrderServiceApplication.class, args);
    }

}
