package com.tekion.cricket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class CricketApplication {
	private static Logger logger = Logger.getLogger(CricketApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CricketApplication.class, args);
		System.out.println("Hi");
	}



}
