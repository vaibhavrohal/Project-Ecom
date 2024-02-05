package com.ecomservice.ecomservice;

import com.ecomservice.ecomservice.Service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomServiceApplication implements CommandLineRunner {
    private InitService initService;
    public EcomServiceApplication(InitService initService){
        this.initService=initService;
    }

    public static void main(String[] args) {

        SpringApplication.run(EcomServiceApplication.class, args);
    }
    @Override
    public void run(String...args) throws Exception{
        initService.initialise();
    }
}
