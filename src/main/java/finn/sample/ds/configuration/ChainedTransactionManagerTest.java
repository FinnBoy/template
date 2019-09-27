package finn.sample.ds.configuration;

import org.springframework.data.transaction.ChainedTransactionManager;

/**
 * @author Finn Zhao
 * @version 2019-09-28
 * <p>
 * 责任链式的事务管理器实现，多个DataSource的事务管理器使用时，可尝试使用此事务管理器？
 * @see ChainedTransactionManager
 */
public class ChainedTransactionManagerTest {

    public ChainedTransactionManager transactionManager() {
        return new ChainedTransactionManager();
    }
}
