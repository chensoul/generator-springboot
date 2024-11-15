package <%= packageName %>;

import <%= packageName %>.common.ContainersConfig;
import org.springframework.boot.SpringApplication;

public class Test<%= appVarName %>Application {
    
    public static void main(String[] args) {
        SpringApplication.from(<%= appVarName %>Application::main).with(ContainersConfig.class).run(args);
    }
}
