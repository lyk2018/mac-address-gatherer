package tr.org.linux.kamp.macaddressgatherer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MacAddressGathererApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacAddressGathererApplication.class, args);
	}
}
