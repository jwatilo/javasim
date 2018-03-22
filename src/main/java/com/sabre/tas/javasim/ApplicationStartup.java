package com.sabre.tas.javasim ;

import java.util.List ;
import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.Collections ;
import java.util.Properties ;
import java.util.stream.StreamSupport ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.context.event.ApplicationReadyEvent ;
import org.springframework.context.ApplicationListener ;
import org.springframework.core.env.AbstractEnvironment ;
import org.springframework.core.env.ConfigurableEnvironment ;
import org.springframework.core.env.EnumerablePropertySource ;
import org.springframework.core.env.Environment ;
import org.springframework.core.env.MutablePropertySources ;
import org.springframework.core.env.PropertySource ;
import org.springframework.stereotype.Component ;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger log = LoggerFactory.getLogger( this.getClass() ) ;
    private final static String DOTTED_LINE = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" ;

    @Autowired
    Environment springEnv ;

    /**
     * This event is executed as late as conceivably possible to indicate that 
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent( final ApplicationReadyEvent event ) {
 
        log.info( DOTTED_LINE ) ;
        log.info( " SPRING PROPERTIES" ) ;
        log.info( DOTTED_LINE ) ;
        Properties props = getSpringEnvironmentProperties() ; 
        printSortedProperties( props ) ;

        log.info( DOTTED_LINE ) ;
        log.info( " WELCOME TO JAVASIM 3000!"   ) ;
        log.info( " Ready to receive requests on port " + props.get( "server.port" ) + "." ) ; 
        log.info( DOTTED_LINE ) ;

        return ;
    }

    private void printSortedProperties( Properties properties ) {
      
        List<String> keys  = new ArrayList<>() ;
        for( String key : properties.stringPropertyNames() ) {
            keys.add( key ) ;
        }
        Collections.sort( keys ) ;
        for( String key : keys ) {

            log.info( " " + key + " = " + properties.getProperty( key ) ) ;
        }
    }

    private Properties getSpringEnvironmentProperties() {

        Properties properties = new Properties() ;
        CharSequence dot = "." ;

        if( springEnv instanceof ConfigurableEnvironment ) {
            for( PropertySource<?> propertySource : ((ConfigurableEnvironment) springEnv).getPropertySources() ) {
                if( propertySource instanceof EnumerablePropertySource ) {
                    for( String key : ((EnumerablePropertySource) propertySource).getPropertyNames() ) {
                        if( key.contains( dot ) ) { 
                          Object val = propertySource.getProperty( key ) ;
                          if( val instanceof String ) {

                                properties.setProperty( key, (String)val ) ;
                            }
                        }
                    }
                }
            }
        }

        return properties ;
    }
}

