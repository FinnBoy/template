package finn.sample.ds.configuration;

import org.springframework.jdbc.datasource.DelegatingDataSource;

/**
 * @author Finn Zhao
 * @version 2019-09-28
 */
public class TestDelegatingDataSource {

    public DelegatingDataSource delegatingDataSource() {
        return new DelegatingDataSource();
    }
}
