package br.com.erudio.restwithspringbootudemy;

import br.com.erudio.restwithspringbootudemy.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableConfigurationProperties({FileStorageConfig.class})
@SpringBootApplication
public class Startup {

	public static void main(String[] args) {

		SpringApplication.run(Startup.class, args);

		/*BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(16);
		String result = bcrypt.encode("admin123");
		System.out.println("My hash " + result);*/

	}

}
