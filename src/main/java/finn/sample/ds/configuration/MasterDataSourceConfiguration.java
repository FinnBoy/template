package finn.sample.ds.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * @author Finn Zhao
 * @version 2019-09-22
 */
@Configuration
public class MasterDataSourceConfiguration {

    //@Primary
    //@Bean("masterDataSourceProperties")
    //@ConfigurationProperties("app.datasources.master")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    //@Bean
    //@ConfigurationProperties("app.datasources.master.configuration")
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean("masterDataSource")
    public DataSource embeddedDatabase() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("classpath:database/schema-h2.sql")
                .addScript("classpath:database/data-h2.sql")
                .build();
        return db;
    }

}
