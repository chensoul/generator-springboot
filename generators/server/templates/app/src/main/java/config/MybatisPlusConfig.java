package <%= packageName %>.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

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

}
