package <%= packageName %>.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<%_ if (persistence === "jpa") { _%>
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
<%_ } _%>
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
<%_ if (persistence === "jpa") { _%>
@EnableJpaRepositories({ "<%= packageName %>.repository" })
<%_ } _%>
@EnableTransactionManagement
public class DatabaseConfig {

}
