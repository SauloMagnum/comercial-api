package br.com.comercial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComercialApiApplication {

	public static void main(String[] args) {
		//System.setProperty("server.servlet.context-path", "/comercial-api");//Set path
		SpringApplication.run(ComercialApiApplication.class, args);
	}

}
