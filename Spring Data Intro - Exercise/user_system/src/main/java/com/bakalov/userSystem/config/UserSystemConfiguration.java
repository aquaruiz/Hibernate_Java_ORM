package com.bakalov.userSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class UserSystemConfiguration {

    @Bean
    public BufferedReader reader(){
        return new BufferedReader(streamReader());
    }

    @Bean
    public InputStreamReader streamReader(){
        return new InputStreamReader(System.in);
    }

}
