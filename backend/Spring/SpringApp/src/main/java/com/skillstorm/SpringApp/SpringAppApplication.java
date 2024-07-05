package com.skillstorm.SpringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// this is the starting point for our entire app
// notice our main method
// it has a single annotation which comprises three other annotations

/*
 *  @EnableAutoConfiguration - eliminates the need for us to manually
 *  configure the context/settings
 *  
 *  @ComponentScan - Look through all child packages for Beans
 *  
 *  @Confidguration - Allow us to, if we wish, loop in the other files for config
 *  
 *  What is a Bean?
 *  
 *  - something Spring can automatically manage via an IoC container
 *  
 *  What is an IoC Container?
 *  
 *  - Inversion of Control - leave the instantiation and management of a bean.object/service
 *  etc, up to the container
 *  
 *  - We don't have to set it ourselves
 *  - there are several different annotations we will use to set on different kinds of bean for different porpuses
 *  - not every class is going to be a Bean mostly just ones we want to inject as a dependency
 *  
 *  What is a Dependency Injection?
 *  - 
 *  
 */
@SpringBootApplication
public class SpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

}
