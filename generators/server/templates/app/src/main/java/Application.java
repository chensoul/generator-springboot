package <%= packageName %>;

import <%= packageName %>.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class <%= appVarName %>Application {

    public static void main(String[] args) {
        SpringApplication.run(<%= appVarName %>Application.class, args);
    }
}
