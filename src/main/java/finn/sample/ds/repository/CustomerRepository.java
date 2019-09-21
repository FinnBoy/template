package finn.sample.ds.repository;

import finn.sample.quickstart.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Finn Zhao
 * @version 2019-09-13
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

}
