package com.sabre.tas.javasim.web ;

import org.springframework.web.bind.annotation.RestController ;
import org.springframework.web.bind.annotation.RequestMapping ;

@RestController
public class RootController {

    @RequestMapping( "/" )
    public String index() {

        return "Welcome to Javasim!" ;
    }

}
