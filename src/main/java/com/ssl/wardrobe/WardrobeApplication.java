package com.ssl.wardrobe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.extern.slf4j.Slf4j;
import java.util.TimeZone;
import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.ssl")
@EnableTransactionManagement
@Slf4j
@EnableEncryptableProperties
@ComponentScan(basePackages = "com.ssl")
public class WardrobeApplication {

	public static void main(String[] args) {

		SpringApplication.run(WardrobeApplication.class, args);
		
	}

        @PostConstruct
    	public void init(){
        	TimeZone.setDefault(TimeZone.getTimeZone("IST"));
    	}

}
