package com.sabre.tas.javasim.web ;

import org.springframework.boot.actuate.health.Health ;
import org.springframework.boot.actuate.health.HealthIndicator ;
import org.springframework.stereotype.Component ;

@Component
public class HealthCheck implements HealthIndicator {
  
    @Override
    public Health health() {
        int statusCode = checkStatus() ; 
        if( statusCode != 0 ) {
            return Health.down().withDetail( "Status Code", statusCode ).build() ;
        }
        return Health.up().build() ;
    }
     
    public int checkStatus() {
        // TODO: Add logic to return a meaningful status code 
        return 0 ;
    }
}
