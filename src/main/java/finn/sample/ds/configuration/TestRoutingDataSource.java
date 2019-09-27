package finn.sample.ds.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 配置主从数据库并使用读写分离:<br>
 * 要实现读写分离，最好不要对现有代码进行改动，而是在底层透明地支持。<br>
 * 可以把多个数据源配置成一个Map，然后，根据不同的key返回不同的数据源。<br>
 *
 * @author Finn Zhao
 * @version 2019-09-28
 */
public class TestRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }
}
