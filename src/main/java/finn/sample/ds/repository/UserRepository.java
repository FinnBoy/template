package finn.sample.ds.repository;

import finn.sample.ds.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Finn Zhao
 * @version 2019-09-22
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
