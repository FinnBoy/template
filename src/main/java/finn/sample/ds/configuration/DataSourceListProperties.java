package finn.sample.ds.configuration;

import finn.app.common.collection.IncreaseList;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Finn Zhao
 * @version 2019-09-28
 */
@Component
@ConfigurationProperties("app.datasource")
public class DataSourceListProperties {

    private DataSourceProperties master;

    private IncreaseList<DataSourceProperties> slaves = new IncreaseList<>(DataSourceProperties.class);

    public DataSourceProperties getMaster() {
        return master;
    }

    public void setMaster(DataSourceProperties master) {
        this.master = master;
    }

    public IncreaseList<DataSourceProperties> getSlaves() {
        return slaves;
    }

    public void setSlaves(IncreaseList<DataSourceProperties> slaves) {
        this.slaves = slaves;
    }
}
