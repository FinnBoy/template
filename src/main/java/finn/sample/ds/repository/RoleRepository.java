package finn.sample.ds.repository;

import finn.sample.ds.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Finn Zhao
 * @version 2019-09-22
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
