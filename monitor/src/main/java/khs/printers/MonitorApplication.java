package khs.printers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class MonitorApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(MonitorApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter("pid.txt"));  // Pid Listener
		springApplication.run(args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://10.214.200.39:8081");
			}
		};
	}

}
