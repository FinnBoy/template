package finn.sample.ds.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

/**
 * @author Finn Zhao
 * @version 2019-09-22
 */
@Configuration
public class SlaveDataSourceConfiguration {

    //@Bean("slaveDataSourceProperties")
    //@ConfigurationProperties("app.datasources.slave")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    //@Bean("slaveDataSource")
    //@ConfigurationProperties("app.datasources.slave.configuration")
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean("slaveDataSource")
    public DataSource embeddedDatabase() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("classpath:database/schema-hsqldb.sql")
                .addScript("classpath:database/data-hsqldb.sql")
                .build();
        return db;
    }

}
