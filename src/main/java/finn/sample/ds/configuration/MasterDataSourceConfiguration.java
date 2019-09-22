package finn.sample.ds.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * @author Finn Zhao
 * @version 2019-09-22
 */
@Configuration
@EntityScan({"finn.sample.ds.entity"})
@EnableJpaRepositories(entityManagerFactoryRef = "masterEntityManagerFactory"
        , transactionManagerRef = "masterTransactionManager"
        , basePackages = {"finn.sample.ds.repository"})
@EnableTransactionManagement
public class MasterDataSourceConfiguration {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Bean
    @ConfigurationProperties("app.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean("masterDataSourceProperties")
    @ConfigurationProperties("app.datasources.master")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean("masterDataSource")
    @ConfigurationProperties("app.datasources.master.configuration")
    public DataSource dataSource() {
        //return productionDataSource();
        return embeddedDatabase();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
        JpaProperties jpaProperties = this.jpaProperties();

        PersistenceUnitManager unitManager = persistenceUnitManager.getIfAvailable();

        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
                jpaVendorAdapter(jpaProperties)
                , jpaProperties.getProperties()
                , unitManager);
        return builder;
    }

    @Primary
    @Bean("masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource())
                .packages(this.getPackagesToScan())
                .properties(this.jpaProperties().getProperties())
                .persistenceUnit("masterPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "masterTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean(builder).getObject());
        return jpaTransactionManager;
    }

    private DataSource productionDataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    private DataSource embeddedDatabase() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                //.addScript("classpath:database/schema-h2.sql")
                //.addScript("classpath:database/data-h2.sql")
                .build();
        return db;
    }

    private AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    private JpaVendorAdapter jpaVendorAdapter(JpaProperties jpaProperties) {
        AbstractJpaVendorAdapter adapter = createJpaVendorAdapter();
        adapter.setShowSql(jpaProperties.isShowSql());
        adapter.setDatabase(jpaProperties.determineDatabase(this.dataSource()));
        adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        return adapter;
    }

    private String[] getPackagesToScan() {
        List<String> packages = EntityScanPackages.get(this.beanFactory).getPackageNames();
        if (packages.isEmpty() && AutoConfigurationPackages.has(this.beanFactory)) {
            packages = AutoConfigurationPackages.get(this.beanFactory);
        }
        return StringUtils.toStringArray(packages);
    }

}
