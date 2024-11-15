package <%= packageName %>.config;

<%_ if (persistence === "mybatis") { _%>
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
<%_ } _%>
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
    <%_ if (persistence === "mybatis") { _%>
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
    <%_ if (databaseType === 'postgresql') { _%>
        return new PaginationInnerInterceptor(DbType.POSTGRE_SQL);
    <%_ } _%>
    <%_ if (databaseType === 'mysql') { _%>
        return new PaginationInnerInterceptor(DbType.MYSQL);
    <%_ } _%>
    <%_ if (databaseType === 'mariadb') { _%>
        return new PaginationInnerInterceptor(DbType.MARIADB);
    <%_ } _%>
    }
    <%_ } _%>

}
