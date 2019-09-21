package finn.sample.ds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Finn Zhao
 * @version 2019-09-22
 */
@Component
public class SampleService {

    private static final Logger log = LoggerFactory.getLogger(SampleService.class);

    @Autowired
    private DataSource masterDataSource;

    @Autowired
    private DataSource slaveDataSource;

    public void test() throws SQLException {
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        Object val;

        connection = masterDataSource.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from PRODUCT_PRICE");
        while (resultSet.next()) {
            val = resultSet.getObject(1);
        }
        resultSet.close();
        statement.close();
        connection.close();

        connection = slaveDataSource.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from PRODUCT_PRICE");
        while (resultSet.next()) {
            val = resultSet.getObject(1);
        }
        resultSet.close();
        statement.close();
        connection.close();

        log.info(masterDataSource.toString());
        log.info(slaveDataSource.toString());
    }

    public void setMasterDataSource(DataSource masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    public void setSlaveDataSource(DataSource slaveDataSource) {
        this.slaveDataSource = slaveDataSource;
    }
}
