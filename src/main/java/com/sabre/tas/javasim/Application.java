package com.sabre.tas.javasim ;

import java.util.Arrays ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.boot.CommandLineRunner ;
import org.springframework.boot.SpringApplication ;
import org.springframework.boot.autoconfigure.SpringBootApplication ;
import org.springframework.context.ApplicationContext ;
import org.springframework.context.annotation.Bean ;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {

    private final Logger log = LoggerFactory.getLogger( this.getClass() ) ;

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args ) ;
    }

    @Bean
    public CommandLineRunner commandLineRunner( ApplicationContext ctx ) {
        return args -> {

            log.info( "Beans provided by Spring Boot:" ) ;

            String[] beanNames = ctx.getBeanDefinitionNames() ;
            Arrays.sort( beanNames ) ;
            for( String beanName : beanNames ) {

                log.info( "    - " + beanName ) ;
            }
        };
    }
}
