package org.jsp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProjectStandAlone1Application {

	public static void main(String[] args) {
		System.out.println("start");
		SpringApplication.run(SpringBootProjectStandAlone1Application.class, args);
		System.out.println("end...");
	}

}
