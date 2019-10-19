package finn.sample.ds.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DelegatingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Finn Zhao
 * @version 2019-09-28
 */
public class TestDelegatingDataSource extends DelegatingDataSource {

    private static final Logger log = LoggerFactory.getLogger(TestDelegatingDataSource.class);

    public TestDelegatingDataSource(DataSource targetDataSource) {
        super(targetDataSource);
    }

    @Override
    public Connection getConnection() throws SQLException {
        log.info("getConnection");
        return obtainTargetDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        log.info("getConnection");
        return obtainTargetDataSource().getConnection(username, password);
    }

}
