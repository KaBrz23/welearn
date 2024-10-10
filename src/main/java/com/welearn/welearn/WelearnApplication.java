package com.welearn.welearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class WelearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelearnApplication.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Deploy efetuado com sucesso no Servidor Tomcat 10";
	}

}

